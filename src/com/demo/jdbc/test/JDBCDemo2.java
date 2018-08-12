package com.demo.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 执行DML语句操作(INSERT, DELETE, UPDATE)
 * 
 * @author Administrator
 *
 */
public class JDBCDemo2 {
	private final static String URL = "jdbc:mysql://localhost:3306/testdb?useSSL=true&useUnicode=true";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "123";

	public static void main(String[] args) {
		// add();
		// delete();
		update();

	}

	// 修改数据
	private static void update() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			Statement statement = connection.createStatement();

			String updateSql = "UPDATE `tb_emp` "
					+ "SET `emp_name` = '肖恩', "
					+ "`emp_sex` = '男', "
					+ "`dept_id` = 3, "
					+ "`salary` = 3000.0, "
					+ "`emp_mail` = 'xiaoen@163.com' "
					+ "WHERE `emp_id` = 11203491";

			int row = statement.executeUpdate(updateSql);

			System.out.println(row > 0 ? "修改成功" : "修改失败");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 删除
	private static void delete() {

		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			Statement statement = connection.createStatement();

			String delSql = "delete from tb_emp where emp_name = '李四9999977'";

			int row = statement.executeUpdate(delSql);

			System.out.println(row > 0 ? "删除成功" : "删除失败");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 新增
	private static void add() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			Statement statement = connection.createStatement();

			String insertSql = "insert into tb_emp (emp_name, emp_sex) values ('new emp','女')";

			int row = statement.executeUpdate(insertSql);

			System.out.println(row > 0 ? "添加成功" : "添加失败");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
