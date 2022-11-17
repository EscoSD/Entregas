// Utiliza al menos una función y un procedimiento

package com.esco.ut2;

import java.sql.*;

public class ej5 {
	public static void main(String[] args) {

		String function = "{? = call name_from_precio(1)}";
		String procedure = "call insert_data('Salmonete', 34, 54)";

		try (Connection conn = ConnectionPool.getInstance().getConnection()) {

			CallableStatement statement = conn.prepareCall(function);
			statement.registerOutParameter(1, Types.VARCHAR);
			statement.execute();

			System.out.println(statement.getString(1));


			statement = conn.prepareCall(procedure);
			statement.execute();

			System.out.println("Se han insertado los datos.");

			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
