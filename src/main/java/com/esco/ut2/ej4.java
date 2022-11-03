package com.esco.ut2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ej4 {

	public static void main(String[] args) {

		String sql = "insert into doujinshi(code, name, pages, prize) values (default, Sqlmovidas, 34, 54)" +
				"insert into doujinshi(code, name, pages, prize) values (default, Sqlmovidas, 34, 54)" +
				"insert into doujinshi(code, name, pages, prize) values (default, Sqlmovidas, 34, 54)" +
				"insert into doujinshi(code, name, pages, prize) values (default, Sqlmovidas, 34, 54)" +
				"insert into doujinshi(code, name, pages, prize) values (default, Sqlmovidas, 34, 54)";

		String sqlFallo = "insert into doujinshi(code, name, pages, prize) values (default, Sqlmovidas, 34, 54)" +
				"insert into doujinshi(code, name, pages, prize) values (default, Sqlmovidas, 34, 54)" +
				"insert into doujinshi(code, name, pages, prize) values (default, Sqlmovidas, 34, 54)" +
				"insert into doujinshi(code, name, pages, prize) values (default, Sqlmovidas, wade, 54)" +	// Esta línea dará error, lo que provocará un rollback
				"insert into doujinshi(code, name, pages, prize) values (default, Sqlmovidas, 34, 54)";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			conn = ConnectionPool.getInstance().getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			//stmt.setInt(1, 23);

			stmt.executeUpdate();

			rs = stmt.getGeneratedKeys();
			rs.next();
			stmt.close();

			rs.first();

			stmt = conn.prepareStatement(sqlFallo);

			//stmt.setInt(1, 43);

			stmt.executeUpdate();

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
				System.out.println("Ha ocurrido un error, iniciando rollback");

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {

			try {
				conn.setAutoCommit(true);
				ConnectionPool.getInstance().closeConnection(conn);
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
