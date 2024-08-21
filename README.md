## Pledari Grond - Java API

This repository contains the code for the Java API of Pledari Grond, a Romansh language dictionary and grammar resource.

The code represents a significant refactoring of the [original Pledari Grond code](https://github.com/plattafurma-libra/pledari-grond/tree/surmiran), which was based on the [Maalr project](https://spinfo.phil-fak.uni-koeln.de/forschung/abgeschlossene-forschungsprojekte/maalr-a-modern-approach-to-aggregate-lexical-resources).

## Running the Project

### Prerequisites:

* **MongoDB:** A running instance of MongoDB is required. For local development, you can use `docker-compose.yaml` to start a Docker container with MongoDB.

### Configuration:

Project configuration is located in the `application.properties` file within the API module. This file also allows you to set the admin user password.


## Project Structure

The repository consists of several modules:

* **api:** Spring Boot application handling web requests to the API.
* **common:** Data definitions used across different modules.
* **inflection:** Logic for inflecting words (conjugation, pluralization) in various Romansh idioms. Conjugation generation partially relies on a copy of the Maalr's `maalr.conjugator` module.
* **lucene:** Creates Apache Lucene indexes for dictionary data. This includes one main index and two suggestion indexes (German and Romansh) for cases where no exact search results are found.
* **mongodb:** Handles persistent storage of dictionary data in MongoDB. This module heavily utilizes a copy of Maalr's `maalr.mongo` module.
* **names:** Manages storage of names (typical Romansh first/last names, geographical names, etc.) used by spellcheckers.
* **parent:** Parent module for all project modules, defining common pom entries.
* **spellchecker:** Handles generation of spellchecker files.
