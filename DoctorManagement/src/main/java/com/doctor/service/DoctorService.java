package com.doctor.service;

import com.doctor.model.Doctor;
import com.doctor.utils.DoctorDBConnection;

import java.sql.*;

public class DoctorService {

    private Connection con;
    private DoctorDBConnection connection;

    public String insertDoctor(Doctor doctor) {

        connection = new DoctorDBConnection();
        String output = "";

        try {
            con = connection.getConnection();

            if (con == null) {
                return "Error while connecting to the database for inserting.";
            } else
                System.out.println("DB connection established");

            String query = " insert into regDoctors (`doctor_id`,`firstName`,`lastName`,`gender`,`email`,`password`,`joinedDate`,`phone`,`specialization`,`address`,`NIC`,`hospital_id`)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, doctor.getDoctor_id());
            preparedStmt.setString(2, doctor.getFirstName());
            preparedStmt.setString(3, doctor.getLastName());
            preparedStmt.setString(4, doctor.getGender());
            preparedStmt.setString(5, doctor.getEmail());
            preparedStmt.setString(6, doctor.getPassword());
            preparedStmt.setString(7, doctor.getJoinedDate());
            preparedStmt.setInt(8, doctor.getPhone());
            preparedStmt.setString(9, doctor.getSpecialization());
            preparedStmt.setString(10, doctor.getAddress());
            preparedStmt.setString(11, doctor.getNIC());
            preparedStmt.setInt(12, doctor.getHospital_id());


            preparedStmt.executeUpdate();
            con.close();
            System.out.println("DB connection closed");
            output = "Inserted successfully";
            System.out.println("Doctor inserted successfully");

        } catch (Exception e) {
            output = "Error while inserting doctor details.";
            System.out.println("Error while inserting doctor");
            System.out.println(e.getMessage());
        }
        return output;
    }

    public String readDoctors() {

        connection = new DoctorDBConnection();
        StringBuilder output = new StringBuilder();
        try {
            con = connection.getConnection();

            if (con == null) {
                return "Error while connecting to the database for reading.";
            } else
                System.out.println("DB connection established");

            output = new StringBuilder("<table border=\"1\"><tr><th>Doctor ID</th><th>First Name</th><th>Last Name</th><th>Gender</th>" +
                    "<th>Email</th><th>Password</th><th>Joined Date</th><th>Phone</th><th>Specialization</th>" +
                    "<th>Address</th><th>NIC</th><th>Hospital ID</th><th>Update</th><th>Remove</th></tr>");
            String query = "select * from regDoctors";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String doctor_id = String.valueOf(rs.getInt("doctor_id"));
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String joinedDate = rs.getString("joinedDate");
                String phone = Integer.toString(rs.getInt("phone"));
                String specialization = rs.getString("specialization");
                String address = rs.getString("address");
                String NIC = rs.getString("NIC");
                String hospital_id = String.valueOf(rs.getInt("hospital_id"));


                output.append("<tr><td>").append(doctor_id).append("</td>");
                output.append("<td>").append(firstName).append("</td>");
                output.append("<td>").append(lastName).append("</td>");
                output.append("<td>").append(gender).append("</td>");
                output.append("<td>").append(email).append("</td>");
                output.append("<td>").append(password).append("</td>");
                output.append("<td>").append(joinedDate).append("</td>");
                output.append("<td>").append(phone).append("</td>");
                output.append("<td>").append(specialization).append("</td>");
                output.append("<td>").append(address).append("</td>");
                output.append("<td>").append(NIC).append("</td>");
                output.append("<td>").append(hospital_id).append("</td>");


                output.append("<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>" + "<td><input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\"></td></tr>");
            }

            output.append("</table>");
            System.out.println("Doctors retrieval Successful");
            System.out.println("DB connection closed");
            con.close();
        } catch (Exception e) {
            output = new StringBuilder("Error while reading doctors.");
            System.out.println("Doctors retrieval Unsuccessful");
            System.err.println(e.getMessage());
        }
        return output.toString();
    }

    public Doctor readDoctor(int id) {

        connection = new DoctorDBConnection();
        Doctor doctor = new Doctor();
        try {
            con = connection.getConnection();

            if (con == null) {
                System.out.println("Error while connecting to the database for reading.");
            } else
                System.out.println("DB connection established");

            String query = "select * from regDoctors where doctor_id = '" + id + "'";
            assert con != null;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                if (rs.getInt("phone") != 0) {
                    doctor.setDoctor_id(rs.getInt("doctor_id"));
                    doctor.setFirstName(rs.getString("firstName"));
                    doctor.setLastName(rs.getString("lastName"));
                    doctor.setGender(rs.getString("gender"));
                    doctor.setEmail(rs.getString("email"));
                    doctor.setPassword(rs.getString("password"));
                    doctor.setJoinedDate(rs.getString("joinedDate"));
                    doctor.setPhone(rs.getInt("phone"));
                    doctor.setSpecialization(rs.getString("specialization"));
                    doctor.setAddress(rs.getString("address"));
                    doctor.setNIC(rs.getString("NIC"));
                    doctor.setHospital_id(rs.getInt("hospital_id"));

                    System.out.println("Doctor retrieval Successful");
                    System.out.println("DB connection closed");
                    con.close();
                    return doctor;
                }
            }


        } catch (Exception e) {
            System.out.println("Error while reading doctor. Doctor retrieval Unsuccessful");
            System.err.println(e.getMessage());
        }
        System.out.println("No such doctor in system");
        return new Doctor();

    }


    public String updateDoctor(Doctor doctor) {

        connection = new DoctorDBConnection();
        String output = "";
        try {
            con = connection.getConnection();
            if (con == null) {
                return "Error while connecting to the database for updating.";
            } else
                System.out.println("DB connection established");

            String query = "UPDATE regDoctors SET firstName=?, lastName=?, gender=?, email=?, password=?, joinedDate=?, phone=?, specialization=?, address=?, NIC=?, hospital_id=? WHERE doctor_id=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, doctor.getFirstName());
            preparedStmt.setString(2, doctor.getLastName());
            preparedStmt.setString(3, doctor.getGender());
            preparedStmt.setString(4, doctor.getEmail());
            preparedStmt.setString(5, doctor.getPassword());
            preparedStmt.setString(6, doctor.getJoinedDate());
            preparedStmt.setInt(7, doctor.getPhone());
            preparedStmt.setString(8, doctor.getSpecialization());
            preparedStmt.setString(9, doctor.getAddress());
            preparedStmt.setString(10, doctor.getNIC());
            preparedStmt.setInt(11, doctor.getHospital_id());
            preparedStmt.setInt(12, doctor.getDoctor_id());

            preparedStmt.executeUpdate();
            con.close();
            output = "Updated successfully";
            System.out.println("DB connection closed");
            System.out.println("Update successful on doctor");
        } catch (Exception e) {
            output = "Error while updating doctor.";
            System.out.println("Update unsuccessful on doctor");
            System.err.println(e.getMessage());
        }
        return output;
    }

    public String deleteDoctor(int doctor_id) {

        connection = new DoctorDBConnection();
        String output = "";

        try {
            con = connection.getConnection();
            if (con == null) {
                return "Error while connecting to the database for deleting.";
            } else
                System.out.println("DB connection established");

            String query = "delete from regDoctors where doctor_id=?";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, doctor_id);
            preparedStmt.execute();
            con.close();
            output = "Deleted successfully";
            System.out.println("DB connection closed");
            System.out.println("Doctor deleted successfully");
        } catch (Exception e) {
            output = "Error while deleting doctor.";
            System.err.println(e.getMessage());
            System.out.println("Doctor deletion error");
        }
        return output;
    }

    public String loginDoctor(Doctor doctor) {

        String output = "";

        connection = new DoctorDBConnection();

        try {
            con = connection.getConnection();

            if (con == null) {
                System.out.println("Error while connecting to the database for reading.");
            } else
                System.out.println("DB connection established");

            String query = "select * from regDoctors where email = '" + doctor.getEmail() + "' AND password = '" + doctor.getPassword() + "'";
            assert con != null;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {

                if (doctor.getEmail().equals(rs.getString("email")) && doctor.getPassword().equals(rs.getString("password")))
                    output = "Doctor login Successful";

            } else output = "Doctor login unsuccessful";

            System.out.println("DB connection closed");
            con.close();

        } catch (Exception e) {
            System.out.println("Error while reading doctor. Doctor login Unsuccessful");
            System.err.println(e.getMessage());
            output = "No such doctor in system";
        }
        return output;
    }
}
