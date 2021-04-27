/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restuv.uv;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;

/**
 *
 * @author alfonso
 */
@SecurityRequirement(name = "jwt")
@RolesAllowed({"ADMIN", "USER"})
@Path("/misure")
public class MisureResource {

    @Inject
    MisuraStore store;

    @Inject
    JsonWebToken jwt;

    @Operation(summary = "ricerca indici UV", description = "ritorna tutti i valori per una quota superiore a quella fornita come parametro")
    @Counted
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MisuraCalcolata> search(@Valid @QueryParam("min-quota") Integer minQuota) {
        System.out.println(jwt.getName());
        return store.searchMisuraCalcolata(minQuota);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Misura find(@PathParam("id") long id) {
        Misura m = store.find(id).orElseThrow(() -> new NotFoundException("misura non presente"));
        return m;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Misura m) {
        Misura result = store.create(m);
        return Response.status(Response.Status.CREATED)
                .entity(result)
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
        store.delete(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
