1. #### This is a web application based on the following stack:
    - Spring Boot
    - Hibernate
    - in-memory H2 database
    - Gradle Project
    - Lombok
    - MapStruct
    - JUnit 5

   The list of entities is following:
    - NotebookEntity
    - NoteEntity
    - TagEntity

   A notebook, a note and a tag are created upon starting.

2. #### How to build

   Type 'gradlew clean build' to build app.jar.

   The server port is specified in application.properties (server.port=8080)

   To build a docker image and run it, type the following command:
   1. docker build -t eta-assignment:v1.0.0 .
   2. docker-compose up

3. #### How to run

   Type either of the following commands:
    -  gradlew clean build && java -jar build/app.jar
    -  gradlew bootRun
    -  docker-compose up  (the docker image must be built)

4. The Swagger 2.0 / OpenAPI specification of the service can be found on http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#


