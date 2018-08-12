package com.demo.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * StatementSQL注入演示
 * @author Administrator
 *
 */
public class StatemetDemo3 {
	
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
		Statement st = null;
		ResultSet rs = null;
		
		//String account = "admin'#";
		String account = "admin'-- ";
		String password = "1234343e44";
		
		try {
			
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			st = conn.createStatement();
			
			// 定义登录的SQL语句
			String logingSQL = "select login_id from t_login "
					+ "where login_account='"+account+"' and login_pass='"+password+"'";
			System.out.println(logingSQL);
			
			rs = st.executeQuery(logingSQL);
			
			System.out.println(rs != null && rs.next()? "登录成功" : "登录失败");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 释放操作资源
				rs.close();
				st.close();
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
