package camel.restlet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path(value="/country") 
public interface MyService {
   @GET 
   @Path("/{countrycode}")
   @Produces(value="text/xml") 
   public String getCities(@PathParam("countrycode") String countryCode); 
}
