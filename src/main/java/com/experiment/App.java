package com.experiment;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

// https://examples.javacodegeeks.com/core-java/jax-rs-client-example/
public class App
{
    private Client client;
    private HttpServer server;

    private String testUrl = "http://www.thomas-bayer.com/sqlrest/";

    public void initializeAndStart() throws IOException {
        client = Client.create();
        server = createHttpServer();
        server.start();
    }


    public void sendRequest() {
        WebResource webResource = client.resource(testUrl);
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("HTTP Error: " + response.getStatus());
        }

        String result = response.getEntity(String.class);
        System.out.println("Response from the server: ");
        System.out.println(result);
    }


    private HttpServer createHttpServer() throws IOException {
        return HttpServerFactory.create(getURI(), getResouceConfig());
    }

    private ResourceConfig getResouceConfig() {
        return new PackagesResourceConfig("com.experiment");
    }

    private URI getURI() {
        return UriBuilder.fromUri("http://" + "localhost" + "/").port(8032).build();
    }

    public static void main( String[] args )
    {
        App app = new App();
        try {
            app.initializeAndStart();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //app.sendRequest();
    }
}
