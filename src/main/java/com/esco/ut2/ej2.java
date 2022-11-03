package com.esco.ut2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ej2 {

	public static void main(String[] args) {

		String sqlStatement = "Select * from doujinshi where code=1 or 1=1";

		try (Connection grande = ConnectionPool.getInstance().getConnection();
			Statement stmt = grande.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE)) {

			ResultSet resultSet = stmt.executeQuery(sqlStatement);

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
