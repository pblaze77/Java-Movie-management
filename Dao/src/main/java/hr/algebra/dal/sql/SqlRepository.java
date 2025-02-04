/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.enums.Titles;
import hr.algebra.enums.Users;
import hr.algebra.model.ApplicationUser;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.sql.DataSource;

/**
 *
 * @author Paulo
 */
public class SqlRepository implements  Repository{

    private static final String CREATE_PERSON = "{ CALL createPerson (?,?,?) }";
    private static final String UPDATE_PERSON = "{ CALL updatePerson (?,?,?) }";
    private static final String DELETE_PERSON = "{ CALL deletePerson (?) }";
    private static final String SELECT_PERSON = "{ CALL selectPerson (?) }";
    private static final String SELECT_PEOPLE = "{ CALL selectPeople }";
    private static final String SELECT_ACTORS = "{ CALL selectActors }";
    private static final String SELECT_DIRECTORS = "{ CALL selectDirectors }";
    private static final String FIND_PERSON = "{ CALL findPerson (?,?) }";
    private static final String FIND_MOVIE = "{ CALL findMovie (?) }";
    
    private static final String ID_PERSON = "IDPerson";
    private static final String NAME = "Name";
    private static final String ROLE = "Role";
    
    private static final String FK_PERSON_ID = "PersonID";
    private static final String FK_MOVIE_ID = "MovieID";
    private static final String ROLE_IN_MOVIE = "RoleInMovie";
    private static final String ID_PERSON_MOVIE = "IDPersonMovie";
    
    private static final String ID_APP_USER = "IDAppUser";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String APP_ROLE = "Role";
    
    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String PUBLISHED_DATE = "PublishedDate";
    private static final String DESCRIPTION = "Description";
    private static final String PICTURE_PATH = "PicturePath";
    private static final String DURATION = "Duration";
    private static final String YEAR = "Year";
    
    private static final String CREATE_PERSON_IN_MOVIE = "{ CALL createPersonInMovie (?,?,?,?) }";
    private static final String SELECT_PEOPLE_IN_MOVIE = "{ CALL selectPeopleInMovie (?) }";
    private static final String DELETE_PEOPLE_IN_MOVIE = "{ CALL deletePeopleInMovie (?) }";
    
    private static final String CREATE_USER = "{ CALL createUser (?,?,?,?) }";
    private static final String SELECT_USER = "{ CALL selectUser (?,?) }";
    
    private static final String MAX_MOVIE_ID = "{ ? = call maxMovieId }";
    private static final String MAX_PERSON_ID = "{ ? = call maxPersonId }";
    
    private static final String CREATE_MOVIE = "{ CALL createMovie (?,?,?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL updateMovie (?,?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL deleteMovie (?) }";
    private static final String SELECT_MOVIE = "{ CALL selectMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL selectMovies }";
    
    private static final String DELETE_ALL_DATA = "{ CALL deleteAllData }";
    private static final String DELETE_DUPLICATE_MOVIES = "{ CALL deleteDuplicateMovies }";
     
    @Override
    public int createPerson(Person person) throws Exception {
      DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_PERSON);) {
            stmt.setString(NAME, person.getName());
            stmt.setString(ROLE, person.getTitle().name());

            stmt.registerOutParameter(ID_PERSON, Types.INTEGER);
            stmt.executeUpdate();

            return stmt.getInt(ID_PERSON);
        }
 }

    @Override
    public void updatePerson(int id, Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_PERSON);) {
            stmt.setString(NAME, person.getName());
            stmt.setString(ROLE, person.getTitle().name());

            stmt.setInt(ID_PERSON, id);
            stmt.executeUpdate();
        } }

    @Override
    public void deletePerson(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_PERSON);) {

            stmt.setInt(ID_PERSON, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Person> selectPerson(int id) throws Exception {
      DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_PERSON);) {

            stmt.setInt(ID_PERSON, id);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(new Person(
                            rs.getInt(ID_PERSON),
                            rs.getString(NAME),
                            Titles.fromString(rs.getString(ROLE))
                    ));
                }
            }

        }
        return Optional.empty();
    }

    @Override
    public Optional<Integer> findPerson(Person person) throws Exception {
       DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(FIND_PERSON);) {
            stmt.setString(NAME, person.getName());
            stmt.setString(ROLE, person.getTitle().name());

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.ofNullable(rs.getInt(ID_PERSON));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void createPeople(List<Person> people) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_PERSON);) {

            for (Person person : people) {
                stmt.setString(NAME, person.getName());
                stmt.setString(ROLE, person.getTitle().name());
                stmt.registerOutParameter(ID_PERSON, Types.INTEGER);
                stmt.executeUpdate();
            }

        }
    }

    @Override
    public List<Person> selectPeople() throws Exception {
        
        List<Person> people = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_PEOPLE); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                people.add(new Person(
                        rs.getInt(ID_PERSON),
                        rs.getString(NAME),
                        Titles.fromString(rs.getString(ROLE))
                ));
            }
        }

        return people;
    }

    @Override
    public List<Person> selectDirectors() throws Exception {
         List<Person> directors = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_DIRECTORS); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                directors.add(new Person(
                        rs.getInt(ID_PERSON),
                        rs.getString(NAME),
                        Titles.fromString(rs.getString(ROLE))
                ));
            }
        }

        return directors;
    }

    @Override
    public List<Person> selectActors() throws Exception {
         List<Person> actors = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ACTORS); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                actors.add(new Person(
                        rs.getInt(ID_PERSON),
                        rs.getString(NAME),
                        Titles.fromString(rs.getString(ROLE))
                ));
            }
        }

        return actors;
    }

    @Override
    public void createCast(int createdMovieId, Set<Person> people) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_PERSON_IN_MOVIE);) {

            for (Person person : people) {
                stmt.setInt(FK_PERSON_ID, person.getId());
                stmt.setInt(FK_MOVIE_ID, createdMovieId);
                stmt.setString(ROLE_IN_MOVIE, person.getTitle().name());

                stmt.registerOutParameter(ID_PERSON_MOVIE, Types.INTEGER);
                stmt.executeUpdate();
            }

        }
    }

    @Override
    public int createCastPerson(int movieId, int personId, Person person) throws Exception {
      DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_PERSON_IN_MOVIE);) {

            stmt.setInt(FK_PERSON_ID, personId);
            stmt.setInt(FK_MOVIE_ID, movieId);
            stmt.setString(ROLE_IN_MOVIE, person.getTitle().name());

            stmt.registerOutParameter(ID_PERSON_MOVIE, Types.INTEGER);
            stmt.executeUpdate();

            return stmt.getInt(ID_PERSON_MOVIE);
        }
    }

    @Override
    public Set<Person> selectCast(int movieId) throws Exception {
        Set<Person> castSet = new HashSet<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_PEOPLE_IN_MOVIE);) {

            stmt.setInt(FK_MOVIE_ID, movieId);

            try (ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    castSet.add(new Person(
                            rs.getInt(ID_PERSON),
                            rs.getString(NAME),
                            Titles.fromString(rs.getString(ROLE))
                    ));
                }
            }
        }
        return castSet;
    }

    
    
    @Override
    public void deleteCast(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_PEOPLE_IN_MOVIE);) {

            stmt.setInt(FK_MOVIE_ID, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public int createUser(ApplicationUser appUser) throws Exception {
       DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_USER);) {
            stmt.setString(USERNAME, appUser.getUsername());
            stmt.setString(PASSWORD, appUser.getPassword());
            stmt.setString(APP_ROLE, appUser.getRole().name());

            stmt.registerOutParameter(ID_APP_USER, Types.INTEGER);
            stmt.executeUpdate();

            return stmt.getInt(ID_APP_USER);
        }
    }

    
    @Override
    public Optional<ApplicationUser> selectUser(String username, String password) throws Exception {
       DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_USER);) {

            stmt.setString(USERNAME, username);
            stmt.setString(PASSWORD, password);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(new ApplicationUser(
                            rs.getInt(ID_APP_USER),
                            Users.fromString(rs.getString(APP_ROLE))
                    ));
                }
            }

        }
        return Optional.empty();
    }

    @Override
    public int createMovie(Movie movie) throws Exception {
       DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE);) {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(PUBLISHED_DATE, movie.getPublishedDate()
                    .format(Movie.DATE_FORMATTER));
            stmt.setString(DESCRIPTION, movie.getDescription());
            stmt.setString(PICTURE_PATH, movie.getPicturePath());
            stmt.setInt(DURATION, movie.getDuration());
            stmt.setInt(YEAR, movie.getYear());

            stmt.registerOutParameter(ID_MOVIE, Types.INTEGER);
            stmt.executeUpdate();

            return stmt.getInt(ID_MOVIE);
        }
    }

    @Override
    public void createMovies(List<Movie> movies) throws Exception {
   DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE);) {

            for (Movie movie : movies) {
                stmt.setString(TITLE, movie.getTitle());
                stmt.setString(PUBLISHED_DATE, movie.getPublishedDate()
                        .format(Movie.DATE_FORMATTER));
                stmt.setString(DESCRIPTION, movie.getDescription());
                stmt.setString(PICTURE_PATH, movie.getPicturePath());
                stmt.setInt(DURATION, movie.getDuration());
                stmt.setInt(YEAR, movie.getYear());

                stmt.registerOutParameter(ID_MOVIE, Types.INTEGER);
                stmt.executeUpdate();
            }
        }
    }

    
    
    @Override
    public void updateMovie(int id, Movie movie) throws Exception {
       DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_MOVIE);) {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(PUBLISHED_DATE, movie.getPublishedDate()
                    .format(Movie.DATE_FORMATTER));
            stmt.setString(DESCRIPTION, movie.getDescription());
            stmt.setString(PICTURE_PATH, movie.getPicturePath());
            stmt.setInt(DURATION, movie.getDuration());
            stmt.setInt(YEAR, movie.getYear());

            stmt.setInt(ID_MOVIE, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteMovie(int id) throws Exception {
          DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_MOVIE);) {

            stmt.setInt(ID_MOVIE, id);
            stmt.executeUpdate();
        }
       }

    
    @Override
    public Optional<Movie> selectMovie(int id) throws Exception {
       DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MOVIE);) {

            stmt.setInt(ID_MOVIE, id);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(new Movie(
                            rs.getInt(ID_MOVIE),
                            rs.getString(TITLE),
                            LocalDateTime.parse(
                                    rs.getString(PUBLISHED_DATE),
                                    Movie.DATE_FORMATTER),
                            rs.getString(DESCRIPTION),
                            rs.getString(PICTURE_PATH),
                            rs.getInt(DURATION),
                            rs.getInt(YEAR)
                    ));
                }
            }
        }
        return Optional.empty();
    }

    
    
    @Override
    public List<Movie> selectMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MOVIES); ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt(ID_MOVIE),
                        rs.getString(TITLE),
                        LocalDateTime.parse(
                                rs.getString(PUBLISHED_DATE),
                                Movie.DATE_FORMATTER),
                        rs.getString(DESCRIPTION),
                        rs.getString(PICTURE_PATH),
                        rs.getInt(DURATION),
                        rs.getInt(YEAR)
                ));
            }
        }

        return movies;
    }

    
    @Override
    public Optional<Integer> findMovie(String title) throws Exception {
       DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(FIND_MOVIE);) {
            stmt.setString(TITLE, title);

            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.ofNullable(rs.getInt(ID_MOVIE));
                }
            }
        }
        return Optional.empty();
    }

    
    @Override
    public void deleteDuplicateMovies() throws Exception {
         DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_DUPLICATE_MOVIES);) {
            stmt.executeUpdate();
        }
       }

    @Override
    public void deleteAllData() throws Exception {
         DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_ALL_DATA);) {
            stmt.executeUpdate();
        }
       }

    
    @Override
    public Optional<Integer> maxPersonId() throws Exception {
         DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(MAX_PERSON_ID);) {

            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.execute();

            int result = stmt.getInt(1);

            if (!stmt.wasNull()) {
                return Optional.of(result);
            }
        }
        return Optional.empty();
   }

    
    
    @Override
    public Optional<Integer> maxMovieId() throws Exception {
         DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(MAX_MOVIE_ID);) {

            stmt.registerOutParameter(1, Types.INTEGER);
            stmt.execute();

            int result = stmt.getInt(1);

            if (!stmt.wasNull()) {
                return Optional.of(result);
            }
        }
        return Optional.empty();
    }
  }
