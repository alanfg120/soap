package org.example.soap.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.example.soap.config.EnvConfig;

public class Conexion {



    public Conexion() {
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            // Cargar el driver
            EnvConfig envs = EnvConfig.getInstance();
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(envs.getUrlMysql(),envs.getUserMysql(),envs.getPasswordMysql());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error en la conexión con la base de datos: " + e.getMessage(), e);
        }
        return con;
    }
}