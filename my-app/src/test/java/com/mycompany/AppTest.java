package com.mycompany;

import org.junit.Test;

/**
 * @author jooby generator
 */
public class AppTest extends BaseTest {

    static String JSON_TO_INSERT = "{\"name\": \"Heiner\", \"phoneNumber\": \"8\"}";
    static String JSON_EXPECTED
        = "{\"id\":1,\"name\":\"Heiner\",\"phoneNumber\":\"8\"}";

    @Test
    public void retrieveContact() throws Exception {
        server.post("/contact").body(JSON_TO_INSERT, "application/json");
        server.get("/contact/1").expect(JSON_EXPECTED).expect(200);
    }

    @Test
    public void insertcontact() throws Exception {
        server.post("/contact")
            .body(JSON_TO_INSERT, "application/json")
            .expect(JSON_EXPECTED)
            .expect(200);
    }

}
