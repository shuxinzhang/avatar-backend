# avatar-backend
A simple RESTAPI which stores avatar information.
This app is using SparkJava's webframework support on RESTAPI (http://sparkjava.com/).
requirement: java SDK 1.8+, Maven 3.0+.


## Installation instructions
1. Clone this repo and make this repo the working directory.
2. Run `mvn clean` 
3. Run `mvn package`
4. Run `java -jar target/avatar-backend-1.0-jar-with-dependencies.jar`
5. The server will start at http://localhost:3000 

## query currently supported:
`GET /avatar/:index` return the avatar information with the index(0-99) specified.

`GET /avatars/page/:pageNumber`: return the avatar at the specific page(1-10).

`GET /avatars/page/:pageNumber/sort/:sortFilter` : return the avatar at the specific page(1-10) sorted by a specific feature("eyes","noses","mouths")

# to-dos 
(backend function support already done, only to write APIs)
`POST /avatars/reload` reload the 100 avatars stored from database to 100 new ones.

`PUT /avatar/:eye/:nose/:mouth/:color` add avatar to index.

`DELETE /avatar/:eye/:nose/:mouth/:color` Delete a specified avatar.


