package com.servlet.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.servlet.connection.DbConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String uname = request.getParameter("name");
		String uemail = request.getParameter("email");
		String upwd = request.getParameter("pass");
		String umobile = request.getParameter("contact");
		
		RequestDispatcher dispatcher = null;
		
		
		
		 try (Connection con = DbConnection.getConnection())
				  {
			 
			 PreparedStatement prepared = con.prepareStatement("insert into Users (ID,uname,uemail,upwd,umobile) values (USERS_SEQUENCE.NEXTVAL,?,?,?,?)");
			 
			 prepared.setString(1, uname);
			 prepared.setString(2, uemail);
			 prepared.setString(3, upwd);
			 prepared.setString(4, umobile);
			 
			 int rowNum = prepared.executeUpdate();
			 dispatcher = request.getRequestDispatcher("registration.jsp");
			 
			 if(rowNum > 0) {
				 request.setAttribute("status", "success");
			 }else {
				 request.setAttribute("status", "failed");
			 }
			 
				dispatcher.forward(request, response);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	}

}
