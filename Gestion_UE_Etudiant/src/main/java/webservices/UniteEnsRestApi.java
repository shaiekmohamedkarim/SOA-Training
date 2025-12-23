package webservices;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
    @Path("/ue")
    public class UniteEnsRestApi {

        UniteEnseignementBusiness helper = new UniteEnseignementBusiness();

        //getlistue
        @Path("/list")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getListUe() {
            return Response
                    .status(200)
                    .entity(helper.getListeUE())
                    .build();
        }

}
