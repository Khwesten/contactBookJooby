package com.mycompany;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.jooby.Jooby;
import org.jooby.Results;
import org.jooby.json.Jackson;

/**
 * @author jooby generator
 */
public class App extends Jooby {

    private ArrayList<Contact> contacts = new ArrayList<>();
    private int lastInsertId = 1;

    {
        use(new Jackson());

        /**
         * Method called by index of server
         */
        get("", req -> {
            return "Welcome to contactBookJooby v1.0";
        });

        /**
         * Method used to retrieve a contact by id
         *
         * @param id contact id used by retrive
         *
         * @return Result
         */
        get("/contact/:id", req -> {
            int id = Integer.parseInt(req.param("id").value()) - 1;

            int statusCode = 404;
            String message = "Contato não encontrado!";

            try {
                Contact contact = this.contacts.get(id);

                ObjectMapper mapper = new ObjectMapper();
                message = mapper.writeValueAsString(contact);
                statusCode = 200;

                return Results.with(message).status(statusCode).type("text/plain");
            } catch (IndexOutOfBoundsException e) {
                return Results.with(message).status(statusCode).type("text/plain");
            }
        });

        /**
         * Method used to insert a contact
         *
         * @return Result with a contact in body
         */
        post("/contact", req -> {
            ObjectMapper mapper = new ObjectMapper();

            String jsonInString = req.body().value();
            Contact contact = mapper.readValue(jsonInString, Contact.class);

            int statusCode = 400;
            String message;

            if (contact.getName().equals("")) {
                message = "Nome não pode ser vazio.";
            } else if (contact.getPhoneNumber().equals("")) {
                message = "O número de telefone não pode ser vazio.";
            } else {
                statusCode = 200;
                contact.setId(this.lastInsertId);
                this.lastInsertId++;
                this.contacts.add(contact);
                message = mapper.writeValueAsString(contact);
            }

            return Results.with(message).status(statusCode).type("text/plain");
        });
    }

    public static void main(final String[] args) throws Throwable {
        run(App::new, args);
    }

}
