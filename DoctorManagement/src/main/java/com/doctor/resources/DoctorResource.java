package com.doctor.resources;

import com.doctor.model.Doctor;
import com.doctor.service.DoctorService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("doctors")

public class DoctorResource {

    private DoctorService doctorRepo = new DoctorService();

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public String readDoctors() {
        return doctorRepo.readDoctors();
    }

    @GET
    @Path("doctor/{doctor_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Doctor readDoctor(@PathParam("doctor_id") String doctor_id) {
        return doctorRepo.readDoctor(doctor_id);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertDoctor(@FormParam("doctor_id") int doctor_id, @FormParam("firstName") String firstName, @FormParam("lastName") String lastName, @FormParam("gender") String gender,
                               @FormParam("email") String email, @FormParam("password") String password, @FormParam("joinedDate") String joinedDate, @FormParam("phone") int phone, @FormParam("specialization") String specialization, @FormParam("address") String address,
                               @FormParam("NIC") String NIC, @FormParam("hospital_id") int hospital_id, @FormParam("patient_id") int patient_id, @FormParam("appointment_id") int appointment_id) {

        Doctor doctor = new Doctor();
        doctor.setDoctor_id(doctor_id);
        doctor.setFirstName(firstName);
        doctor.setLastName(lastName);
        doctor.setGender(gender);
        doctor.setEmail(email);
        doctor.setPassword(password);
        doctor.setJoinedDate(joinedDate);
        doctor.setPhone(phone);
        doctor.setSpecialization(specialization);
        doctor.setAddress(address);
        doctor.setNIC(NIC);
        doctor.setHospital_id(hospital_id);
        doctor.setPatient_id(patient_id);
        doctor.setAppointment_id(appointment_id);

        return doctorRepo.insertDoctor(doctor);
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateDoctor(String doctorData) {
        Doctor doctor = new Doctor();
        JsonObject doctorObject;

        doctorObject = new JsonParser().parse(doctorData).getAsJsonObject();
        doctor.setDoctor_id(Integer.parseInt(doctorObject.get("doctor_id").getAsString()));
        doctor.setFirstName(doctorObject.get("firstName").getAsString());
        doctor.setLastName(doctorObject.get("lastName").getAsString());
        doctor.setGender(doctorObject.get("gender").getAsString());
        doctor.setEmail(doctorObject.get("email").getAsString());
        doctor.setPassword(doctorObject.get("password").getAsString());
        doctor.setJoinedDate(doctorObject.get("joinedDate").getAsString());
        doctor.setPhone(Integer.parseInt(doctorObject.get("phone").getAsString()));
        doctor.setSpecialization(doctorObject.get("specialization").getAsString());
        doctor.setAddress(doctorObject.get("address").getAsString());
        doctor.setNIC(doctorObject.get("NIC").getAsString());
        doctor.setHospital_id(Integer.parseInt(doctorObject.get("hospital_id").getAsString()));
        doctor.setPatient_id(Integer.parseInt(doctorObject.get("patient_id").getAsString()));
        doctor.setAppointment_id(Integer.parseInt(doctorObject.get("appointment_id").getAsString()));

        return doctorRepo.updateDoctor(doctor);
    }

    @DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteDoctor(String doctorData) {

        //Convert the input string to an XML document
        Document doc = Jsoup.parse(doctorData, "", Parser.xmlParser());
        int doctor_id = Integer.parseInt(doc.select("doctor_id").text());
        return doctorRepo.deleteItem(doctor_id);
    }
}

