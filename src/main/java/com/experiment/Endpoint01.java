package com.experiment;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Random;

/**
 * Created by robertwood on 5/13/16.
 */
@Path("endpoint01")
public class Endpoint01 {

    private Random random = new Random();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String handleGet() {
        int waitMilliSeconds = random.nextInt(4000);
        try {
            Thread.sleep(waitMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Results of endpoint01 { wait (" + waitMilliSeconds + ") millis }";
    }
}
