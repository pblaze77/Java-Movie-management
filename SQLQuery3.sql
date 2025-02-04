CREATE DATABASE MOVIES
GO
USE MOVIES
GO



CREATE TABLE Person
(
	IDPerson int primary key identity,
	Name nvarchar(50),
	Role nvarchar(10)
)

CREATE TABLE Movie
(
	IDMovie int primary key identity,
	Title nvarchar(300),
	PublishedDate nvarchar(90),
	Description nvarchar(900),
	PicturePath nvarchar(300),	
    Duration int,
    Year int
)

CREATE TABLE PersonMovie
(
    IDPersonMovie int primary key identity,
    PersonID int,
    MovieID int,
    RoleInMovie nvarchar(10),
    FOREIGN KEY (PersonID) REFERENCES Person(IDPerson),
    FOREIGN KEY (MovieID) REFERENCES Movie(IDMovie)
);



CREATE TABLE AppUser
(
	IDAppUser int primary key identity,
	Username nvarchar(50),
	Password nvarchar(100),
	Role nvarchar(10)
)



insert into AppUser values ('admin', 'admin', 'ADMIN');
insert into AppUser values ('paulo', '12345678', 'DEFAULT')
go



CREATE PROCEDURE selectUser
	@Username nvarchar(50),
	@Password nvarchar(100)
AS 
BEGIN 
	SELECT 
		IDAppUser, Role 
	FROM 
		AppUser
	WHERE 
		Username = @Username
	AND
		Password = @Password

END
GO

CREATE PROCEDURE createUser
	@Username nvarchar(50),
	@Password nvarchar(100),
	@Role nvarchar(10),
	@IDAppUser int output
AS 
BEGIN 
	INSERT INTO AppUser VALUES(@Username, @Password, @Role)
	SET @IDAppUser = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE createPerson
	@Name nvarchar(50),
	@Role nvarchar(10),
	@IDPerson int output
AS 
BEGIN 
	INSERT INTO Person VALUES(@Name, @Role)
	SET @IDPerson = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE selectPerson
	@IDPerson INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Person
	WHERE 
		IDPerson = @IDPerson
END
GO

CREATE PROCEDURE findPerson
	@Name nvarchar(50),
	@Role nvarchar(10)
AS 
BEGIN 
	SELECT 
		p.IDPerson
	FROM 
		Person as p
	WHERE 
		Name = @Name AND Role = @Role
END
GO

CREATE PROCEDURE findMovie
	@Title nvarchar(300)
AS 
BEGIN 
	SELECT 
		m.IDMovie
	FROM 
		Movie as m
	WHERE 
		Title=@Title
END
GO

CREATE PROCEDURE deletePerson
	@IDPerson INT 
AS 
BEGIN 
	DELETE  
	FROM 
			Person
	WHERE 
		IDPerson = @IDPerson
END
GO

CREATE PROCEDURE updatePerson
	@Name nvarchar(50),
	@Role nvarchar(10),
	@IDPerson int
AS 
BEGIN 
	UPDATE Person SET 
		Name = @Name,
		Role = @Role		
	WHERE 
		IDPerson = @IDPerson
END
GO

CREATE PROCEDURE selectPeople
AS 
BEGIN 
	SELECT * FROM Person
END
GO

CREATE PROCEDURE selectActors
AS 
BEGIN 
	SELECT * FROM Person
	WHERE Role='ACTOR'
END
GO

CREATE PROCEDURE selectDirectors
AS 
BEGIN 
	SELECT * FROM Person
	WHERE Role='DIRECTOR'
END
GO

CREATE PROCEDURE selectMovies
AS 
BEGIN 
	SELECT * FROM Movie
END
GO

CREATE PROCEDURE createMovie
	@Title nvarchar(300),
	@PublishedDate nvarchar(90),
	@Description nvarchar(900),
	@PicturePath nvarchar(300),
	@Duration int,
	@Year int,
	@IDMovie int output
AS 
BEGIN 
	INSERT INTO Movie VALUES(@Title, @PublishedDate, @Description, @PicturePath, @Duration, @Year)
	SET @IDMovie = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE selectMovie
	@IDMovie INT
AS 
BEGIN 
	SELECT 
		* 
	FROM 
		Movie
	WHERE 
		IDMovie = @IDMovie
END
GO

CREATE PROCEDURE updateMovie
	@Title nvarchar(300),
	@PublishedDate nvarchar(90),
	@Description nvarchar(900),
	@PicturePath nvarchar(300),
	@Duration int,
	@Year int,
	@IDMovie int
AS 
BEGIN 
	UPDATE Movie SET 
		Title = @Title,
		PublishedDate = @PublishedDate,
		Description = @Description,
		PicturePath = @PicturePath,
		Duration = @Duration,
		[Year] = @Year
	WHERE 
		IDMovie = @IDMovie
END
GO

CREATE PROCEDURE createPersonInMovie
	@PersonID int,
	@MovieID int,
	@RoleInMovie nvarchar(10),
	@IDPersonMovie int output
AS 
BEGIN 
	INSERT INTO PersonMovie VALUES(@PersonID, @MovieID, @RoleInMovie)
	SET @IDPersonMovie = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE selectPeopleInMovie
	@MovieID INT
AS 
BEGIN
	SELECT
		p.IDPerson,
		p.Name,
		p.Role
	FROM Person as p
	JOIN PersonMovie as pm on p.IDPerson = pm.PersonID
	WHERE pm.MovieID = @MovieID
END
GO

CREATE PROCEDURE deletePeopleInMovie
	@MovieID INT 
AS 
BEGIN 
	DELETE  
	FROM 
			PersonMovie
	WHERE 
		MovieID = @MovieID
END
GO

CREATE PROCEDURE deleteMovie
	@IDMovie INT 
AS 
BEGIN 
	DELETE  
	FROM 
			Movie
	WHERE 
		IDMovie = @IDMovie
END
GO

CREATE PROCEDURE deleteAllData
AS
BEGIN
	DELETE FROM PersonMovie
	DELETE FROM Person
	DELETE FROM Movie

	drop table PersonMovie
	drop table Movie
	drop table Person

	CREATE TABLE Person
	(
		IDPerson int primary key identity,
		Name nvarchar(50),
		Role nvarchar(10)
	)

	CREATE TABLE Movie
	(
		IDMovie int primary key identity,
		Title nvarchar(300),
		PublishedDate nvarchar(90),
		Description nvarchar(900),
		PicturePath nvarchar(300),	
		Duration int,
		Year int
	)

	CREATE TABLE PersonMovie
	(
		IDPersonMovie int primary key identity,
		PersonID int,
		MovieID int,
		RoleInMovie nvarchar(10),
		FOREIGN KEY (PersonID) REFERENCES Person(IDPerson),
		FOREIGN KEY (MovieID) REFERENCES Movie(IDMovie)
	);
END
GO

CREATE PROCEDURE deleteDuplicateMovies
AS
BEGIN
	DELETE m1
	FROM Movie m1
	JOIN Movie m2 ON m1.Title = m2.Title
		AND m1.IDMovie > m2.IDMovie;	
END
GO


CREATE PROCEDURE maxMovieId
AS
BEGIN
	SELECT MAX(IDMovie) FROM Movie;
END
GO

CREATE PROCEDURE maxPersonId
AS
BEGIN
	SELECT MAX(IDPerson) FROM Person;
END
GO

