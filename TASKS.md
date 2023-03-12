# Coding Assignment

---
**DISCLAIMER:**

This coding assignment is intellectual property of etalytics GmbH. Neither the original task with initial project, nor
the solutions developed by you may be passed on or published.
---

For this assignment we modeled a few simplified sub-domains of our real product. The goal of this task is to give
you realistic Java and Spring Boot problems that you could face in your daily work.

How to solve the assignment:

* Get an overview about the different tasks
* Send the assignment back to your recruiter
* We will get back in touch with you and discuss your solution in short session
* There are eleven tasks in total to solve. If you can't solve one of the tasks, just continue.
* Write a short README on how to set up the project and run it
* There are five optional tasks

Optional tasks:

As the name implies, these tasks are optional and can be tackled when the main tasks are finished.

* Create a Docker Image for the assignment and tag it with `eta-assignment:v1.0.0`.
* Create a UML based sequence diagram for creating a Note tagged with the Tag `important`.
* Create OpenAPI documentation for the various endpoints.
* Create a Kubernetes Deployment / Docker Compose file for easy deployment
* Create a Spring Boot native image with GraalVM

## General

## Task 1: Create a Spring Boot Project on Spring Starter

Dependencies:

- Gradle Project
- Lombok
- MapStruct
- JUnit 5

## Task 2: Create CRUD Endpoints for Notebook

POST / Collection GET Endpoint: /notebooks
GET / DELETE / PUT Endpoint: /notebooks/{id}

Notebook Model:

```
Request (PUT, POST):
{
  "title": String, (minSize=3, maxSize=128, NotNull)
  "description": String, (maxSize=1024),
  "tags[]": List of String (Tag Names)
}
```

```
Response (GET):
{
  "id": Number,
  "title": String, (minSize=3, maxSize=128, NotNull)
  "description": String, (maxSize=1024),
  "createdOn": Date, (should be implicitly set on a POST request),
  "lastModified": Date, (should be implicitly set on each create / update [POST, PUT])
  "notes"[]: Array of Note,
  "tags[]": List of Tags
}
```

## Task 3: Create CRUD Endpoints for Note

POST / Collection GET Endpoint: /notebooks/{notebook-id}/notes  
GET / DELETE / PUT Endpoint: /notebooks/{notebook-id}/notes/{note-id}

Note Model:

```
Request (PUT, POST):
{
  "title": String,
  "content": String,
  "tags[]": List of String (Tag Names)
}
```

```
Response (GET):
{
  "id": Number,
  "title": String,
  "content": String,
  "tags[]": List of Tags
}
```

## Task 4: Create CRUD Endpoint for Tag

A Tag can be attached to a Note or to a Notebook.  
If a Tag gets deleted the corresponding Notebook / Note should not be deleted.

POST / Collection GET Endpoint: /tags
GET / DELETE / PUT Endpoint: /tags/{tag-name}

Tag Model:

```
Request (PUT, POST):
{
  "name": String, (minSize=3, maxSize=128, NotNull)
  "color": [RED, GREEN, BLUE], 
}
```

```
Response (GET):
{
  "name": String, (minSize=3, maxSize=128, NotNull)
  "color": String
}
```

## Task 5: Find Notes and Notebooks by Tag

Introduce a Query Parameter to find a NoteBook / Note by a List of Tags

Example: /notebooks?tags=testTag1,TestTag2

## Task 6: Find Notes by Fulltext search

A simple Hotword search for the notes content.

Example: /notebooks/{notebook-id}/notes?contains=blabla

## Task 7: Simplify code by using Lombok

Transform the use of Java vanilla to Lombok (Constructors, Getter, Setter and Value, and optionally use Builders)

## Task 8: Simplify code by using MapStruct for mapping

Use Mapstruct for mapping the output model instead of vanilla Java.
This should be used in the cases where we have to map between a DTO (for instance a create command without an id)
and the other way around.

## Task 9: Write Unit test for the size constrains

Write Unit test for the size constrains for:

- [ ] Note
- [ ] Notebook
- [ ] Tag

## Task 10: Write Web Integration Tests

Write Web Integration tests with @WebMvcTest:

- [ ] Note
- [ ] Notebook
- [ ] Tag

## Task 11: Write Persistence Integration Test

    Write Persistence Integration tests with @DataJpaTest

- [ ] Note
- [ ] Notebook
- [ ] Tag
