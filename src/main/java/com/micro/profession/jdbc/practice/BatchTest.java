package com.micro.profession.jdbc.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class BatchTest {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static String DB_URL = "jdbc:mysql://localhost/cloud_study";
	static final String USER = "root";
	static final String PASSWORD = "root";
	
	public static void insertUsers(Set<String> users) 
			throws ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		
		//1. 加载数据库驱动
		Class.forName(JDBC_DRIVER);
		//2. 建立数据库连接
		try {
			// 获得数据库连接
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			//3. 执行SQL语句
			stmt = conn.createStatement();
			for(String user : users) {
				stmt.addBatch("insert into user(userName) values ('" + user
						+ "')");
			}
			stmt.executeBatch();
			stmt.clearBatch();
		} catch (SQLException e) {
			// 异常处理
			e.printStackTrace();
		} finally {
			//5. 清理环境
			try {
				if(conn != null)
					conn.close();
				if(stmt != null)
					stmt.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {
		Set<String> users = new HashSet<String>();
		users.add("GuoYi");
		users.add("ZhangSi");
		users.add("LiSan");
		insertUsers(users);
	}

}
