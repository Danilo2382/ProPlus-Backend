# Pro Plus - Spring Boot Project

## Description
Pro Plus is a project management application developed using Spring Boot. The application helps users organize their tasks and projects effectively.

## Current Work
I am currently working on:
- Implementing JWT (JSON Web Tokens) for user authentication.
- Adding DELETE endpoints to enhance API functionality.
- Optimizing code and improving the overall structure.
- Fixing bugs and improving performance.

## Endpoints
Here is a list of available endpoints:

### Authentication
- `POST /auth/login`: Authenticate user.
- `POST /auth/register`: Register a new user.

### User information
- `PUT /user/changeUsername`: Update the user's username.
- `PUT /user/changePassword`: Change the user's password.
- `PUT /user/changeEmail`: Update the user's email address.
- `GET /user/showDetails`: Retrieve information about the authenticated user.

### User Actions
- `POST /actions/joinProject`: Join an existing project as a member.
- `POST /actions/createProject`: Create a new project with specified details.
- `GET /actions/getAllTasks`: Retrieve all tasks assigned to the authenticated user.
- `GET /actions/getAllProjects`: Retrieve all projects associated with the authenticated user.

### Project information
- `PUT /project/changeRole`: Change the role of a user within a project.
- `PUT /project/changePassword`: Change the project's password.
- `GET /project/showDetails`: Retrieve detailed information about a specific project.
- `GET /project/listTasks`: List all tasks associated with a specific project.
- `GET /project/listMembers`: List all members participating in a specific project.

### Task actions
- `PUT /task/finishTask`: Mark a specific task as completed.
- `PUT /task/doTask`: Update the status of a task to indicate it is currently being worked on.
- `PUT /task/addComment`: Add a comment to a specific task.
- `POST /task/createTask`: Create a new task within a project.
- `GET /task/showComments`: Retrieve all comments associated with a specific task.

## Database Schema
The following SQL script is used to create the database schema for the project:

```sql
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema propulsdb
-- -----------------------------------------------------

CREATE SCHEMA IF NOT EXISTS `propulsdb` DEFAULT CHARACTER SET utf8mb3 ;
USE `propulsdb` ;

-- -----------------------------------------------------
-- Table `propulsdb`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `propulsdb`.`project` (
  `idProject` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(1000) NOT NULL,
  `startDate` DATETIME NOT NULL,
  `endDate` DATETIME NOT NULL,
  `createDate` DATETIME NOT NULL,
  `status` INT NOT NULL,
  `code` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idProject`),
  UNIQUE INDEX `idProject_UNIQUE` (`idProject` ASC) VISIBLE,
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table `propulsdb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `propulsdb`.`user` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `secondName` VARCHAR(45) NOT NULL,
  `birthday` DATETIME NOT NULL,
  `joinDate` DATETIME NOT NULL,
  `profilePicture` VARCHAR(500) NULL DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `idUser_UNIQUE` (`idUser` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table `propulsdb`.`projectmember`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `propulsdb`.`projectmember` (
  `idProjectMember` INT NOT NULL AUTO_INCREMENT,
  `role` INT NOT NULL,
  `User_idUser` INT NOT NULL,
  `Project_idProject` INT NOT NULL,
  PRIMARY KEY (`idProjectMember`),
  UNIQUE INDEX `idProjectMember_UNIQUE` (`idProjectMember` ASC) VISIBLE,
  INDEX `fk_ProjectMember_User_idx` (`User_idUser` ASC) VISIBLE,
  INDEX `fk_ProjectMember_Project1_idx` (`Project_idProject` ASC) VISIBLE,
  CONSTRAINT `fk_ProjectMember_Project1`
    FOREIGN KEY (`Project_idProject`)
    REFERENCES `propulsdb`.`project` (`idProject`),
  CONSTRAINT `fk_ProjectMember_User`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `propulsdb`.`user` (`idUser`))
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table `propulsdb`.`task`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `propulsdb`.`task` (
  `idTask` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NOT NULL,
  `createDate` DATETIME NOT NULL,
  `status` INT NOT NULL,
  `attachment` BLOB NULL DEFAULT NULL,
  `Project_idProject` INT NOT NULL,
  `ProjectMember_idProjectMember` INT NOT NULL,
  PRIMARY KEY (`idTask`),
  UNIQUE INDEX `idTask_UNIQUE` (`idTask` ASC) VISIBLE,
  INDEX `fk_Task_Project1_idx` (`Project_idProject` ASC) VISIBLE,
  INDEX `fk_Task_ProjectMember1_idx` (`ProjectMember_idProjectMember` ASC) VISIBLE,
  CONSTRAINT `fk_Task_Project1`
    FOREIGN KEY (`Project_idProject`)
    REFERENCES `propulsdb`.`project` (`idProject`),
  CONSTRAINT `fk_Task_ProjectMember1`
    FOREIGN KEY (`ProjectMember_idProjectMember`)
    REFERENCES `propulsdb`.`projectmember` (`idProjectMember`))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8mb3;

-- -----------------------------------------------------
-- Table `propulsdb`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `propulsdb`.`comment` (
  `idComment` INT NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(200) NOT NULL,
  `timeStamp` DATETIME NOT NULL,
  `Task_idTask` INT NOT NULL,
  `ProjectMember_idProjectMember` INT NOT NULL,
  PRIMARY KEY (`idComment`),
  UNIQUE INDEX `idComment_UNIQUE` (`idComment` ASC) VISIBLE,
  INDEX `fk_Comment_Task1_idx` (`Task_idTask` ASC) VISIBLE,
  INDEX `fk_Comment_ProjectMember1_idx` (`ProjectMember_idProjectMember` ASC) VISIBLE,
  CONSTRAINT `fk_Comment_ProjectMember1`
    FOREIGN KEY (`ProjectMember_idProjectMember`)
    REFERENCES `propulsdb`.`projectmember` (`idProjectMember`),
  CONSTRAINT `fk_Comment_Task1`
    FOREIGN KEY (`Task_idTask`)
    REFERENCES `propulsdb`.`task` (`idTask`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
