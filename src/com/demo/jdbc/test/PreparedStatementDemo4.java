package com.demo.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 使用PreparedStatement屏蔽使用Statement对象SQL注入的问题
 * @author Administrator
 *
 */
public class PreparedStatementDemo4 {
	private final static String URL = "jdbc:mysql://localhost:3306/testdb?useSSL=true&useUnicode=true";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "123";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pst = null;
		
		ResultSet rs = null;
		
		//String account = "admin'#";
		String account = "admin";
		String password = "1234";
		
		try {
			
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			// 定义登录的SQL语句
			String logingSQL = "select login_id from t_login "
					+ "where login_account=? and login_pass=?";
			
			pst = conn.prepareStatement(logingSQL);
			
			// 设置占位符？对应值
			pst.setString(1, account);
			pst.setString(2, password);
			
			System.out.println(logingSQL);
			
			rs = pst.executeQuery();
			
			System.out.println(rs != null && rs.next()? "登录成功" : "登录失败");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放操作资源
				rs.close();
				pst.close();
				if(!conn.isClosed()) {
					conn.close();
					conn = null;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
