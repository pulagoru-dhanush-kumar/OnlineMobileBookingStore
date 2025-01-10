package com.mobile.app;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Servlet implementation class Datafetcher
 */
public class Datafetcher {
	
	public static int itemprice(int phoneid) throws ClassNotFoundException, SQLException, IOException
	{
		Connection con=Jdbc.jdbcconnection();
		Statement st=con.createStatement();
		String query="select * from phone where phoneid="+Integer.toString(phoneid);
		ResultSet rs=st.executeQuery(query);
		rs.next();
		String s=rs.getString(3);
		s=s.replace(",", "");
		int price=Integer.parseInt(s); 
		return price;
	}
	public static int totalPrice(int phoneid,int quantity) throws ClassNotFoundException, SQLException, IOException
	{
		return itemprice(phoneid)*quantity;
	}
	public static String getmobilename(int phoneid) throws ClassNotFoundException, SQLException, IOException
	{
		Connection con=Jdbc.jdbcconnection();
		Statement st=con.createStatement();
		String query="select * from phone where phoneid="+Integer.toString(phoneid);
		ResultSet rs=st.executeQuery(query);
		rs.next();
		String s=rs.getString(2);
		return s;
	}
	

	
	
}
