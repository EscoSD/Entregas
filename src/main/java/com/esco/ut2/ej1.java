package com.esco.ut2;

import java.sql.*;

public class ej1 {

    public static void main(String[] args) {

        String sql = "select code, name, pages, prize from doujinshi";

        try (Connection conn = ConnectionPool.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                     ResultSet.CONCUR_UPDATABLE)) {

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getInt(3));
                System.out.println(resultSet.getDouble(4)+"\n");
            }

            resultSet.first();
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getInt(3));
            System.out.println(resultSet.getDouble(4)+"\n");

            resultSet.absolute(2);
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getInt(3));
            System.out.println(resultSet.getDouble(4)+"\n");

            resultSet.moveToInsertRow();
            resultSet.updateString(2, "Ramón dice hola");
            resultSet.updateInt(3, 32);
            resultSet.updateDouble(4, 43);
            resultSet.insertRow();

            resultSet.last();
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getInt(3));
            System.out.println(resultSet.getDouble(4)+"\n");

            resultSet.last();
            resultSet.deleteRow();

            resultSet.last();
            System.out.println(resultSet.getInt(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getInt(3));
            System.out.println(resultSet.getDouble(4)+"\n");

            ConnectionPool.getInstance().closeConnection(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
