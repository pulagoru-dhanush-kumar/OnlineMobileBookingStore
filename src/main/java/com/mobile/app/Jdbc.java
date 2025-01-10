package com.mobile.app;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Jdbc {
	static Connection jdbcconnection() throws ClassNotFoundException, SQLException, IOException
	{
		Connection con=null;
		try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 Properties pr=new Properties();
	     FileReader fr=new FileReader("C:\\Users\\dhanushkumar\\Downloads\\UserDetails.txt");
	     pr.load(fr);
	     String url=pr.getProperty("url");
	     String pass=pr.getProperty("password");
	     String uname=pr.getProperty("username");
	     con= DriverManager.getConnection(url,uname,pass);
	     System.out.println("Connection created sucessfully");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return con;
		
	}

}
