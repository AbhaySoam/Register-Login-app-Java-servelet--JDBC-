package userpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
	private String userName;
	private String userPassword;
	
	private Connection conn;
	private PreparedStatement pst;
	private String sql;
	
	public User(String userName, String userPassword) throws ClassNotFoundException, SQLException{
		this.userName = userName;
		this.userPassword = userPassword;
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "oracle", "oracle");
		System.out.println("connect to :" + conn);
	}
	
	public User() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "oracle", "oracle");
		System.out.println("connect to :" + conn);
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword  = userPassword;
	}
	
	public int addUser() throws SQLException{
		sql = "insert into idtable values(?, ?)";
		pst = conn.prepareStatement(sql);
		pst.setString(1, userName);
		pst.setString(2, userPassword);
		int rows = pst.executeUpdate();
		return rows;
	}
	
	public ResultSet findUser() throws SQLException{
		PreparedStatement pst = conn.prepareStatement("select * from idtable where username = ? and userpassword = ?");
		pst.setString(1, userName);
		pst.setString(2, userPassword);
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	
	public int deleteUser() throws SQLException{
		PreparedStatement pst = conn.prepareStatement("delete from idtable where username = ? and userpassword = ?");
		pst.setString(1, userName);
		pst.setString(2, userPassword);
		int rows = pst.executeUpdate();
		
		return rows;
	}
	
	public int modifyUser(String newPassword) throws SQLException{
		PreparedStatement pst = conn.prepareStatement("update idtable set userpassword = ? where username = ? and userpassword = ?");
		pst.setString(2, userName);
		pst.setString(3, userPassword);
		pst.setString(1, newPassword);
		int rows = pst.executeUpdate();
		
		return rows;
	}
	
	public ResultSet findAll() throws SQLException{
		PreparedStatement pst = conn.prepareStatement("select username from idtable");
		ResultSet rs = pst.executeQuery();
		
		return rs;
	}
	
	public void closeConn() throws SQLException{
		conn.close();
	}
}
