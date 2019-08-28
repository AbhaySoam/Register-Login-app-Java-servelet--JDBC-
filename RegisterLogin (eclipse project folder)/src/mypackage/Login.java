package mypackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userpackage.User;

public class Login extends HttpServlet{
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String userName = req.getParameter("userName");
		String userPassword = req.getParameter("userPassword");
		
		
		try {
			User user = new User(userName, userPassword);
			
			ResultSet rs = user.findUser();
			
			if(rs.next()) {
				out.println("welcome user   ");
				out.println(rs.getString("username"));
				out.println(rs.getString("userpassword"));
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
