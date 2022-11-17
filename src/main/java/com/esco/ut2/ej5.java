// Utiliza al menos una función y un procedimiento

package com.esco.ut2;

import java.sql.*;

public class ej5 {
	public static void main(String[] args) {

		String nombre = "Caracol";
		String function = "{call name_from_precio (5)}";
		String procedure = "{call insert_data (?, ?, ?) values (\"Ramón dice hola\", 34, 54)}";

		try (Connection conn = ConnectionPool.getInstance().getConnection()) {

			CallableStatement statement = conn.prepareCall(function);
			statement.execute();

			//statement = conn.prepareCall(procedure);
			//statement.execute();	No se admiten procedures

			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
