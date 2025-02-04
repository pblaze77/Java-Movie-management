/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Paulo
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "title", 
    "publishedDate", "description", 
    "directors", "actors", 
    "duration", "year", "picturePath"})
public class Movie {

    public static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private int id;

    @XmlElement(name = "title")
    private String title;
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlElement(name = "publisheddate")
    private LocalDateTime publishedDate;
    
    @XmlElement(name = "description")
    private String description;
    
    
    @XmlElement(name = "picturepath")
    private String picturePath;
    
    @XmlElement(name = "duration")
    private int duration;
    
    @XmlElement(name = "year")
    private int year;
       

    public Movie() {
    }

    public Movie(int id, String title, LocalDateTime publishedDate, String description, String picturePath, int duration, int year, Set<Person> actors, Set<Person> directors) {
        this.id = id;
        this.title = title;
        this.publishedDate = publishedDate;
        this.description = description;
        this.picturePath = picturePath;
        this.duration = duration;
        this.year = year;
    }

    public Movie(String title, LocalDateTime publishedDate, String description, String picturePath, int duration, int year) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.description = description;
        this.picturePath = picturePath;
        this.duration = duration;
        this.year = year;
    }
    
    public Movie(int id, String title, LocalDateTime publishedDate, String description, String picturePath, int duration, int year) {
        this(title, publishedDate, description, picturePath, duration, year);
         this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", title=" + title + ", publishedDate=" + publishedDate + ", description=" + description + '}';
    }

}
