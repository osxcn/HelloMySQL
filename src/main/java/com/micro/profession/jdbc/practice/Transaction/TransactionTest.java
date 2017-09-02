package com.micro.profession.jdbc.practice.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class TransactionTest {
	
	public static BasicDataSource ds = null;
	
	static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	static String DB_URL = "jdbc:mysql://localhost/cloud_study?useSSL=true";
	static final String USER_NAME = "root";
	static final String PASSWORD = "root";
	
	public static void transactionInit() {
		ds = new BasicDataSource();
		ds.setUrl(DB_URL);
		ds.setDriverClassName(DRIVER_NAME);
		ds.setUsername(USER_NAME);
		ds.setPassword(PASSWORD);
	}
	
	public static void transferAccount() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement ptmt = null;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			ptmt = conn.prepareStatement("update user set account = ? where userName = ?");
			ptmt.setInt(1, 0);
			ptmt.setString(2, "ZhangSan");
			ptmt.execute();
			ptmt.setInt(1, 100);
			ptmt.setString(2, "LiSi");
			ptmt.execute();
			conn.commit();
		} catch (SQLException e) {
			if(conn != null)
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		} finally {
			try {
				if(conn != null) conn.close();
				if(ptmt != null) ptmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {
		transactionInit();
		new TransactionTest().transferAccount();
	}

}
