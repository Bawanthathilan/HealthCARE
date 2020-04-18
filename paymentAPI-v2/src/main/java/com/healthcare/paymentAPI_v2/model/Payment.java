package com.healthcare.paymentAPI_v2.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayment(int docID, int hospitalID, int patientID, String amount, String appointmentID, String paymentStatus) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			String query = " insert into payments (`patientID`, `hospitalID`,`docID`,`amount`, `appointmentID`, `paymentStatus`, `date` )"
					+ " values (?, ?, ?, ?, ?, ?, ?)";

			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			System.out.println(formatter.format(date));

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setInt(1, patientID);
			preparedStmt.setInt(2, hospitalID);
			preparedStmt.setInt(3, docID);
			preparedStmt.setString(4, amount);
			preparedStmt.setString(5, appointmentID);
			preparedStmt.setString(6, paymentStatus);
			preparedStmt.setString(7, (formatter.format(date)));

			preparedStmt.execute();
			con.close();

			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;

	}

	public String insertCreditCardInfo(String cardNo, String cvv, String ccHolderName, String ccExpDate, String paymentID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			String query = " insert into creditcardinfo (`cardNo`, `cvv`,`ccHolderName`,`ccExpDate`,`paymentID` )" + " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, cardNo);
			preparedStmt.setString(2, cvv);
			preparedStmt.setString(3, ccHolderName);
			preparedStmt.setString(4, ccExpDate);
			preparedStmt.setString(5, paymentID);

			preparedStmt.execute();
			con.close();

			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;

	}

	public String readPayments() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			output = "<table border=\"1\"><tr><th>PaymentID</th><th>hospitalID</th><th>patientID</th><th>docID</th><th>amount</th><th>dates</th><th>appointmentID</th><th>paymentStatus</th></tr>";
			String query = "select * from payments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String hospitalID = Integer.toString(rs.getInt("hospitalID"));
				String patientID = Integer.toString(rs.getInt("patientID"));
				String docID = Integer.toString(rs.getInt("docID"));
				String amount = rs.getString("amount");
				String dates = rs.getString("date");
				String appointmentID = rs.getString("appointmentID");
				String paymentStatus = rs.getString("paymentStatus");

				System.out.println(paymentID + patientID);

				output += "<tr><td>" + paymentID + "</td>";
				output += "<td>" + hospitalID + "</td>";
				output += "<td>" + patientID + "</td>";
				output += "<td>" + docID + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + dates + "</td>";
				output += "<td>" + appointmentID + "</td>";
				output += "<td>" + paymentStatus + "</td>";
				output += "</tr>";
			}
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage() + "---------------------------------------------");
		}
		System.out.println(output);
		return output;
	}

	public String readPaymentAccordingToPatientID(String patientID) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				
				return "Error while connecting to the database for reading.";
			}
			output = "<table border=\"1\"><tr><th>PaymentID</th><th>hospitalID</th><th>patientID</th><th>docID</th><th>amount</th><th>dates</th><th>appointmentID</th><th>paymentStatus</th></tr>";
			String query = "select * from payments where patientID = " + patientID;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

		

			while (rs.next()) {

				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String hospitalID = Integer.toString(rs.getInt("hospitalID"));
				String docID = Integer.toString(rs.getInt("docID"));
				String amount = rs.getString("amount");
				String dates = rs.getString("date");
				String appointmentID = rs.getString("appointmentID");
				String paymentStatus = rs.getString("paymentStatus");

				System.out.println(paymentID + patientID);

				output += "<tr><td>" + paymentID + "</td>";
				output += "<td>" + hospitalID + "</td>";
				output += "<td>" + patientID + "</td>";
				output += "<td>" + docID + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + dates + "</td>";
				output += "<td>" + appointmentID + "</td>";
				output += "<td>" + paymentStatus + "</td>";
				output += "</tr>";
			}
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage() + "---------------------------------------------");
		}
		

		return output;
	}
	
	public String readCreditCardInfo() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			output = "<table border=\"1\"><tr><th>CCID</th><th>cardNo</th><th>cvv</th><th>ccHolderName</th><th>ccExpDate</th><th>paymentID</th></tr>";
			String query = "select * from creditcardinfo";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String CCID = Integer.toString(rs.getInt("CCID"));
				String cardNo = rs.getString("cardNo");
				String cvv = rs.getString("cvv");
				String ccHolderName = rs.getString("ccHolderName");
				String ccExpDate = rs.getString("ccExpDate");
				String paymentID = rs.getString("paymentID");
				
				System.out.println(CCID + paymentID);
				
				output += "<tr><td>" + CCID + "</td>";
				output += "<td>" + cardNo + "</td>";
				output += "<td>" + cvv + "</td>";
				output += "<td>" + ccHolderName + "</td>";
				output += "<td>" + ccExpDate + "</td>";
				output += "<td>" + paymentID + "</td>";	
				output += "</tr>";
			}
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage() + "---------------------------------------------");
		}
		System.out.println(output);
		return output;
	}
	
	public String readCCInfoAccordingToPaymentID(String paymentID) {

		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				
				return "Error while connecting to the database for reading.";
			}
			output = "<table border=\\\"1\\\"><tr><th>CCID</th><th>cardNo</th><th>ccHolderName</th><th>ccExpDate</th><th>paymentID</th></tr>";
			String query = "select * from creditcardinfo where paymentID = " + paymentID;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

		

			while (rs.next()) {

				String CCID = Integer.toString(rs.getInt("CCID"));
				String cardNo = rs.getString("cardNo");
				String ccHolderName = rs.getString("ccHolderName");
				String ccExpDate = rs.getString("ccExpDate");
				
				
				System.out.println(CCID + paymentID);
				
				output += "<tr><td>" + CCID + "</td>";
				output += "<td>" + cardNo + "</td>";
				output += "<td>" + ccHolderName + "</td>";
				output += "<td>" + ccExpDate + "</td>";
				output += "<td>" + paymentID + "</td>";	
				output += "</tr>";
			}
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage() + "---------------------------------------------");
		}
		

		return output;
	}
	
	public String refund(int paymentID, String paymentStatus) {

		String output = "Enter 'Refund' to request a refund";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			String query = "UPDATE payments SET paymentStatus= ? WHERE paymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, paymentStatus);
			preparedStmt.setInt(2, paymentID);
			preparedStmt.execute();
			con.close();
			output = "refund successfully";
		} catch (Exception e) {
			output = "Error while refunding...";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateCreditCardInfo(int CCID, String cardNo, String cvv, String ccHolderName, String ccExpDate, String paymentID) {

		String output = "";

		try {

			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = "UPDATE creditcardinfo SET  cardNo= ?, cvv= ?, ccHolderName= ?, ccExpDate= ?, paymentID= ? WHERE CCID= ?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			
			preparedStmt.setString(1, cardNo);
			preparedStmt.setString(2, cvv);
			preparedStmt.setString(3, ccHolderName);
			preparedStmt.setString(4, ccExpDate);
			preparedStmt.setString(5, paymentID);
			preparedStmt.setInt(6, CCID);

			preparedStmt.execute();

			con.close();
			output = "Updated successfully";

		} catch (Exception e) {
			output = "Error while updating.....";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
}
