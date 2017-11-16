/*
 * Development of Software Systems based on Components and Services
 * Master in Computer Engineering
 *
 * 2017 © Copyleft - All Wrongs Reserved
 *
 * Ernesto Serrano <erseco@correo.ugr.es>
 *
 */
package movies;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;


public class MovieTest {

    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());
        // Get XML
        String xmlResponse = target.path("rest").path("movies").request()
                .accept(MediaType.TEXT_XML).get(String.class);

        System.out.println(xmlResponse);
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri(
                "http://localhost:8080/crud").build();
    }

}