package pafProject.pafProHospital;
import pafProject.pafProHospital.hospitalresourses;
import pafProject.pafProHospital.hospitalresourses;

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
import pafProject.pafProHospital.hospitals;
import pafProject.pafProHospital.hospitalService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

@Path("hospitals")
public class hospitalresourses {
	private hospitalService appRepo = new hospitalService();
	

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_HTML)
    public String readhospitals() {
        return appRepo.readhospitals();
    }

    @GET
    @Path("hospitals/{hosp_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public hospitals readhospitals(@PathParam("hosp_id") String hosp_id) {
        return appRepo.readhospitals(hosp_id);
    }
    
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String inserthospitals(@FormParam("hosp_id") int hosp_id, @FormParam("hosp_name") String hosp_name, @FormParam("phn_no") int phn_no, @FormParam("hosp_address") String hosp_address,
                               @FormParam("hosp_type") String hosp_type, @FormParam("description") String description)
                                {

    	hospitals Hospitals = new hospitals();
    	Hospitals.setHosp_id(hosp_id);
    	Hospitals.setHosp_name(hosp_name);
    	Hospitals.setPhn_no(phn_no);
    	Hospitals.setHosp_address(hosp_address);
    	Hospitals.setHosp_type(hosp_type);
    	Hospitals.setDescription(description);
        

        return appRepo.inserthospitals(Hospitals);
    }
    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updatehospitals(String appData) {
    	hospitals Hospitals = new hospitals();
        JsonObject appObject;

        appObject = new JsonParser().parse(appData).getAsJsonObject();
        Hospitals.setHosp_id(Integer.parseInt(appObject.get("hosp_id").getAsString()));
        Hospitals.setHosp_name(appObject.get("hosp_name").getAsString());
        Hospitals.setPhn_no(Integer.parseInt(appObject.get("phn_no").getAsString()));
        Hospitals.setHosp_address(appObject.get("hosp_address").getAsString());
        Hospitals.setHosp_type(appObject.get("hosp_type").getAsString());
        Hospitals.setDescription(appObject.get("description").getAsString());
        

        return appRepo.updatehospitals(Hospitals);
       
    }
    
    @DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String deletehospitals(String appData) {

        //Convert the input string to an XML document
        Document doc = Jsoup.parse(appData, "", Parser.xmlParser());
        String hosp_id = doc.select("hosp_id").text();
        return appRepo.deletehospitals(hosp_id);
    }
}
