package pafProject.pafProHospital;


import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import pafProject.pafProHospital.hospitals;
import pafProject.pafProHospital.DBconnection;

public class hospitalService {
	public String inserthospitals(hospitals app) {

		DBconnection connection = new DBconnection();
        String output = "";

        try {
            Connection con = connection.getConnection();

            if (con == null) {
                return "Error while connecting to the database for inserting.";
            }

            String query = " insert into Hospitals (`hosp_id`,`hosp_name`,`phn_no`,`hosp_address`,`hosp_type`,`description`)"
                    + " values (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, app.getHosp_id());
            preparedStmt.setString(2, app.getHosp_name());
            preparedStmt.setInt(3, app.getPhn_no());
            preparedStmt.setString(4, app.getHosp_address());
            preparedStmt.setString(5, app.getHosp_type());
            preparedStmt.setString(6, app.getDescription());
           


            preparedStmt.executeUpdate();
            con.close();
            output = "Inserted successfully";
            System.out.println("Hospital inserted successfully");

        } catch (Exception e) {
            output = "Error while inserting Hospital details.";
            System.out.println("Error while inserting Hospital");
            System.out.println(e.getMessage());
        }
        return output;
    }
    public String readhospitals() {
        DBconnection connection = new DBconnection();
        StringBuilder output = new StringBuilder();
        try {
            Connection con = connection.getConnection();

            if (con == null) {
                return "Error while connecting to the database for reading.";
            }

            output = new StringBuilder("<table border=\"1\"><tr><th>Hospital ID</th><th>Hospital Name</th><th>Phone No</th><th>Hospital Address</th>" +
                    "<th>Hospital Type</th><th>Description</th></tr>");
            String query = "select * from Hospitals";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String Hosp_id =Integer.toString(rs.getInt("hosp_id"));
                String Hosp_name = rs.getString("hosp_name");
                String Phn_no = Integer.toString(rs.getInt("phn_no"));
                String Hosp_address = rs.getString("hosp_address");
                String Hosp_type = rs.getString("hosp_type");
                String Description = rs.getString("description");
                
               
                


                output.append("<tr><td>").append(Hosp_id).append("</td>");
                output.append("<td>").append(Hosp_name).append("</td>");
                output.append("<td>").append(Phn_no).append("</td>");
                output.append("<td>").append(Hosp_address).append("</td>");
                output.append("<td>").append(Hosp_type).append("</td>");
                output.append("<td>").append(Description).append("</td>");
               


                output.append("<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>" + "<td><input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\"></td></tr>");
            }

            output.append("</table>");
            System.out.println("Hospital retrieval Successful");
            con.close();
        } catch (Exception e) {
            output = new StringBuilder("Error while reading Hospitals.");
            System.out.println("Hospital retrieval Unsuccessful");
            System.err.println(e.getMessage());
        }
        return output.toString();

    }

    public hospitals readhospitals(String id) {
        DBconnection connection = new DBconnection();
        hospitals Hospitals = new hospitals();
        try {
            Connection con = connection.getConnection();

            if (con == null) {
                System.out.println("Error while connecting to the database for reading.");
            }

            String query = "select * from Hospitals where hosp_id = '" + id + "'";
            assert con != null;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                
            	Hospitals.setHosp_id(rs.getInt("hosp_id"));
            	Hospitals.setHosp_name(rs.getString("hosp_name"));
            	Hospitals.setPhn_no(rs.getInt("phn_no"));
            	Hospitals.setHosp_address(rs.getString("hosp_address"));
            	Hospitals.setHosp_type(rs.getString("hosp_type"));
            	Hospitals.setDescription(rs.getString("description"));
                	
                    

                    System.out.println("Hospital retrieval Successful");
                    con.close();
                    return Hospitals;
                }
            


        } catch (Exception e) {
            System.out.println("Error while reading Hospital. Hospital retrieval Unsuccessful");
            System.err.println(e.getMessage());
        }
        System.out.println("No such Hospital in system");
        return new hospitals();

    }

    public String updatehospitals(hospitals Hospitals) {
        DBconnection connection = new DBconnection();
        String output = "";
        try {
            Connection con = connection.getConnection();
            if (con == null) {
                return "Error while connecting to the database for updating.";
            }

            String query = "UPDATE Hospital SET hosp_name=?, phn_no=?, hosp_address=?, hosp_type=?, description=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, Hospitals.getHosp_name());
            preparedStmt.setInt(2, Hospitals.getPhn_no());
            preparedStmt.setString(3, Hospitals.getHosp_address());
            preparedStmt.setString(4, Hospitals.getHosp_type());
            preparedStmt.setString(5, Hospitals.getDescription());
            
            

            preparedStmt.executeUpdate();
            con.close();
            output = "Updated successfully";
            System.out.println("Update successful on hospital");
        } catch (Exception e) {
            output = "Error while updating hospital.";
            System.out.println("Update unsuccessful on hospital");
            System.err.println(e.getMessage());
        }
        return output;
    }

    public String deletehospitals(String hosp_id) {
        DBconnection connection = new DBconnection();
        String output = "";

        try {
            Connection con = connection.getConnection();
            if (con == null) {
                return "Error while connecting to the database for deleting.";
            }

            String query = "delete from Hospital where hosp_id=?";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, hosp_id);
            preparedStmt.execute();
            con.close();
            output = "Deleted successfully";
            System.out.println("Hospital deleted successfully");
        } catch (Exception e) {
            output = "Error while deleting Hospital.";
            System.err.println(e.getMessage());
            System.out.println("Hospital deletion error");
        }
        return output;
    }
}
