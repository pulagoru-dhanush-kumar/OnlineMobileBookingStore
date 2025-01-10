package com.mobile.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import jakarta.servlet.http.HttpServlet;

public class Myorders extends HttpServlet {
	
	static String[] phonedetails(int id) throws ClassNotFoundException, SQLException, IOException
	{
		Connection con=Jdbc.jdbcconnection();
		String arr[]=new String[3];
		Statement st=con.createStatement();
		ResultSet rs1=st.executeQuery("select * from phone where phoneid="+id);
		rs1.next();
		rs1.getString(4);
		arr[0]=rs1.getString(4);//productspec
		arr[1]=rs1.getString(5);//imageid
		return arr;
	}
	
	public static ArrayList<String[]> getorders(String email) throws ClassNotFoundException, SQLException, IOException
	{
	ArrayList<String[]> a=new ArrayList<String[]>();
	Connection con=Jdbc.jdbcconnection();
	String query="select * from orders where email=?";
	PreparedStatement st=con.prepareStatement(query);
	st.setString(1, email);
	ResultSet rs=st.executeQuery();
	while(rs.next())
	{
		
		String arr[]=new String[8];
		int id=rs.getInt(2);
		String phoneid=Integer.toString(id);
		arr[0]=phoneid;//phoneid
		String name=Datafetcher.getmobilename(id);
		arr[1]=name;//phonename
		int price=rs.getInt(5);
		String totalprice=Integer.toString(price);
		int qnt=rs.getInt(4);
		String quantity=Integer.toString(qnt);
		arr[2]=totalprice;
		arr[3]=quantity;
		arr[4]=rs.getString(6);//status
	
		arr[7]=rs.getString(10);//time
		
		String phone[]=phonedetails(id);
		arr[5]=phone[0];//productspec
		arr[6]=phone[1];//imageid
		
		System.out.println(Arrays.toString(arr));
		a.add(arr);
		
		
	}
//	System.out.println(a);
//	System.out.println(a.size());
	return a; 
	
	}
	
	
}
