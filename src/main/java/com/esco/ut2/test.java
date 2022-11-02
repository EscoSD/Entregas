package com.esco.ut2;

import java.sql.Connection;
import java.sql.DriverManager;

public class test {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/javierEgido";
        try (Connection conn = DriverManager.getConnection(url, "root", "root")) {

            System.out.println("asd "+ conn.getSchema());

        } catch (Exception ignored) {

        }
    }
}
