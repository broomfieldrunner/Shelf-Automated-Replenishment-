package com.smartbuy.ocb.main;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.smartbuy.ocb.bo.OcbBO;
import com.smartbuy.ocb.exception.OCBException;

public class OCBMain 
{
	
	private static Logger logger = Logger.getLogger(OCBMain.class);
	
	public static void main(String[] args) 
	{
		final String username = "aidahailu87@gmail.com";
        final String password = "Test$1234";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
		
		//String storeNumber = args[0]; 
		String storeNumber = "501";
		
		// is a starting point
		OcbBO bo = new OcbBO();
		try 
		{
			//assigning store 501 to the store number
			//storeNumber = "501";
			bo.executeGetSkuforStore(storeNumber);
			
		} 
		catch (OCBException e) 
		{
			logger.error("This is final Error Handling " + e.getMessage(), e);
		}
		
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() 
		    {
		        return new PasswordAuthentication(username, password);
		    }
		  });
		
			try {
		
		    Message message = new MimeMessage(session);
		    message.setFrom(new InternetAddress("aidahailu87@gmail.com"));
		    message.setRecipients(Message.RecipientType.TO,
		        InternetAddress.parse("aidahailu2016@gmail.com"));
		    message.setSubject("OCB Completion Notification");
		    message.setText("OCB completed Successfully!");
		      
		    Transport.send(message);
		
		    System.out.println("Email was sent");
		
			} 
			catch (MessagingException e) 
			{
			    throw new RuntimeException(e);
			}

	}

}
