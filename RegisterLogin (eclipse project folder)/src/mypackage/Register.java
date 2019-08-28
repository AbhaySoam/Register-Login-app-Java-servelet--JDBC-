package mypackage;

import userpackage.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet{
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String userName = req.getParameter("userName");
		String userPassword = req.getParameter("userPassword");
		
		
		try {
			User user = new User(userName, userPassword);
			int rows = user.addUser();
			out.println("you are successfully registered.");
			user.closeConn();
		}catch(ClassNotFoundException e) {
			out.println(e);
		}catch(SQLException e) {
			out.println(e);
		}
	}

}
