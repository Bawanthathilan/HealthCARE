package com.appApi.service;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.appApi.model.Appointment;
import com.appApi.utils.DBconnection;

public class appointmentService {
	public String insertAppointment(Appointment app) {

		DBconnection connection = new DBconnection();
        String output = "";

        try {
            Connection con = connection.getConnection();

            if (con == null) {
                return "Error while connecting to the database for inserting.";
            }

            String query = " insert into appointment (`appID`,`fullName`,`phoneNo`,`email`,`address`,`message`,`hospitalID`,`docID`,`patientID`,`date`)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, app.getAppID());
            preparedStmt.setString(2, app.getFullName());
            preparedStmt.setInt(3, app.getPhoneNo());
            preparedStmt.setString(4, app.getEmail());
            preparedStmt.setString(5, app.getAddress());
            preparedStmt.setString(6, app.getMessage());
            preparedStmt.setInt(7, app.getHospitalID());
            preparedStmt.setInt(8, app.getDocID());
            preparedStmt.setInt(9, app.getPatientID());
            preparedStmt.setString(10, app.getDate());
           


            preparedStmt.executeUpdate();
            con.close();
            output = "Inserted successfully";
            System.out.println("Appointment inserted successfully");

        } catch (Exception e) {
            output = "Error while inserting Appointment details.";
            System.out.println("Error while inserting Appointment");
            System.out.println(e.getMessage());
        }
        return output;
    }

    public String readAppointment() {
        DBconnection connection = new DBconnection();
        StringBuilder output = new StringBuilder();
        try {
            Connection con = connection.getConnection();

            if (con == null) {
                return "Error while connecting to the database for reading.";
            }

            output = new StringBuilder("<table border=\"1\"><tr><th>Appointment ID</th><th>Full Name</th><th>Phone No</th><th>Email</th>" +
                    "<th>Address</th><th>Message</th><th>Hospital ID</th><th>Doc ID</th><th>Patient ID</th><th>Date</th>"+
                    "<th>Update</th><th>Remove</th></tr>");
            String query = "select * from appointment";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String AppID =Integer.toString(rs.getInt("appID"));
                String fullName = rs.getString("fullName");
                String phoneNo = Integer.toString(rs.getInt("phoneNo"));
                String email = rs.getString("email");
                String Address = rs.getString("address");
                String message = rs.getString("message");
                String hospitalID = Integer.toString(rs.getInt("hospitalID"));
                String docID = Integer.toString(rs.getInt("docID"));
                String patientID = Integer.toString(rs.getInt("patientID"));
                String date = rs.getString("date");
               
                


                output.append("<tr><td>").append(AppID).append("</td>");
                output.append("<td>").append(fullName).append("</td>");
                output.append("<td>").append(phoneNo).append("</td>");
                output.append("<td>").append(email).append("</td>");
                output.append("<td>").append(Address).append("</td>");
                output.append("<td>").append(message).append("</td>");
                output.append("<td>").append(hospitalID).append("</td>");
                output.append("<td>").append(docID).append("</td>");
                output.append("<td>").append(patientID).append("</td>");
                output.append("<td>").append(date).append("</td>");
               


                output.append("<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>" + "<td><input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\"></td></tr>");
            }

            output.append("</table>");
            System.out.println("Appointment retrieval Successful");
            con.close();
        } catch (Exception e) {
            output = new StringBuilder("Error while reading Appointment.");
            System.out.println("Appointment retrieval Unsuccessful");
            System.err.println(e.getMessage());
        }
        return output.toString();

    }

    public Appointment readAppointment(String id) {
        DBconnection connection = new DBconnection();
        Appointment appointment = new Appointment();
        try {
            Connection con = connection.getConnection();

            if (con == null) {
                System.out.println("Error while connecting to the database for reading.");
            }

            String query = "select * from appointment where appID = '" + id + "'";
            assert con != null;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                
                	appointment.setAppID(rs.getInt("appID"));
                	appointment.setFullName(rs.getString("fullName"));
                	appointment.setPhoneNo(rs.getInt("phonrNo"));
                	appointment.setEmail(rs.getString("email"));
                	appointment.setAddress(rs.getString("address"));
                	appointment.setMessage(rs.getString("message"));
                	appointment.setHospitalID(rs.getInt("hospitalID"));
                	appointment.setDocID(rs.getInt("docID"));
                	appointment.setPatientID(rs.getInt("patientID"));
                	appointment.setDate(rs.getString("date"));
                    

                    System.out.println("Appointment retrieval Successful");
                    con.close();
                    return appointment;
                }
            


        } catch (Exception e) {
            System.out.println("Error while reading appointment. appointment retrieval Unsuccessful");
            System.err.println(e.getMessage());
        }
        System.out.println("No such appointment in system");
        return new Appointment();

    }


    public String updateAppointment(Appointment appointment) {
        DBconnection connection = new DBconnection();
        String output = "";
        try {
            Connection con = connection.getConnection();
            if (con == null) {
                return "Error while connecting to the database for updating.";
            }

            String query = "UPDATE appointment SET fullName=?, phoneNo=?, email=?, address=?, message=?, hospitalID=?, docID=?, patientID=?, date=? WHERE appID=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, appointment.getFullName());
            preparedStmt.setInt(2, appointment.getPhoneNo());
            preparedStmt.setString(3, appointment.getEmail());
            preparedStmt.setString(4, appointment.getAddress());
            preparedStmt.setString(5, appointment.getMessage());
            preparedStmt.setInt(6, appointment.getHospitalID());
            preparedStmt.setInt(7, appointment.getDocID());
            preparedStmt.setInt(8, appointment.getPatientID());
            preparedStmt.setString(9, appointment.getDate());
            preparedStmt.setInt(10, appointment.getAppID());
            
            

            preparedStmt.executeUpdate();
            con.close();
            output = "Updated successfully";
            System.out.println("Update successful on Appointment");
        } catch (Exception e) {
            output = "Error while updating doctor.";
            System.out.println("Update unsuccessful on Appointment");
            System.err.println(e.getMessage());
        }
        return output;
    }

    public String deleteItem(String appID) {
        DBconnection connection = new DBconnection();
        String output = "";

        try {
            Connection con = connection.getConnection();
            if (con == null) {
                return "Error while connecting to the database for deleting.";
            }

            String query = "delete from appointment where appID=?";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, appID);
            preparedStmt.execute();
            con.close();
            output = "Deleted successfully";
            System.out.println("Appointment deleted successfully");
        } catch (Exception e) {
            output = "Error while deleting Appointment.";
            System.err.println(e.getMessage());
            System.out.println("Appointment deletion error");
        }
        return output;
    }
		
		}
		
	
