package com.micro.profession.jdbc.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static String DB_URL = "jdbc:mysql://localhost/cloud_study?useSSL=true";
	static final String USER = "root";
	static final String PASSWORD = "root";
	
	public static User login(String userName, String password) 
			throws ClassNotFoundException {
		 Connection conn = null;
		 PreparedStatement ptmt = null;
		 ResultSet rs = null;
		 User user = null;
		 
		 // 1. 装载驱动程序
		 Class.forName(JDBC_DRIVER);
		 // 2. 建立数据库连接
		 try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			// 3. 执行SQL语句
			ptmt = conn.prepareStatement("select * from user where userName = ? and password = ?");
			ptmt.setString(1, userName);
			ptmt.setString(2, password);
			rs = ptmt.executeQuery();
			// 4. 获取执行结果
			while(rs.next()) {
				user = new User();
				user.setUserName(rs.getString("userName"));
				user.setSex(rs.getBoolean("sex"));
			}
		} catch (SQLException e) {
			// 异常处理
			e.printStackTrace();
		} finally {
			//5. 清理环境
			try {
				if(conn != null)
					conn.close();
				if(ptmt != null)
					ptmt.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(login("ZhangSi", "123456")!= null);
	}

}
