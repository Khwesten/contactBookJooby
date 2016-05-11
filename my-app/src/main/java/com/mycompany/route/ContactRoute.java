package com.mycompany.route;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mycompany.App;
import com.mycompany.model.Contact;
import org.jooby.Result;
import org.jooby.Results;
import org.jooby.mvc.Body;
import org.jooby.mvc.GET;
import org.jooby.mvc.POST;
import org.jooby.mvc.Path;

/**
 *
 * @author k-heiner@hotmail.com
 */
@Path("/contact")
public class ContactRoute {

    @Path("/:id")
    @GET
    /**
     * Method used to retrieve a contact by id
     *
     * @param id contact id used by retrive
     *
     * @return Result
     */
    public Result retrieve(int id) throws JsonProcessingException {
        try {
            Contact contact = App.contacts.get(id-1);
            return Results.ok(contact);
        } catch (IndexOutOfBoundsException e) {
            return Results.with("Contato não encontrado!").status(404);
        }
    }

    @POST
    /**
     * Method used to insert a contact
     *
     * @return Result with a contact in body
     */
    public Result insert(@Body Contact contact) {
        String message;

        if (contact.getName().equals("")) {
            message = "Nome não pode ser vazio.";
        } else if (contact.getPhoneNumber().equals("")) {
            message = "O número de telefone não pode ser vazio.";
        } else {
            contact.setId(App.lastInsertId);
            App.lastInsertId++;
            App.contacts.add(contact);

            return Results.ok(contact);
        }

        return Results.with(message).status(400);
    }
}
