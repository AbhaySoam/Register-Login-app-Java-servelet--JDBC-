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

public class FindAll extends HttpServlet{
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();		
		
		try {
			User user = new User();
			
			ResultSet rs = user.findAll();
			
			while(rs.next())
				out.println(rs.getString("username"));

			user.closeConn();
			
		}catch(ClassNotFoundException e) {
			out.println(e);
		}catch(SQLException e) {
			out.println(e);
		}
	}

}
