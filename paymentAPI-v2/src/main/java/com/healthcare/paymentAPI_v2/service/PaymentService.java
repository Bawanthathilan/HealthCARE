package com.healthcare.paymentAPI_v2.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.healthcare.paymentAPI_v2.model.Payment;


@Path("Payments")
public class PaymentService {
	Payment itemObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayments() {
		return itemObj.readPayments();
	}


	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("patientID") int patientID,
								@FormParam("hospitalID") int hospitalID,
								@FormParam("docID") int docID,
								@FormParam("amount") String amount,
								@FormParam("appointmentID") String appointmentID,
								@FormParam("paymentStatus") String paymentStatus)
	{

		String output = itemObj.insertPayment(patientID, hospitalID, docID, amount, appointmentID, paymentStatus);
		return output;
	}
	
	@GET
	@Path("/{patientID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String readPaymentAccordingToPatientID(@PathParam("patientID") String patientID) {
		return itemObj.readPaymentAccordingToPatientID(patientID);
	}

	
	@GET
	@Path("/CreditCardInfo/")
	@Produces(MediaType.TEXT_HTML)
	public String readCreditCardInfo() {
		return itemObj.readCreditCardInfo();
	}
	
	@GET
	@Path("/CreditCardInfo/{paymentID}")
	@Produces(MediaType.APPLICATION_JSON)
	public String readCCInfoAccordingToPaymentID(@PathParam("paymentID") String paymentID) {
		return itemObj.readCCInfoAccordingToPaymentID(paymentID);
	}
	
	
	@POST
	@Path("/CreditCardInfo/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCreditCardInfo(@FormParam("cardNo") String cardNo,
								       @FormParam("cvv") String cvv,
								       @FormParam("ccHolderName") String patientID,
								       @FormParam("ccExpDate") String ccExpDate,
								       @FormParam("paymentID") String paymentID)
	
	{

		String output = itemObj.insertCreditCardInfo(cardNo, cvv, patientID, ccExpDate, paymentID);
		return output;
	}

	@PUT
	@Path("/CreditCardInfo/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCreditCardInfo(String CreditCardData) {

		JsonObject itemObject = new JsonParser().parse(CreditCardData).getAsJsonObject();

		
		int CCID = itemObject.get("CCID").getAsInt();
		String cardNo = itemObject.get("cardNo").getAsString();
		String cvv = itemObject.get("cvv").getAsString();
		String ccHolderName = itemObject.get("ccHolderName").getAsString();
		String ccExpDate = itemObject.get("ccExpDate").getAsString();
		String paymentID = itemObject.get("paymentID").getAsString();
		
		String output = itemObj.updateCreditCardInfo(CCID, cardNo, cvv, ccHolderName, ccExpDate, paymentID);
		return output;

	}



	@PUT
	@Path("/refund/{paymentID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_XML)
	public String refund(@PathParam("paymentID")int paymentID, String paymentStatusData) {
		
		JsonObject itemObject = new JsonParser().parse(paymentStatusData).getAsJsonObject();

		
		String paymentStatus = itemObject.get("paymentStatus").getAsString();
		
		String output = itemObj.refund(paymentID, paymentStatus);
		return output;
	}
}
