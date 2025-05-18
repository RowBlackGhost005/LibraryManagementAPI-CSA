# Library Management System API
A small REST API developed in Spring Boot to register books.

# How to Execute
### Requirements:
- Java 21+
- Maven
- MySQL

### Steps to execute
1. Clone the repo
2. Go to your MySQL and create the database with the name `LibraryCatalogCSA`
3. Integrate your database (Go to src/main/resources/application.properties) and change your database credentials as follows (Remove #yourUser/#yourPass):
```
spring.datasource.username=#yourUser
spring.datasource.password=#yourPass
```
4. Execute the main file `LibraryManagementCsaApplication` under src with your IDE to start the API.
5. Wait till the embeeded tomcat server initializes.

### API URL
Once the API is up and running you should be able to access it in the following url:
		http://localhost:8080

### Swagger
You can follow up this file to see examples of how to call this API or you can go into its swagger page to see its doc and test the API. Once the app is up and running you shuld be able to access swagger with this endpoint:
`http://localhost:8080/docs/swagger-ui/index.html`

### ENDPOINTS
All the endpoint in this API are under /books, so all your HTTP methods should be directed into: `http://localhost:8080/books` , below you can see a table with examples as to how to call the API

| Method  | Endpoint  | Description  | Note  |
| ------------ | ------------ | ------------ | ------------ |
| POST  | /books  | Saves in the API Database the book sent in the body. Note that it should contain all the fields as the example provided otherwise it will return a bad request status.  | **Body:** `{ "title": "Dune: Children of Dune", "author": "Frank Hebert", "isbn": "1234151678569", "publishedDate": "1970-01-01", "genre": "Science Fiction" }`  |
| PUT | /books/*{id}*  | Updates the book that has the same ID as the path param with the data sent in the body of this request. Note that in this case you only need to send the fields to update  | **Body:** `{ "title" : "Childrens of Dune"}`  |
| DELETE  | books/{id}  | Deletes the book that has the same ID as the path param. | **Response:** Returns a 1 if deletion was completed |
| GET  | /books  | Returns a JSON with a list of all books registered in this API  | **Response:** Returns a JSON Array with the books  |
| GET  | /books/{id}  | Returns a book whose ID match the path param. Note that it will return a 404 status if there is no book with such ID.   | **Response:** Returns the book as a JSON object  |
| GET  | /books/titles/{title}  | Returns a list of books that contains the exact sequence of characters as the given as path param in any part of the book title  | **Response:** Returns a JSON array with the matches. |
| GET  | /books/authors/{author}  | Returns a list of books that have the author name as the author param  | **Response:** Returns a JSON array with the matches.  |

---
Developed by Luis Marin.
