# Pledari Grond - API

This repository contains the code for the Java-API of the Pledari Grond. 

The code in this repository is a big refactoring of the [old code of the Pledari Grond](https://github.com/plattafurma-libra/pledari-grond/tree/surmiran), witch was based on [Maalr](https://spinfo.phil-fak.uni-koeln.de/forschung/abgeschlossene-forschungsprojekte/maalr-a-modern-approach-to-aggregate-lexical-resources). 

## Running the project
To run the Pledari Grond application, you must run a MongoDB. For local development, you can run the `docker-compose.yaml`. This starts a docker container with a mongodb.

All the project configuration can be done in the `application.properties` of the API module. There you can also define the password of the admin user. 

## Project structure

It consists of the following modules: 

### api
The API-module is a Spring Boot application. It handles the Web-Requests to the API. 

### common
The common module contains data definitions used by different modules.

### inflection
The inflection module has the logic to inflect words (conjugate, create plural forms...) in different Romansh idioms.

The generation of the conjugations is partially based on a copy of this [Maalr](https://github.com/plattafurma-libra/pledari-grond/tree/surmiran/maalr.conjugator) module.

### lucene
The lucene module creates Lucene Indexes of all entries of the dictionary. It is used to perform the searches on.

There are two lucene indexes that are created: one on the hard disk and another one just in RAM. Searches are always executed on the index in RAM. When the application starts, the index of the hard disk is loaded into RAM. 

This module is heavily based on a copy of this [Maalr](https://github.com/plattafurma-libra/pledari-grond/tree/surmiran/maalr.lucene) module.

### mongodb
The mongodb module handles the persistent storage of the dictionary data in a MongoDB.

This module is heavily based on a copy of this [Maalr](https://github.com/plattafurma-libra/pledari-grond/tree/surmiran/maalr.mongo) module.

### names
The names module handles the storage of Names (typical romansh firstnames, lastnames, geographical names,...) used for the spellcheckers.

This module is heavily based on a copy of this [Maalr](https://github.com/plattafurma-libra/pledari-grond/tree/surmiran/maalr.mongo) module.

### parent
The parent-module is parent for all modules in this repository and allows defining common pom entries.

### spellchecker
The spellchecker module handles the generation of the spellchecker files.

This module is heavily based on a copy of this [Maalr](https://github.com/plattafurma-libra/pledari-grond/tree/surmiran/maalr.mongo) module.
