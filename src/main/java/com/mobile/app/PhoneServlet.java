package com.mobile.app;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServlet;
public class PhoneServlet extends HttpServlet{
	public static String[] phonedata(int i) throws ClassNotFoundException, SQLException, IOException
	{
		Connection con=Jdbc.jdbcconnection();
		PreparedStatement st=con.prepareStatement("select * from phone where phoneid=?");
		st.setInt(1, i);
		ResultSet rs=st.executeQuery();
		rs.next();
		String arr[]=new String[5];
		arr[0]=rs.getString(2);
		arr[1]=rs.getString(3);
		arr[2]=rs.getString(4);
		arr[3]=rs.getString(5);
		arr[4]=Integer.toString(rs.getInt(1));
		return arr;
	}
}
