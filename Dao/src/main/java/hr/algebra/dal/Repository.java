/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.ApplicationUser;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import java.util.List;
import java.util.Optional;
import java.util.Set;




/**
 *
 * @author daniel.bele
 */
public interface Repository {

    
    int createPerson(Person person) throws Exception;
     
     void updatePerson(int id, Person person) throws Exception;
     
     void deletePerson(int id) throws Exception;
     
     Optional<Person> selectPerson(int id) throws Exception;
     
     Optional<Integer> findPerson(Person person) throws Exception;
     
     void createPeople(List<Person> people) throws Exception;
     
     List<Person> selectPeople() throws Exception;
      
     List<Person> selectDirectors() throws Exception; 
      
     List<Person> selectActors() throws Exception;
       
      void createCast(int createdMovieId, Set<Person> people) throws Exception;
      
      int createCastPerson(int movieId, int personId, Person person) throws Exception;
      
      Set<Person> selectCast(int movieId) throws Exception;

     public void deleteCast(int id) throws Exception;
     
      int createUser(ApplicationUser appUser) throws Exception;
      
     Optional<ApplicationUser> selectUser(String username, String password) throws Exception; 
     
     int createMovie(Movie movie) throws Exception;
     
     void createMovies(List<Movie> movies) throws Exception;

     void updateMovie(int id, Movie movie) throws Exception;
     
      void deleteMovie(int id) throws Exception;
      
      Optional<Movie> selectMovie(int id) throws Exception;
      
      List<Movie> selectMovies() throws Exception;

     Optional<Integer> findMovie(String title) throws Exception;
     
      void deleteDuplicateMovies() throws Exception;
      
      void deleteAllData() throws Exception;
      
      Optional<Integer> maxPersonId() throws Exception;
      
      Optional<Integer> maxMovieId() throws Exception;

}
