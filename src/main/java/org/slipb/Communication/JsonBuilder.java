package org.slipb.Communication;

import java.util.UUID;
import javax.json.Json;
import javax.json.JsonObject;

/**
 * Created by Marshall Bradley (marshallbradley93@gmail.com)
 * ---
 * TODO: Description
 */

public class JsonBuilder {

    private static final String POTENTIAL_ID = "potential";

    private JsonObject json;

    public JsonBuilder(UUID potentialUUID) {

        json = Json.createObjectBuilder()
                .add(POTENTIAL_ID, potentialUUID.toString())
                .build();
    }

    public String getString() {return json.toString();}
}
