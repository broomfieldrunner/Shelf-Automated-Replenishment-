package com.smartbuy.ocb.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionUtil {
	
	public static Connection getConnection()
	{
		String connectionURL = "jdbc:mysql://localhost:3306/retail";
		String user = "root";
		String pass = "kirubel%12";
		 
		Connection conn = null;
		 
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connectionURL, user, pass);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}
		
		return conn;
		
	}

	
	public static void closeConnection(Connection conn){
		
		if (conn != null) 
		{
	        try 
	        { 
	        	conn.close(); 
	        }
	        catch(Exception e)
	        {
	        	System.out.println(e);
	        }
	        
	    }
		
	}
}
