package com.mycompany;

import com.mycompany.model.Contact;
import com.mycompany.route.ContactRoute;
import java.util.ArrayList;
import org.jooby.Jooby;
import org.jooby.json.Jackson;

/**
 * Bootstrap and routes to interact with contact
 *
 * @author jooby generator
 */
public class App extends Jooby {

    public static ArrayList<Contact> contacts = new ArrayList<>();
    public static int lastInsertId = 1;

    {
        use(new Jackson());
        use(ContactRoute.class);

        /**
         * Method called by index of server
         */
        get("", req -> {
            return "Welcome to contactBookJooby v1.0";
        });
    }

    public static void main(final String[] args) throws Throwable {
        run(App::new, args);
    }

}
