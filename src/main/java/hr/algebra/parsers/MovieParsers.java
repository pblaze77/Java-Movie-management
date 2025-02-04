/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.parsers;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.enums.Titles;
import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import hr.algebra.utilities.FileUtils;
import hr.algebra.view.JPanelAdminCenter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.jsoup.Jsoup;

/**
 *
 * @author Paulo
 */
public class MovieParsers {
    
     public MovieParsers() {
    }
     
     
     
    private static final String RSS_URL = "https://www.blitz-cinestar-bh.ba/rss.aspx?id=2682";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";
    private static final String DIV = "div";
    private static final String COMMA = ",";
    
    private static Repository repository;

    
   
    private static void handlePersonData(String data, Titles role, int id, List<Person> people, int personId) {
          if (!data.isEmpty()) {
            try {
                String names = data.replaceAll("<!\\[CDATA\\[", "").replaceAll("\\]\\]>", "").trim();
                String[] peopleNames = names.split(COMMA);

                for (String personName : peopleNames) {
                    Person person = new Person(personName, role, id);
                    if (!repository.findPerson(person).isPresent()) {
                        person.setId(personId);
                        personId++;
                        people.add(person);
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(JPanelAdminCenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }}

    private static void handlePicture(Movie movie, String url) throws IOException {
        String ext = url.substring(url.lastIndexOf("."));
        if (ext.length() > 4) {
            ext = EXT;
        }
        var imageName = UUID.randomUUID() + ext;
        var localPath = DIR + File.separator + imageName;

        FileUtils.copyFromUrl(url, localPath);
        movie.setPicturePath(localPath);
    }

    private enum TagType {
        ITEM("item"),
        TITLE("title"),
        PUB_DATE("pubDate"),
        DESCRIPTION("description"),
        DIRECTOR("redatelj"),
        ACTORS("glumci"),
        DURATION("trajanje"),
        YEAR("godina"),
        PICTURE("plakat");

        private final String name;

        TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }

   

    public static void parse(int maxMovieId, int maxPersonId) throws IOException, Exception {
      List<Movie> movies = new ArrayList<>();
      List<Person> people = new ArrayList<>();

        int movieId = maxMovieId == 0 ? 1 : maxMovieId;
        int personId = maxPersonId == 0 ? 1 : maxPersonId;

        repository = RepositoryFactory.getRepository();

        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);

        repository.deleteAllData();

        try (InputStream is = con.getInputStream()) {
            XMLEventReader reader = ParserFactory.createStaxParser(is);

            Movie movie = null;
            Person person = null;
            StartElement startElement = null;
            Optional<TagType> tagType = Optional.empty();

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);

                        if (tagType.isPresent() && tagType.get().equals(TagType.ITEM)) {
                            movie = new Movie();
                            movies.add(movie);
                            movie.setId(movieId);
                            movieId++;
                        }
                    }
                    case XMLStreamConstants.CHARACTERS -> {
                        if (tagType.isPresent() && movie != null) {
                            String data = event.asCharacters().getData().trim();
                            if (data.isEmpty()) {
                                continue;
                            }
                            switch (tagType.get()) {
                                case TITLE:
                                    movie.setTitle(data);
                                    break;
                                case PUB_DATE:
                                    try {
                                        movie.setPublishedDate(LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME));
                                    } catch (Exception e) {
                                        Logger.getLogger(MovieParsers.class.getName()).log(Level.SEVERE, "Invalid date format: " + data, e);
                                    }
                                    break;
                                case DESCRIPTION:
                                    org.jsoup.nodes.Document doc = Jsoup.parse(data);
                                    org.jsoup.nodes.Element descriptionEl = doc.selectFirst(DIV);
                                    if (descriptionEl != null) {
                                        String desc = descriptionEl.ownText();
                                        movie.setDescription(desc);
                                    } else {
                                        movie.setDescription(data);
                                    }
                                    break;
                                case DIRECTOR:
                                    handlePersonData(data, Titles.DIRECTOR, movie.getId(), people, personId);
                                    break;
                                case ACTORS:
                                    handlePersonData(data, Titles.ACTOR, movie.getId(), people, personId);
                                    break;
                                case DURATION:
                                    try {
                                        movie.setDuration(Integer.parseInt(data));
                                    } catch (NumberFormatException e) {
                                        Logger.getLogger(MovieParsers.class.getName()).log(Level.SEVERE, "Invalid duration: " + data, e);
                                    }
                                    break;
                                case YEAR:
                                    try {
                                        movie.setYear(Integer.parseInt(data));
                                    } catch (NumberFormatException e) {
                                        Logger.getLogger(MovieParsers.class.getName()).log(Level.SEVERE, "Invalid year: " + data, e);
                                    }
                                    break;
                                case PICTURE:
                                    handlePicture(movie, data);
                                    break;
                                default:
                                    throw new AssertionError();
                            }
                        }
                    }
                }
            }

            Set<Movie> uniqueMovies = new HashSet<>(movies);
            Set<Person> uniquePeople = new HashSet<>(people);
            for (Movie m : uniqueMovies) {
                if (!repository.findMovie(m.getTitle()).isPresent()) {
                    repository.createMovie(m);
                } else {
                    repository.updateMovie(m.getId(), m);
                }
            }

            for (Person p : uniquePeople) {
                if (!repository.findPerson(p).isPresent()) {
                    repository.createPerson(p);
                } else {
                    repository.updatePerson(p.getId(), p);
                }
            }
        }
    }
}
