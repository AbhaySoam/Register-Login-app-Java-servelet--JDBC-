package mypackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userpackage.User;

public class Modify extends HttpServlet{
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String userName = req.getParameter("userName");
		String userPassword = req.getParameter("userPassword");
		String newPassword = req.getParameter("newPassword");
		
		
		try {
			User user = new User(userName, userPassword);
			
			int rows = user.modifyUser(newPassword);
			
			if(rows != 0) {
				user.setUserPassword(newPassword);
				out.println("password changed.");
			}else {
				out.println("userName or password is wrong.");
			}
			user.closeConn();
			
		}catch(ClassNotFoundException e) {
			out.println(e);
		}catch(SQLException e) {
			out.println(e);
		}
	}

}
