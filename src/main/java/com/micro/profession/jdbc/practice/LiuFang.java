package com.micro.profession.jdbc.practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LiuFang {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static String DB_URL = "jdbc:mysql://localhost/cloud_study";
	static final String USER = "root";
	static final String PASSWORD = "root";
	static final String FILE_URL = System.getProperty("user.dir") + "/file/write.txt";
	
	public static void helloword() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		
		try {
			// 1. 加载数据库驱动
			Class.forName(JDBC_DRIVER);
			// 2. 获取数据库连接
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			// 3. 创建PreparedStatement对象
			String sql = "select * from user_note";
			ptmt = conn.prepareStatement(sql);
			// 4. 执行SQL语句
			rs = ptmt.executeQuery();
			while(rs.next()) {
				// 5. 获取对象流
				InputStream in = rs.getBinaryStream("blog");
				// 6. 将对象流写入文件
				File file = new File(FILE_URL);
				OutputStream out = null;
				out = new FileOutputStream(file);
				int temp = 0;
				while((temp = in.read()) != -1) { //边读边写
					out.write(temp);
				}
				in.close();
				out.close();
			}
		} catch (SQLException e) {
			// 异常处理
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
				// ignore
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException {
		helloword();
	}

}
