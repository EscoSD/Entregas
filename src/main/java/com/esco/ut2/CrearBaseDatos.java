package com.esco.ut2;

import java.sql.*;

public class CrearBaseDatos {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/postgres";

        try(Connection conn = DriverManager.getConnection(url, "root", "root");
            Statement stmt = conn.createStatement()) {

            String sql = "CREATE DATABASE javieregido";
            stmt.executeUpdate(sql);
            System.out.println("Base de datos creada.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        createTable();

    }

    public static void createTable() {

        String url = "jdbc:postgresql://localhost:5432/javieregido";

        String sql = "Create Table doujinshi(code serial primary key, name varchar, pages integer, prize numeric)";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Se ha creado la tabla.");

            insertData(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertData(Connection conn) throws SQLException{

        //String sentenciaSql = "INSERT INTO Doujinshi(code, name, pages, prize) VALUES (?, ?, ?, ?)";
        String sentenciaSql = "INSERT INTO doujinshi (code, name, pages, prize) VALUES (default, ?, ? ,?)";
        PreparedStatement sentencia;

        sentencia = conn.prepareStatement(sentenciaSql);
        //sentencia.setInt(1, 4);
        sentencia.setString(1, "Emilio va al parque");
        sentencia.setInt(2, 3);
        sentencia.setDouble(3, 4.34);
        sentencia.executeUpdate();

        sentencia = conn.prepareStatement(sentenciaSql);
        //sentencia.setInt(1, 2);
        sentencia.setString(1, "El Vise come Kebab");
        sentencia.setInt(2, 65);
        sentencia.setDouble(3, 22.34);
        sentencia.executeUpdate();

        sentencia = conn.prepareStatement(sentenciaSql);
        //sentencia.setInt(1, 3);
        sentencia.setString(1, "AndroidStudio es una movida");
        sentencia.setInt(2, 23);
        sentencia.setDouble(3, 42);
        sentencia.executeUpdate();

        sentencia.close();
    }
}
//first, next, last, movetoinsertrow, absolute