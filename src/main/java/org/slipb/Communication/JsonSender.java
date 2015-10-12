package org.slipb.Communication;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

/**
 * Created by Marshall Bradley (marshallbradley93@gmail.com)
 *
 * This class initiates a HTTP POST request to send the JSON to the server
 */

public class JsonSender {

    private static final String HEADER_NAME = "content-type";
    private static final String HEADER_VALUE = "application/x-www-form-urlencoded";

    private String destination;

    public JsonSender(String destination) {
        this.destination = destination;
    }

    public HttpResponse send(String json) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(destination);
        StringEntity params = new StringEntity(json);
        request.addHeader(HEADER_NAME, HEADER_VALUE);
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);
        return response;
    }
}
