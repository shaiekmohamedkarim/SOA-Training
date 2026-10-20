package webservices;

import entities.Module;
import filtres.Secured;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/module")
public class ModuleWS {
    ModuleBusiness helper = new ModuleBusiness();

    @Path("/list")
    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return Response.status(200).entity(helper.getAllModules()).build();
    }

    @Path("/{matricule}")
    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByMatricule(@PathParam("matricule") String matricule) {
        Module module = helper.getModuleByMatricule(matricule);
        if (module != null) {
            return Response.status(200).entity(module).build();
        }
        return Response.status(404).entity("Module not found").build();
    }

    @Path("/type/{type}")
    @Secured
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByType(@PathParam("type") String type) {
        try {
            Module.TypeModule moduleType = Module.TypeModule.valueOf(type.toUpperCase());
            return Response.status(200).entity(helper.getModulesByType(moduleType)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(400).entity("Type de module invalide").build();
        }
    }

    @Path("/add")
    @Secured
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addModule(Module module){
        if(helper.addModule(module)){
            return Response
                    .status(201)
                    .entity("Module added").build();
        }else return Response
                .status(400)
                .entity("Module not added")
                .build();

    }

    @Path("/delete/{matricule}")
    @Secured
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteModule(@PathParam("matricule") String matricule) {
        if (helper.deleteModule(matricule)) {
            return Response.status(200).entity("Module deleted").build();
        }
        return Response.status(404).entity("Module not found").build();
    }

    @Path("/update/{matricule}")
    @Secured
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateModule(@PathParam("matricule") String matricule, Module module) {
        if (helper.updateModule(matricule, module)) {
            return Response.status(200).entity("Module updated").build();
        }
        return Response.status(400).entity("Module not updated").build();
    }
}
