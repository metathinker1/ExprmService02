package com.experiment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by robertwood on 7/2/16.
 */
@Path("endpoint02")
public class Endpoint02 {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String handleGet() {
        StringBuilder responseText = new StringBuilder();

        try {
            URL url = new URL("http://localhost:8031/endpoint01");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed: HTTP error code " + conn.getResponseCode());
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                responseText.append(line);
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            responseText.append(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            responseText.append(e.getMessage());
        } finally {

        }

        responseText.append("Exprm-Service02: Results of endpoint02");
        return responseText.toString();
    }
}
