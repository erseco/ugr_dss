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

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Movie {
    private String id;
    private String title;
    private String genre;
    private String director;
    private Integer year;
    private String synopsis;
    private String image;

    public Movie(){

    }

    public Movie (String id, String title, String genre, String director, Integer year, String synopsis, String image){
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.synopsis = synopsis;
        this.image = image;
    }

    public String getId() {
        return id.toString();
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


}