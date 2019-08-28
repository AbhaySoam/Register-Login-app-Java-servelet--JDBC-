package mypackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userpackage.User;

public class Delete extends HttpServlet{
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String userName = req.getParameter("userName");
		String userPassword = req.getParameter("userPassword");
		
		
		try {
			User user = new User(userName, userPassword);
			
			int rows = user.deleteUser();
			if (rows > 0)
				out.println("user successfully deleted.");
			else
				out.println("no user found");
			user.closeConn();
			
		}catch(ClassNotFoundException e) {
			out.println(e);
		}catch(SQLException e) {
			out.println(e);
		}
	}

}
