package com.practice.jms;

import javax.jms.*;
import javax.naming.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class MyQueueSender
{
	public static final String QUEUE_FACTORY = "QueueConnectionFactory";
	public static final String QUEUE		 = "Sample1";
	boolean sendStatus = false;


	
	/**
	 * @param txtmessage
	 * @param importType
	 * @param fileName
	 * @return boolean
	 * @throws NamingException
	 * @throws JMSException
	 * 
	 */
	public boolean sendQueueMsg(String txtmessage,String importType,String fileName,Properties prop) throws NamingException, JMSException
	{
			//Properties prop = new Properties();
			System.out.println("Entering into sendQueueMsg-->");
			
			prop.put(Context.INITIAL_CONTEXT_FACTORY,prop.get("CONTEXT_FACTORY"));
			prop.put(Context.PROVIDER_URL,prop.get("PROVIDER_URL"));

			if (prop.getProperty("SECURITY_PRINCIPAL") != null && prop.getProperty("SECURITY_CREDENTIALS") != null) 
			{
				prop.put(Context.SECURITY_PRINCIPAL,prop.get("SECURITY_PRINCIPAL"));
				prop.put(Context.SECURITY_CREDENTIALS,prop.get("SECURITY_CREDENTIALS"));
			}
			
			Context ctx; 
		try
		{
			ctx = new InitialContext(prop);
			Queue myQueue = (Queue)ctx.lookup(QUEUE);
			QueueConnectionFactory QCF = (QueueConnectionFactory)ctx.lookup(QUEUE_FACTORY);
			QueueConnection qConn= null;
			
			System.out.println("Before Getiing connection-->"+prop.get(Context.PROVIDER_URL));
			if (prop.getProperty("UserName") != null && prop.getProperty("Password") != null && prop.getProperty("UserName") != "") 
			{
				qConn = QCF.createQueueConnection(prop.getProperty("UserName"), prop.getProperty("Password"));
			}
			else
			{
				qConn = QCF.createQueueConnection();
			}
			
			qConn.setClientID("12345");

			QueueSession qSession = qConn.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);

			QueueSender qSend = qSession.createSender(myQueue);

			qConn.start();

			TextMessage msg = qSession.createTextMessage();
				msg.setText(txtmessage);
				msg.setStringProperty("FILE_NAME",fileName);
				msg.setStringProperty("IMPORT_TYPE",importType);
				qSend.send(msg,DeliveryMode.PERSISTENT,Message.DEFAULT_PRIORITY,0);
				System.out.println(" Created Message..Publishing on Queue->"+msg.getText());
				System.out.println(" in send method->"+msg.getJMSMessageID());
			//qSend.send(qSession.createMessage());
			//Close Connection
				sendStatus = true;
			qConn.close();
			ctx.close();
			sendStatus=true;
		}
		catch(Exception e)
		{
			System.out.println("In send method -->");
			e.printStackTrace(System.out);
			sendStatus=false;
		}
		return sendStatus;
	}
	
	/**
	 *	To send Queue message to Messaging server
	 * @param fileInput
	 * @throws NamingException
	 * @throws JMSException
	 * @return void
	 *
	 */
	public void sendQueueMsg(File fileInput) throws NamingException, JMSException
	{
			Properties prop = new Properties();

			prop.put(Context.INITIAL_CONTEXT_FACTORY,"com.sonicsw.jndi.mfcontext.MFContextFactory");
			prop.put(Context.PROVIDER_URL,"tcp://10.0.2.144:2506");
			Context ctx; 
		try
		{
			ctx = new InitialContext(prop);
			Queue myQueue = (Queue)ctx.lookup(QUEUE);
			QueueConnectionFactory QCF = (QueueConnectionFactory)ctx.lookup(QUEUE_FACTORY);
			
			QueueConnection qConn = QCF.createQueueConnection();
			qConn.setClientID("12345");

			QueueSession qSession = qConn.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);

			QueueSender qSend = qSession.createSender(myQueue);

			qConn.start();

			byte[] buf = new byte[new Long(fileInput.length()).intValue()+1];
	        BytesMessage message = qSession.createBytesMessage();
	        
	       // message.writeBytes(buf, 0, buf.length);
	        
	        InputStream in=new FileInputStream(fileInput);
	        
		        while(in.read()!=-1)
		        {
		        	in.read(buf);
		        }
		        
		        message .writeBytes(buf);
		        message.setStringProperty("Filename","userload_xml");
				qSend.send(message,DeliveryMode.PERSISTENT,Message.DEFAULT_PRIORITY,100000);
				//System.out.println(" Created Message..Publishing on Queue->"+message.getText());
				System.out.println(" in send method->"+message.getJMSMessageID());
			//qSend.send(qSession.createMessage());
			//Close Connection
			qConn.close();
			ctx.close();
		}
		catch(Exception e)
		{
			System.out.println("In send method -->");
			e.printStackTrace(System.out);
		}
	}
}


