package org.slipb.Generators;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slipb.Communication.JsonBuilder;
import org.slipb.Communication.JsonSender;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by Marshall Bradley (marshallbradley93@gmail.com)
 * ---
 * TODO: Description
 */

public class IDGenerator {

    private static final String SERVER_URL = "http://www.test.com";
    private static final int MAX_ATTEMPTS = 3;
    private static final String POS_RESPONSE = "ID FREE";
    private static final String NEG_RESPONSE = "ID USED";

    private static final String RE_GENERATE = "ID already in use, regenerating...";
    private static final String HTTP_POST_FAILED = "HTTP POST Request failed, retrying...";
    private static final String MAX_HTTP_POST_FAILED = "HTTP POST Request failed, max attempts reached. Exiting...";


    public static UUID generateID() {

        UUID potentialUUID = UUID.randomUUID();

        if (checkID(potentialUUID)) {
            return potentialUUID;
        } else {
            return generateID();
        }
    }

    public static String toString(UUID uuid) {
        return uuid.toString();
    }

    private static Boolean checkID(UUID uuid) {

        int attempts = 0; // try each HTTP request MAX_ATTEMPTS times
        JsonSender jsonSender = new JsonSender(SERVER_URL);
        String json = new JsonBuilder(uuid).getString();

        while (true) {

            String responseString = "";

            try {
                HttpResponse httpResponse = jsonSender.send(json);
                responseString = EntityUtils.toString(httpResponse.getEntity());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            responseString = POS_RESPONSE; // TODO: Implement properly

            if (responseString.equals(POS_RESPONSE)) { // Good ID
                return true;
            } else if (responseString.equals(NEG_RESPONSE)) { // ID already used
                System.out.println(RE_GENERATE);
                return false;
            } else if (attempts >= MAX_ATTEMPTS - 1) {
                System.err.println(MAX_HTTP_POST_FAILED);
                System.exit(-1);
            } else {
                System.err.println(HTTP_POST_FAILED);
                attempts++;
            }
        }

    }
}
