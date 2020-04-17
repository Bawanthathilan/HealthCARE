package com.appApi.appointmentApi;
import com.appApi.appointmentApi.appointmentResource;
import com.appApi.service.appointmentService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.*;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.appApi.model.Appointment;
import com.appApi.service.appointmentService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

@Path("appointments")
public class appointmentResource {
	private appointmentService appRepo = new appointmentService();

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public String readAppointment() {
        return appRepo.readAppointment();
    }

    @GET
    @Path("appointment/{appID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Appointment readAppointment(@PathParam("appID") String appID) {
        return appRepo.readAppointment(appID);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertAppointment(@FormParam("appID") int appID, @FormParam("fullName") String fullName, @FormParam("phoneNo") int phoneNo, @FormParam("email") String email,
                               @FormParam("address") String address, @FormParam("message") String message, @FormParam("hospitalID") int hospitalID, @FormParam("docID") int docID, @FormParam("patientID") int patientID, @FormParam("date") String date)
                                {

        Appointment appointment = new Appointment();
        appointment.setAppID(appID);
        appointment.setFullName(fullName);
        appointment.setPhoneNo(phoneNo);
        appointment.setEmail(email);
        appointment.setAddress(address);
        appointment.setMessage(message);
        appointment.setHospitalID(hospitalID);
        appointment.setDocID(docID);
        appointment.setPatientID(patientID);
        appointment.setDate(date);
        

        return appRepo.insertAppointment(appointment);
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateAppointment(String appData) {
    	Appointment appointment = new Appointment();
        JsonObject appObject;

        appObject = new JsonParser().parse(appData).getAsJsonObject();
        appointment.setAppID(Integer.parseInt(appObject.get("appID").getAsString()));
        appointment.setFullName(appObject.get("fullName").getAsString());
        appointment.setPhoneNo(Integer.parseInt(appObject.get("phoneNo").getAsString()));
        appointment.setEmail(appObject.get("email").getAsString());
        appointment.setAddress(appObject.get("address").getAsString());
        appointment.setMessage(appObject.get("message").getAsString());
        appointment.setHospitalID(Integer.parseInt(appObject.get("hospitalID").getAsString()));
        appointment.setDocID(Integer.parseInt(appObject.get("docID").getAsString()));
        appointment.setPatientID(Integer.parseInt(appObject.get("patienID").getAsString()));
        appointment.setDate(appObject.get("date").getAsString());
        

        return appRepo.updateAppointment(appointment);
    }

    @DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteAppointment(String appData) {

        //Convert the input string to an XML document
        Document doc = Jsoup.parse(appData, "", Parser.xmlParser());
        String appID = doc.select("appID").text();
        return appRepo.deleteItem(appID);
    }
	
	
	
}