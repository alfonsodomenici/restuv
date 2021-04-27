/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restuv.uv;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 *
 * @author alfonso
 */
@Path("/immagini")
public class ImmaginiResource {
    
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response image() {
        try {
            byte[] image = Files.readAllBytes(Paths.get("/home/alfonso/Scaricati/immaginiedati/mappa210426.jpg"));
            return Response.ok().entity(new StreamingOutput() {
                @Override
                public void write(OutputStream out) throws IOException, WebApplicationException {
                    out.write(image);
                    out.flush();
                }
            }).build();
        } catch (IOException ex) {
            throw new NotFoundException();
        }
    }
}
