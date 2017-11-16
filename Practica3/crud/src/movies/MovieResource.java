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
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;


public class MovieResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String id;
    public MovieResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    //Application integration
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Movie getMovie() {
        Movie movie = MovieDao.instance.getModel().get(id);
        if(movie==null)
            throw new RuntimeException("Get: Movie with " + id +  " not found");
        return movie;
    }

    // for the browser
    @GET
    @Produces(MediaType.TEXT_XML)
    public Movie getMovieHTML() {
        Movie movie = MovieDao.instance.getModel().get(id);
        if(movie==null)
            throw new RuntimeException("Get: Movie with " + id +  " not found");
        return movie;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putMovie(JAXBElement<Movie> movie) {
        Movie c = movie.getValue();
        return putAndGetResponse(c);
    }

    @PUT
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response putMovie(
	    		@FormParam("id") String id,
	    		@FormParam("title") String title,
	    		@FormParam("genre") String genre,
	    		@FormParam("director") String director,
	    		@FormParam("year") Integer year,
	    		@FormParam("synopsis") String synopsis,
	    		@FormParam("image") String image, 
            @Context HttpServletResponse servletResponse) throws IOException {

    //	@PathParam("primaryKey") String primaryKey,
    	
    		Movie movie = new Movie(id, title, genre, director, year, synopsis, image);
    		return putAndGetResponse(movie);

    }

    @DELETE
    public void deleteMovie() {
        Movie c = MovieDao.instance.getModel().remove(id);
        if(c==null)
            throw new RuntimeException("Delete: Movie with " + id +  " not found");
    }

    private Response putAndGetResponse(Movie movie) {
        Response res;
        if(MovieDao.instance.getModel().containsKey(movie.getId())) {
            res = Response.noContent().build();
        } else {
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }
        MovieDao.instance.getModel().put(movie.getId(), movie);
        return res;
    }



}