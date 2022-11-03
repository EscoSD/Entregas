package com.esco.ut2;

import java.sql.*;

public class ej3 {

	public static void main(String[] args) {

		String sqlStatement = "Select * from doujinshi";

		try (Connection conn = ConnectionPool.getInstance().getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sqlStatement, ResultSet.TYPE_SCROLL_SENSITIVE,
					 ResultSet.CONCUR_UPDATABLE)) {

			ResultSet resultSet = stmt.executeQuery();

			resultSet.first();
			System.out.println(resultSet.getString(1));
			System.out.println(resultSet.getString(2));
			System.out.println(resultSet.getString(3));
			System.out.println(resultSet.getString(4));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
