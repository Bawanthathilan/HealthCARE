package com.doctor.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DoctorDBConnection {
    Connection con = null;

    public DoctorDBConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        return con;
    }
}
