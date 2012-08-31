package com.practice.jms;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import etrace.rif.dbutil.*;

public class MQAdapter implements javax.jms.MessageListener
{
	Context context 			= null;
	ConnectionFactory factory 	= null;
	Connection connection 		= null;
	String factoryName 			= "";
	String destName 			= null;
	String faultDestName		= null;
	Destination dest 			= null;
	Destination faultDest		= null;
	int count 					= 1;
	Session session 			= null;
	MessageConsumer receiver 	= null;
	MessageProducer sender 		= null;
	Message message 			= null;
	TextMessage text 			= null;
	String UserName				= null;
	String Password				= null;

	String FILE_TYPE 			= ".xml";
	String FILE_CREATE_MODE		= "DEL_AND_NEW";
	String file_seperator		= System.getProperty("file.separator");
	String stPath;
	String FAULT_MSG;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	String[] importTypeandFilename = null;
	List messageList			   = null;	

	boolean bnStatus = false;
	private PreparedStatement selectPrepareStmt;

	public MQAdapter()
	{
		
	}
	
	/**
	 * @param oid
	 * @param cid
	 * @param prop
	 * @return List
	 * 
	 */
	public List getMQData(int oid,String cid,Properties prop)
	{
		try
		{
			prop.put(Context.INITIAL_CONTEXT_FACTORY,prop.get("CONTEXT_FACTORY"));
			prop.put(Context.PROVIDER_URL,prop.get("PROVIDER_URL"));

			if (prop.getProperty("SECURITY_PRINCIPAL") != null && prop.getProperty("SECURITY_CREDENTIALS") != null) 
			{
				prop.put(Context.SECURITY_PRINCIPAL,prop.get("SECURITY_PRINCIPAL"));
				prop.put(Context.SECURITY_CREDENTIALS,prop.get("SECURITY_CREDENTIALS"));
			}
			
			stPath 				= (String)prop.getProperty("FILE_DEST_IN");
			factoryName 		= (String)prop.getProperty("CONNECT_FACTORY");
			destName 			= (String)prop.getProperty("JMS_OBJECT");
			faultDestName		= (String)prop.getProperty("FAULT_QUEUE");
			FILE_CREATE_MODE 	= (String)prop.getProperty("FILE_MODE");
			FILE_TYPE 			= (String)prop.getProperty("INPUT_FILE_TYPE");
			FAULT_MSG			= (String)prop.getProperty("FAULT_MSG");
			UserName			= (String)prop.getProperty("UserName");
			Password			= (String)prop.getProperty("Password");
			
			
			try 
			{
				context = (Context)new InitialContext(prop);    
			}
			catch(NamingException ne) 
			{
				System.out.println("not sure what has happened ");
				ne.printStackTrace(System.out);
			}
			
            factory 	= (ConnectionFactory) context.lookup(factoryName);
            dest 		= (Destination) context.lookup(destName);
            faultDest	= (Destination) context.lookup(faultDestName);
            
            if(UserName!=null && Password!=null)
            	connection 	= factory.createConnection(UserName,Password);
            else
            	connection 	= factory.createConnection();
            
            session 	= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			receiver 	= session.createConsumer(dest);
			
			messageList	= new ArrayList();
			
			System.out.println("before connection start");
			// if u want to use Message Listener then uncomment the below line and comment Block1
			//receiver.setMessageListener(this);
			
			 connection.start();
			//Block1
			//message = receiver.receive(30000);
			
			try
			{
				while((message = receiver.receiveNoWait())!=null)
				{
					System.out.println("in while loop");
					importTypeandFilename = new String[2];
					
					if(message.getStringProperty("IMPORT_TYPE")!=null)
						importTypeandFilename[0] = message.getStringProperty("IMPORT_TYPE");
		        	else
		        		importTypeandFilename[0] = "Others";
		        	
		        	if(message.getStringProperty("FILE_NAME")!=null)
		        		importTypeandFilename[1] =  sdf.format(new Date())+"_"+message.getStringProperty("FILE_NAME");
		        	else
		        		importTypeandFilename[1] = sdf.format(new Date())+"_dataload" ;
		        	
		        	importTypeandFilename[1] = validateFileName(importTypeandFilename[0],importTypeandFilename[1],1);
		        	
					if(!WritetoFile(message,importTypeandFilename[0]+file_seperator+importTypeandFilename[1]))
						throw new Exception("Message received but there was some problem in writing it to file.");
					
					System.out.println("importTypeandFilename-->"+(sdf.format(new Date())+"_"+message.getStringProperty("FILE_NAME")));
					
					messageList.add(importTypeandFilename);
				}
			}
			catch (JMSException jmse) 
			{
				sendFaultMsg(MessageFormat.format(FAULT_MSG, new Object[]{new Date()}));
				throw new Exception(" Error in receiving message sent a message to the Fault Queue - "+FAULT_MSG);
			}
			//Block1 Ends
		}
		catch(Exception e)
		{
			//bnStatus = false;
			importTypeandFilename=null;
			e.printStackTrace(System.out);
		}
		finally 
		{
            if (context != null)
			{
                try 
				{
                    context.close();
                } 
				catch(NamingException exception) 
				{
                    exception.printStackTrace(System.out);
                }
            }
            if (connection != null) 
			{
                try 
				{
                    connection.close();
                }
				catch(JMSException exception) 
				{
                    exception.printStackTrace(System.out);
                }
            }
		//	return bnStatus;
		}
		return messageList;
	}
	
	/*
	*
	*It is abstract method in MessageListener called when a message is available in the queue
	*/
	/* (non-Javadoc)
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	public void onMessage(javax.jms.Message aMessage)
	{
        try
        {
			importTypeandFilename = new String[2];
			
			if(aMessage.getStringProperty("IMPORT_TYPE")!=null)
				importTypeandFilename[0] = aMessage.getStringProperty("IMPORT_TYPE");
        	else
        		importTypeandFilename[0] = "Others";
        	
        	if(aMessage.getStringProperty("FILE_NAME")!=null)
        		importTypeandFilename[1] = sdf.format(new Date())+"_"+aMessage.getStringProperty("FILE_NAME");
        	else
        		importTypeandFilename[1] =  sdf.format(new Date())+"_dataload" ;
			
			if(!WritetoFile(aMessage,importTypeandFilename[0]+file_seperator+importTypeandFilename[1]))
				throw new Exception("Message received but there was some problem in writing it to file.");
			
			messageList.add(importTypeandFilename);
		}
        catch (JMSException jmse) 
        {
        	sendFaultMsg(MessageFormat.format(FAULT_MSG, new Object[]{new Date()}));
        	jmse.printStackTrace();
		}
        catch (Exception e) 
        {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param faultMsg
	 * @return boolean
	 * 
	 */
	public boolean sendFaultMsg(String faultMsg) 
	{
		System.out.println(faultMsg);
		try 
		{
			sender = session.createProducer(faultDest);
			TextMessage msg = session.createTextMessage();
			msg.setText(faultMsg);
			sender.send(msg,DeliveryMode.PERSISTENT,Message.DEFAULT_PRIORITY,100000);
			
			return true;
		}
		catch (JMSException jmse) 
		{
			jmse.printStackTrace();
			return false;
		}
	}

	public boolean WritetoFile(Message msg, String stFile)
	{
		StringBuffer stBuffer = new StringBuffer();
		FileOutputStream fileoutputstream;
		byte[] byteBuffer = new byte[1024];
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		int read = 0;
		try
		{
			if (msg instanceof TextMessage)
			{
				TextMessage inMsg = (TextMessage)msg ;
				stBuffer.append(inMsg.getText());
			}
			else if(msg instanceof BytesMessage) 
			{
				BytesMessage bms = (BytesMessage) msg;
					while ((read = (bms).readBytes(byteBuffer)) != -1) {
					bytes.write(byteBuffer, 0, read);
					}
					System.out.println(bytes.size());
			}
			else
				throw new Exception("Message is not of text or byte type");	
			
			if(stFile==null || stFile.equals(""))
				stFile = "upload";
			
			String stFileName = stPath+stFile+FILE_TYPE;
			File file = new File(stFileName);

			if(FILE_CREATE_MODE.equals("APPEND"))
			{
				fileoutputstream = new FileOutputStream(stFileName,true);
				if(file.exists())				
					fileoutputstream.write("\n".getBytes());
				
				if(bytes!=null && bytes.size()!=0)
					fileoutputstream.write(bytes.toByteArray());
				
				fileoutputstream.write(stBuffer.toString().getBytes());
				fileoutputstream.close();	
			}
			else if(FILE_CREATE_MODE.equals("DEL_AND_NEW"))
			{
				if(file.exists())
					file.delete();							
				fileoutputstream = new FileOutputStream(stFileName);
				if(bytes!=null && bytes.size()!=0)
					fileoutputstream.write(bytes.toByteArray());
				fileoutputstream.write(stBuffer.toString().getBytes());
				fileoutputstream.close();				
			}
			bnStatus=true;
		}
		catch (Exception e)
		{
			bnStatus=false;
			e.printStackTrace(System.out);
		}
		return bnStatus; 
	}
	
	public void CallUrl(List urlList,Properties prop) 
	{
		for(int index=0;index<urlList.size();index++)
		{
			String params =  prop.getProperty((String) urlList.get(index));
			
			if(params!=null && !params.equals(""))
				callResource(params);
			else
				System.out.println("Invalid Import Type or This Import Type is Not having Wrapper");
		}
	}
	
	
	public static String callResource(String params)
	{
		String responseText = null;
		String targetUrl = "";
		int response = 0;
		String respMsg = "";
		String msg = "";
		String temp = "";
		HttpURLConnection conn = null;
		URL url = null;
		InputStream is = null;
		BufferedReader in = null;
		
		try
		{
			System.out.println("Initializing URL at..."+new Date());			
			targetUrl = params!=null?params:"";
			System.out.println("targetUrl is : "+targetUrl);
			if(!"".equals(targetUrl.trim()))
			{
				url = new URL(targetUrl);
				conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("POST");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				System.out.println("Sending request at..."+new Date());
				conn.connect();
				response = conn.getResponseCode();
			//	System.out.println("response---->"+response);
				if(response == 200)
					respMsg = "SUCCESS";
				else
					respMsg = "FAILURE";
				System.out.println("Connection : "+respMsg+" at "+new Date());
				is = conn.getInputStream();
				in = new BufferedReader(new InputStreamReader(is));
				
				while((temp=in.readLine())!=null)
				{
					msg += temp;
				}
				in.close();
				is.close();
				System.out.println("Response recieved ---> "+msg+" <-->at "+new Date());
				conn.disconnect();
				conn = null;
			}
			else
			{
				System.out.println("ERROR:--> Invalid URL");
			}
		}
		catch (Exception e)
		{
			System.out.println("Error in CallUrl method-->"+e);
			e.printStackTrace();
		}
		return msg;
	}
	
	private String validateFileName(String importType, String FileName,int i) 
	{
		String tempFileName = stPath+importType+file_seperator+FileName;
		String changedFileName = FileName;
		System.out.println(tempFileName);
		if((new File(tempFileName+FILE_TYPE)).exists()) 
		{
			changedFileName = FileName+"_"+i;
			validateFileName(importType,changedFileName,i++);
		}
		return changedFileName;
	}
	
	public Hashtable getCatCodeValueDesc(int oid,String cid,String codSetId,DatabaseConnection dbConn)
	{
   	Hashtable arr = new Hashtable();
   	ResultSet dataResultSet = null;
   	try
		{
   		System.out.println("in codeval table before");
   	      String query="select CODE_VALUE,CODE_DESCRIPTION from code_value where owner_id = "+oid+" and client_id = '"+cid+"' and " +
   	      							" codeset_id = '"+codSetId + "'";
   	      selectPrepareStmt = dbConn.getConnection().prepareStatement(query);
   	      System.out.println(selectPrepareStmt);
   	      dataResultSet = selectPrepareStmt.executeQuery();
   	      System.out.println("in codeval table before");
   	      while (dataResultSet.next())
   	      {
   	    	  if(dataResultSet.getString("CODE_DESCRIPTION")!=null)
   	    		  arr.put(dataResultSet.getString("CODE_VALUE"), dataResultSet.getString("CODE_DESCRIPTION")); 
   	      }
   	      dataResultSet.close();
   	      selectPrepareStmt.close();
   	     
   	      
   	      System.out.println("in codeval table after");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return arr;
	}
	
	
}