/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restuv;

import java.io.IOException;
import java.util.UUID;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.eclipse.microprofile.jwt.JsonWebToken;

/**
 *
 * @author alfonso
 */
@Provider
public class RequestFilter implements ContainerRequestFilter {

    @Inject
    JsonWebToken jwt;

    @Override
    public void filter(ContainerRequestContext rc) throws IOException {
        String key = rc.getHeaderString("arpa-key");
        if (key == null) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        UUID.randomUUID().toString();
        System.out.println("filter request " + rc.getUriInfo().getPath() + " user: " + jwt.getName());
    }

}
