# Task Management Application

Simple task management application APi built on spring boot

## Glorious features include
- in memory storage because who needs dAtaBaSes!
- a single CRUD tasks API point to make, query update and delete tasks ( revolutionary! )

## Prerequisites
- Java 21
- Gradle

## API Endpoints
- GET /tasks/all (get all tasks)
- GET /tasks (get tasks based on sort and pagination)
- GET /tasks/{id} (get a task based on Id)
- POST /tasks (add new tasks)
- PUT /tasks/{id} (replace task with another)
- PUT /tasks/status/{id) (update task status)
- DELETE /tasks/{id} (Remove task based on Id)