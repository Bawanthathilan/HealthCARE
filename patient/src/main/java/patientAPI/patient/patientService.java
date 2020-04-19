package patientAPI.patient;


import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import patientAPI.patient.patient;
import patientAPI.patient.DBconnection;

public class patientService {
	public String insertpatient(patient app) {

		DBconnection connection = new DBconnection();
        String output = "";

        try {
            Connection con = connection.getConnection();

            if (con == null) {
                return "Error while connecting to the database for inserting.";
            }

            String query = " insert into Patient (`pat_id`,`first_name`,`last_name`,`pat_NIC`,`pat_bday`,`pat_phno`,`pat_email`,`pat_gender`)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, app.getPat_id());
            preparedStmt.setString(2, app.getFirst_name());
            preparedStmt.setString(3, app.getLast_name());
            preparedStmt.setInt(4, app.getPat_NIC());
            preparedStmt.setString(5, app.getPat_bday());
            preparedStmt.setInt(6, app.getPat_phno());
            preparedStmt.setString(7, app.getPat_email());
            preparedStmt.setString(8, app.getPat_gender());


            preparedStmt.executeUpdate();
            con.close();
            output = "Inserted successfully";
            System.out.println("patient inserted successfully");

        } catch (Exception e) {
            output = "Error while inserting patient details.";
            System.out.println("Error while inserting patient");
            System.out.println(e.getMessage());
        }
        return output;
    }

    public String readpatient() {
        DBconnection connection = new DBconnection();
        StringBuilder output = new StringBuilder();
        try {
            Connection con = connection.getConnection();

            if (con == null) {
                return "Error while connecting to the database for reading.";
            }

            output = new StringBuilder("<table border=\"1\"><tr><th>Patient ID</th><th>First Name</th><th>Last Name</th><th>NIC</th>" +
                    "<th>Birthday</th><th>phoneNo</th><th>Email</th><th>Gender</th>"+
                    "<th>Update</th><th>Remove</th></tr>");
            String query = "select * from Patient";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String Pat_id =Integer.toString(rs.getInt("pat_id"));
                String First_name = rs.getString("first_name");
                String Last_name = Integer.toString(rs.getInt("last_name"));
                String Pat_NIC = rs.getString("pat_NIC");
                String Pat_bday = rs.getString("pat_bday");
                String Pat_phno = rs.getString("pat_phno");
                String Pat_email = Integer.toString(rs.getInt("pat_email"));
                String Pat_gender = Integer.toString(rs.getInt("pat_gender"));
                


                output.append("<tr><td>").append(Pat_id).append("</td>");
                output.append("<td>").append(First_name).append("</td>");
                output.append("<td>").append(Last_name).append("</td>");
                output.append("<td>").append(Pat_NIC).append("</td>");
                output.append("<td>").append(Pat_bday).append("</td>");
                output.append("<td>").append(Pat_phno).append("</td>");
                output.append("<td>").append(Pat_email).append("</td>");
                output.append("<td>").append(Pat_gender).append("</td>");
               


                output.append("<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>" + "<td><input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\"></td></tr>");
            }

            output.append("</table>");
            System.out.println("patient retrieval Successful");
            con.close();
        } catch (Exception e) {
            output = new StringBuilder("Error while reading patient.");
            System.out.println("patient retrieval Unsuccessful");
            System.err.println(e.getMessage());
        }
        return output.toString();

    }

    public patient readpatient(String id) {
        DBconnection connection = new DBconnection();
        patient Patient = new patient();
        try {
            Connection con = connection.getConnection();

            if (con == null) {
                System.out.println("Error while connecting to the database for reading.");
            }

            String query = "select * from Patient where pat_id = '" + id + "'";
            assert con != null;
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                
            	Patient.setPat_id(rs.getInt("pat_id"));
            	Patient.setFirst_name(rs.getString("first_name"));
            	Patient.setLast_name(rs.getString("last_name"));
            	Patient.setPat_NIC(rs.getInt("pat_NIC"));
            	Patient.setPat_bday(rs.getString("pat_bday"));
            	Patient.setPat_phno(rs.getInt("pat_phno"));
            	Patient.setPat_email(rs.getString("pat_email"));
            	Patient.setPat_gender(rs.getString("pat_gender"));
                    

                    System.out.println("Patient retrieval Successful");
                    con.close();
                    return Patient;
                }
            


        } catch (Exception e) {
            System.out.println("Error while reading Patient. Patient retrieval Unsuccessful");
            System.err.println(e.getMessage());
        }
        System.out.println("No such Patient in system");
        return new patient();

    }


    public String updatepatient(patient Patient) {
        DBconnection connection = new DBconnection();
        String output = "";
        try {
            Connection con = connection.getConnection();
            if (con == null) {
                return "Error while connecting to the database for updating.";
            }

            String query = "UPDATE Patient SET first_name=?, last_name=?, pat_NIC=?, pat_bday=?, pat_phno=?, pat_email=?, pat_gender=? WHERE pat_id=?";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, Patient.getPat_id());
            preparedStmt.setString(2, Patient.getFirst_name());
            preparedStmt.setString(3, Patient.getLast_name());
            preparedStmt.setInt(4, Patient.getPat_NIC());
            preparedStmt.setString(5, Patient.getPat_bday());
            preparedStmt.setInt(6, Patient.getPat_phno());
            preparedStmt.setString(7, Patient.getPat_email());
            preparedStmt.setString(8, Patient.getPat_gender());
            

            preparedStmt.executeUpdate();
            con.close();
            output = "Updated successfully";
            System.out.println("Update successful on Patient");
        } catch (Exception e) {
            output = "Error while updating Patient.";
            System.out.println("Update unsuccessful on Patient");
            System.err.println(e.getMessage());
        }
        return output;
    }

    public String deletepatient(String pat_id) {
        DBconnection connection = new DBconnection();
        String output = "";

        try {
            Connection con = connection.getConnection();
            if (con == null) {
                return "Error while connecting to the database for deleting.";
            }

            String query = "delete from Patient where pat_id=?";

            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, pat_id);
            preparedStmt.execute();
            con.close();
            output = "Deleted successfully";
            System.out.println("Patient deleted successfully");
        } catch (Exception e) {
            output = "Error while deleting Patient.";
            System.err.println(e.getMessage());
            System.out.println("Patient deletion error");
        }
        return output;
    }
		
		}