/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Paulo
 */
@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "moviearchive")
public class MovieArchive {
    public MovieArchive() {
    }
     @XmlElementWrapper
    @XmlElement(name = "movie")
     private List<Movie> movies;
     
      public MovieArchive(List<Movie> movies) {
        this.movies = movies;
    }
    
    public List<Movie> getMovies() {
        return movies;
    }
}
