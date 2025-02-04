/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import hr.algebra.enums.Titles;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;



/**
 *
 * @author Paulo
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Person implements Comparable<Person> {

    @Override
    public int compareTo(Person p) {
         return name.compareTo(p.name);}
    
     public Person() {
    } 
     
      @XmlAttribute
    private int id;
    
    @XmlElement(name = "name")
    private String name;
    
    @XmlElement(name = "role")
    private Titles title;
    
    @XmlTransient
    private int movieId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Titles getTitle() {
        return title;
    }

    public void setTitle(Titles title) {
        this.title = title;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Person(int id, String name, Titles title, int movieId) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.movieId = movieId;
    }

    public Person(int id, String name, Titles title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public Person(String name, Titles title) {
        this.name = name;
        this.title = title;
    }

    public Person(String name, Titles title, int movieId) {
        this.name = name;
        this.title = title;
        this.movieId = movieId;
    }

    public Person(Titles title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        return Objects.equals(this.name, other.name);
    }

    
    
    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", title=" + title + '}';
    }

  
     
}
