package com.mycompany;

import org.jooby.mvc.Body;
import org.jooby.mvc.GET;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;

/**
 *
 * @author k-heiner@hotmail.com
 */
@Path("/testroute")
public class TestRoute {

    Contact contact = new Contact();

    @Path("/:id")
    @GET
    private Contact get(int id) {
        return this.contact;
    }

    @Path("/:id")
    @POST
    private Contact post(int id, @Body Contact contact) {
        contact.setId(id);
        this.contact = contact;
        return this.contact;
    }
}
