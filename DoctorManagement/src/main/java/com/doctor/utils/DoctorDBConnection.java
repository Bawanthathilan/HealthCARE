package com.doctor.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DoctorDBConnection {

    private static Connection con = null;

    public DoctorDBConnection() {

    }

    public Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Doctor", "root", "");
        } catch (ClassNotFoundException e){
            System.out.println("Driver Class Not Found");
        }catch (SQLException e){
            createDatabase();
            createTable();
        }


        System.out.println("Connected to DB");

        return con;
    }


    private static void createDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306","root","");
            Statement s = con.createStatement();
            s.executeUpdate("CREATE DATABASE IF NOT EXISTS Doctor");
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable() {
        String myTableName = "create table regDoctors(doctor_id INT, firstName varchar(40),lastName varchar(50),gender varchar(10),email varchar(50),password varchar(50),joinedDate varchar(30),phone INT,specialization varchar(40),address varchar(50),NIC varchar(10),hospital_id INT)";
        String myTableData = "insert into regDoctors(`doctor_id`,`firstName`,`lastName`,`gender`,`email`,`password`,`joinedDate`,`phone`,`specialization`,`address`,`NIC`,`hospital_id`) values (1, 'Savandi','Subhartha', 'Female','savandi98@gmail.com','cdsf&','01/01/2020',0776354622,'ENT','23/5, Obawatta road, Madiwela','987673546V',1)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Doctor", "root", "");
            Statement s1 = con.createStatement();
            Statement s2 = con.createStatement();
            s1.executeUpdate(myTableName);
            s2.executeUpdate(myTableData);
            System.out.println("Table Created and one doctor row added");
        }
        catch (SQLException e ) {
            System.out.println("An error has occurred on Table Creation");
        }
        catch (ClassNotFoundException e) {
            System.out.println("An Mysql drivers were not found");
        }
    }
}
