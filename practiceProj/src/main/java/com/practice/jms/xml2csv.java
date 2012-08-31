package com.practice.jms;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.sax.*;
import java.io.*;

public class xml2csv 
{

	public xml2csv()
	{}
	/**
	 * @param xmlfilname
	 * @param xslfilename
	 * @param csvfilename
	 * @return boolean
	 * @throws TransformerException
	 * @throws TransformerConfigurationException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * 
	 */
	public boolean convertToXml(String xmlfilname, String xslfilename, String csvfilename) throws TransformerException, TransformerConfigurationException, FileNotFoundException, IOException
	{
		boolean bnStatus=false;
		FileOutputStream fs =null;
		javax.xml.transform.Transformer transformer = null;
		System.setProperty("javax.xml.transform.TransformerFactory", "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
		try
		{
			try 
			{
				javax.xml.transform.TransformerFactory tFactory = javax.xml.transform.TransformerFactory.newInstance();
				transformer = tFactory.newTransformer(new javax.xml.transform.stream.StreamSource(xslfilename));
				transformer.transform(new javax.xml.transform.stream.StreamSource(xmlfilname), new javax.xml.transform.stream.StreamResult(fs = new FileOutputStream(csvfilename)));
				bnStatus=true;
			}
			catch (javax.xml.transform.TransformerConfigurationException tce) 
			{
				tce.printStackTrace(System.out);
			}
			catch (javax.xml.transform.TransformerException tfe) 
			{
				tfe.printStackTrace(System.out);
			}
			catch (javax.xml.transform.TransformerFactoryConfigurationError tfc )
			{
				tfc.printStackTrace();
			}
		}
		catch (Exception e)
		{
			bnStatus=false;
			e.printStackTrace(System.out);
		}
		finally
		{
			if(fs!=null)
			{
				fs.flush();
				fs.close();
			}
			return bnStatus;
		}
	}
}