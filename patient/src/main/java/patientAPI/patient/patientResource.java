package patientAPI.patient;

import patientAPI.patient.patientResource;
import patientAPI.patient.patientService;

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
import patientAPI.patient.patient;
import patientAPI.patient.patientService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;


@Path("patient")
public class patientResource {
	private patientService appRepo = new patientService();

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public String readpatient() {
        return appRepo.readpatient();
    }

    @GET
    @Path("patient/{pat_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public patient readpatient(@PathParam("pat_id") String pat_id) {
        return appRepo.readpatient(pat_id);
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertpatient(@FormParam("pat_id") int pat_id, @FormParam("first_name") String first_name,@FormParam("last_name") String last_name, @FormParam("pat_NIC") int pat_NIC, @FormParam("pat_bday") String pat_bday,  @FormParam("pat_phno") int pat_phno,@FormParam("pat_email") String pat_email,
                               @FormParam("pat_gender") String pat_gender)
                                {

    	patient Patient = new patient();
    	Patient.setPat_id(pat_id);
    	Patient.setFirst_name(first_name);
    	Patient.setLast_name(last_name);
    	Patient.setPat_NIC(pat_NIC);
    	Patient.setPat_bday(pat_bday);
    	Patient.setPat_phno(pat_phno);
    	Patient.setPat_email(pat_email);
    	Patient.setPat_gender(pat_gender);
        

        return appRepo.insertpatient(Patient);
    }

    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updatepatient(String appData) {
    	patient Patient = new patient();
        JsonObject appObject;

        appObject = new JsonParser().parse(appData).getAsJsonObject();
        Patient.setPat_id(Integer.parseInt(appObject.get("appID").getAsString()));
        Patient.setFirst_name(appObject.get("fullName").getAsString());
        Patient.setLast_name(appObject.get("fullName").getAsString());
        Patient.setPat_NIC(Integer.parseInt(appObject.get("appID").getAsString()));
        Patient.setPat_bday(appObject.get("date").getAsString());
        Patient.setPat_phno(Integer.parseInt(appObject.get("appID").getAsString()));
        Patient.setPat_email(appObject.get("fullName").getAsString());
        Patient.setPat_gender(appObject.get("fullName").getAsString());
        
        
     
        return appRepo.updatepatient(Patient);
    }

    @DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String deletepatient(String appData) {

        //Convert the input string to an XML document
        Document doc = Jsoup.parse(appData, "", Parser.xmlParser());
        String pat_id = doc.select("pat_id").text();
        return appRepo.deletepatient(pat_id);
    }
	
	
	
}