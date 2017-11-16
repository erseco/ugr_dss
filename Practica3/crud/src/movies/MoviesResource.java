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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;


// Will map the resource to the URL movies
@Path("/movies")
public class MoviesResource {

    // Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    // Return the list of movies to the user in the browser
    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Movie> getMoviesBrowser() {
        List<Movie> movies = new ArrayList<Movie>();
        movies.addAll(MovieDao.instance.getModel().values());
        return movies;
    }

    // Return the list of movies for applications
    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        movies.addAll(MovieDao.instance.getModel().values());
        return movies;
    }

    // retuns the number of movies
    // Use http://localhost:8080/com.vogella.jersey.movie/rest/movies/count
    // to get the total number of records
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCount() {
        int count = MovieDao.instance.getModel().size();
        return String.valueOf(count);
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newMovie(
	    		@FormParam("id") String id,
	    		@FormParam("title") String title,
	    		@FormParam("genre") String genre,
	    		@FormParam("director") String director,
	    		@FormParam("year") Integer year,
	    		@FormParam("synopsis") String synopsis,
	    		@FormParam("image") String image, 
            @Context HttpServletResponse servletResponse) throws IOException {

    		Movie movie = new Movie(id, title, genre, director, year, synopsis, image);

        MovieDao.instance.getModel().put(id, movie);
    }

    // Defines that the next path parameter after movies is
    // treated as a parameter and passed to the MovieResources
    // Allows to type http://localhost:8080/com.vogella.jersey.movie/rest/movies/1
    // 1 will be treaded as parameter movie and passed to MovieResource
    @Path("{movie}")
    public MovieResource getMovie(@PathParam("movie") String id) {
        return new MovieResource(uriInfo, request, id);
    }

}