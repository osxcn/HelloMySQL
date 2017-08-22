package com.micro.profession.jdbc.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBPoolDbcpImpl extends Thread {
	
	public static BasicDataSource ds = null;
	
	public final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public final static String USER_NAME = "root";
	public final static String PASSWORD = "root";
	public final static String DB_URL = "jdbc:mysql://localhost/cloud_study";
	
	public static void dbpoolInit() {
		ds = new BasicDataSource();
		ds.setUrl(DB_URL);
		ds.setDriverClassName(DRIVER_NAME);
		ds.setUsername(USER_NAME);
		ds.setPassword(PASSWORD);
		ds.setMaxTotal(2);
	}
	
	public void dbPoolTest() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select userName from user");
			while(rs.next()) {
				System.out.println("hello " + rs.getString("userName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(stmt != null) stmt.close();
				if(rs != null) rs.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}
	
	public void jdbcTest() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from user");
			while(rs.next()) {
				System.out.println(rs.getString("userName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			// ignore
		} finally {
			try {
				if(conn != null)
					conn.close();
				if(stmt != null)
					stmt.close();
				if(rs != null)
					rs.close();
			} catch (SQLException e) {
				// ignore
			}
		}
	}
	
	public void run() {
		long start = System.currentTimeMillis();
		while (System.currentTimeMillis() - start < 10000) {
//			jdbcTest();	// 同时有10个线程进行访问
			dbPoolTest();	// 同时有2个线程进行访问
		}
	}

	public static void main(String[] args) {
		dbpoolInit();
		for (int i=0; i<10; i++) {
			new DBPoolDbcpImpl().start();
		}
	}

}
