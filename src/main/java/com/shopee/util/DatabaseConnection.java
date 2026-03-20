package com.shopee.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static String DRIVE = "com.mysql.cj.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/shopee_db";
    private static String USER = "root";
    private static String PASSWORD = "root123";

    public static Connection getConnection(){
        try {
            Class.forName(DRIVE);
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Drive nao encontrado"+ e.getMessage());
            return null;
        } catch (SQLException sql) {
            System.err.println("error no banco de dados"+sql.getMessage());
            return null;
        }
    }

}