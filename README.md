# Pro Plus - Spring Boot Project

## Description
Pro Plus is a project management application developed using Spring Boot. The application helps users organize their tasks and projects effectively.

## Current Work
I am currently working on:
- Implementing new security filters.
- Adding new functionalities.
- Adding DELETE endpoints to enhance API functionality.
- Improving validation for better data integrity and security.

## Endpoints
Here is a list of available endpoints:

### Authentication
- `POST /auth/login`: Authenticate user.
- `POST /auth/register`: Register a new user.

### User information
- `GET /user/show`: Retrieve information about the authenticated user.
- `PATCH /user/changeUsername`: Update the user's username (Login needed after successful change).
- `PATCH /user/changePassword`: Change the user's password.
- `PATCH /user/changeEmail`: Update the user's email address.

### User Actions
- `GET /action/projects`: Retrieve all projects assigned to the authenticated user.
- `GET /action/tasks`: Retrieve all tasks assigned to the authenticated user.
- `POST /action/createProject`: Create a new project with specified details.
- `POST /action/joinProject`: Join an existing project as a member.

### Project information
- `GET /project/show`: Retrieve detailed information about a specific project.
- `GET /project/members`: List all members participating in a specific project.
- `GET /project/tasks`: List all tasks associated with a specific project.
- `PATCH /project/changeRole`: Change the role of a user within a project.
- `PATCH /project/changePassword`: Change the project's password.

### Task actions
- `GET /task/comments`: Retrieve all comments associated with a specific task.
- `POST /task/create`: Create a new task within a project.
- `POST /task/addComment`: Add a comment to a specific task.
- `PATCH /task/finish`: Mark a specific task as completed.

## Database Schema
I used Docker to run the MySQL server and DBeaver for database management. The database is automatically created when the Spring Boot application starts.
