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

    private static final String HTTP_POST_FAILED = "HTTP POST Request failed, retrying...";
    private static final String MAX_HTTP_POST_FAILED = "HTTP POST Request failed, max attempts reached. Exiting...";

    private static UUID uuid;

    public IDGenerator() {

        UUID potentialUUID = UUID.randomUUID();
        int attempts = 0; // try each HTTP request MAX_ATTEMPTS times
        JsonSender jsonSender = new JsonSender(SERVER_URL);
        String json = new JsonBuilder(potentialUUID).getString();

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
                break;
            } else if (responseString.equals(NEG_RESPONSE)) { // ID already used
                attempts = 0; // reset attempts for each new ID
                potentialUUID = UUID.randomUUID(); // try a new ID
                json = new JsonBuilder(potentialUUID).getString(); // generate new json
            } else if (attempts >= MAX_ATTEMPTS - 1) {
                System.err.println(MAX_HTTP_POST_FAILED);
                System.exit(-1);
            } else {
                System.err.println(HTTP_POST_FAILED);
                attempts++;
            }
        }

        this.uuid = potentialUUID;
    }

    public String toString() {
        return this.uuid.toString();
    }
}
