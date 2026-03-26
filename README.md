# Movie Manager

A Java Swing desktop application for browsing and managing a movie catalogue. The application fetches movie data from a live RSS feed, persists it in a Microsoft SQL Server database, and supports full CRUD operations with role-based access control.

---

## Project Structure

This is a Maven multi-module project composed of three modules:

```
Paulo_Blazevic_Java_Projekt/   ← parent POM
├── Utilities/                 ← shared helpers (JAXB, parsers, file/icon utils)
├── Dao/                       ← data access layer (models, Repository interface, SQL implementation)
└── MovieManager/              ← Swing GUI application (views, parsers, entry point)
```

### Modules

**Utilities**
Shared library used by the other modules. Contains:
- `JAXBUtils` – generic JAXB marshalling/unmarshalling (save/load XML files)
- `ParserFactory` – creates a StAX `XMLEventReader` from an `InputStream`
- `UrlConnectionFactory` – opens `HttpURLConnection` instances
- `FileUtils` – file I/O helpers
- `IconUtils` – icon loading helpers
- `MessageUtils` – dialog/message utilities

**Dao**
Data access layer. Contains:
- Domain models: `Movie`, `Person`, `ApplicationUser`, `MovieArchive`
- `Titles` enum – person roles (actor/director)
- `Users` enum – application roles (ADMIN/DEFAULT)
- `Repository` interface – full contract for all DB operations
- `SqlRepository` – implementation via MSSQL stored procedures using `CallableStatement`
- `RepositoryFactory` – reads `repository.properties` and reflectively instantiates the correct `Repository`
- `DataSourceSingleton` – reads `db.properties` and provides a shared `DataSource`

**MovieManager**
The runnable Swing application. Contains:
- `MovieManager` – main `JFrame`, entry point
- `JPanelLogin` – login screen with credential validation
- `JPanelAdminCenter` – admin panel (RSS import trigger)
- `JPanelCRUDMovies` – full CRUD UI for movies, with XML export/import for actors and directors
- `JPanelCRUDPeople` – full CRUD UI for people
- `Registration_JFrame` – new user registration
- `MovieParsers` – StAX-based RSS parser that fetches movies from Blitz Cinestar's feed, downloads poster images, strips HTML via Jsoup, and persists to DB
- `MovieTable` / `PersonTable` – custom `AbstractTableModel` implementations

---

## Features

- **RSS Import** – fetches live movie data from `https://www.blitz-cinestar-bh.ba/rss.aspx?id=2682` and saves poster images locally in the `assets/` directory
- **Full CRUD** – create, read, update, and delete movies and people through the GUI
- **XML Export/Import** – export actor/director lists to XML files and import them back using JAXB
- **Role-based access** – `ADMIN` users can access the admin centre and import data; `DEFAULT` users have read-only browse access
- **User registration** – new accounts can be created via the registration form
- **Stored procedure layer** – all database operations go through named SQL Server stored procedures

---

## Prerequisites

| Requirement | Version |
|---|---|
| Java JDK | 20 |
| Maven | 3.x |
| Microsoft SQL Server | any recent version |
| NetBeans IDE | recommended (`.form` files present) |

---

## Database Setup

1. Run the provided SQL script to create the database and seed the default users:

   ```
   SQLQuery3.sql
   ```

   This creates the `MOVIES` database with tables `Person`, `Movie`, `PersonMovie`, `AppUser`, and all required stored procedures.

2. Two default accounts are inserted by the script:

   | Username | Password | Role |
   |---|---|---|
   | `admin` | `admin` | ADMIN |
   | `paulo` | `12345678` | DEFAULT |

---

## Configuration

Edit the properties files in `Dao/src/main/resources/config/` before building:

**`db.properties`** – database connection settings:
```properties
SERVER_NAME = localhost
DATABASE_NAME = MOVIES
USER = sasa
PASSWORD = SQL
```

**`repository.properties`** – selects the active `Repository` implementation:
```properties
# Points to SqlRepository by default
```

---

## Building

From the root of the project (where the parent `pom.xml` lives):

```bash
mvn clean install
```

This builds all three modules in dependency order: `Utilities` → `Dao` → `MovieManager`.

---

## Running

After building, run the MovieManager JAR:

```bash
java -jar MovieManager/target/MovieManager-1.0-SNAPSHOT.jar
```

Or open and run the project directly from NetBeans using the `MovieManager` module as the main project.

---

## Dependencies

| Library | Version | Purpose |
|---|---|---|
| `mssql-jdbc` | 8.4.1.jre14 | SQL Server JDBC driver |
| `jaxb-api` | 2.3.1 | XML binding API |
| `jaxb-impl` | 2.3.4 | XML binding implementation |
| `jsoup` | — | HTML parsing (strips CDATA/HTML from RSS descriptions) |
| JUnit Jupiter | 5.6.0 | Unit testing |

---

## Author

Paulo Blažević  
Faculty of Algebra, Zagreb
