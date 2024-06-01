package com.servlet.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.servlet.connection.DbConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uemail = request.getParameter("username");
		String upwd = request.getParameter("password");
		HttpSession session = request.getSession();
		RequestDispatcher rd =  null;
		try(Connection con = DbConnection.getConnection()){
			
			PreparedStatement prepared = con.prepareStatement("select * from Users where uemail=? and upwd=? ");
			prepared.setString(1, uemail);
			prepared.setString(2, upwd);
			
			ResultSet rs = prepared.executeQuery();
			if(rs.next()) {
				session.setAttribute("name", rs.getString("uname"));
				rd = request.getRequestDispatcher("index.jsp");
			}else {
				request.setAttribute("status", "failed");
				rd = request.getRequestDispatcher("login.jsp");
			}
			
			rd.forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
