package net.javaguides.login.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.javaguides.login.model.LoginBean;

public class LoginDao {
	
	private String dbUrl = "jdbc:mysql://localhost:3306/employees2?serverTimezone=UTC";
	private String dbUname = "root";
	private String dbPassword = "**mysqleya123";
	private String dbDriver = "com.mysql.cj.jdbc.Driver";
	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	
	
	public Connection getConnection(){
		
		Connection con = null;
		 try { 
			 con = DriverManager.getConnection(dbUrl,dbUname,dbPassword);
              System.out.println("Perfectly perfect eya connected to ur database");
			
		}catch(SQLException e){
			System.out.println("Ooops eya not connected to ur database !!!");
			 e.printStackTrace();
		}
		return con;
	}


	public boolean validate(LoginBean loginBean) {
		
		loadDriver(dbDriver);
		
		boolean status=false;
		String sql="SELECT * FROM employee2 WHERE username=? and password=?";		
		Connection con=getConnection();
		try {
		PreparedStatement  ps = con.prepareStatement(sql);
		ps.setString(1, loginBean.getUsername());
		ps.setString(2, loginBean.getPassword());
		
		ResultSet rs=ps.executeQuery();
		//method next to move the cursor to the next row
		status = rs.next();
		//return true if the authentification is done
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return status;
	}
}
