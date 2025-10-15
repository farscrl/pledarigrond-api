## Pledari Grond - Java API

This repository contains the code for the Java API of Pledari Grond, a Romansh language dictionary and grammar resource.

## Technology Stack

* **Java**
* **Spring Boot** - Web framework and dependency injection
* **MongoDB** - Document database for dictionary data
* **Apache Lucene** - Full-text search indexing
* **Maven** - Build and dependency management
* **Docker** - Containerization for local development

## Development Setup

### Prerequisites

* **Java 21**
* **Maven 3.6+**
* **Docker** (for local MongoDB instance)

### Getting Started

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd pg
   ```

2. **Start MongoDB with Docker:**
   ```bash
   docker-compose up -d
   ```
   This will start:
   - MongoDB on port `27000` (accessible with `rootuser`/`rootpass`)
   - Mongo Express (web UI) on port `8081` (login with `admin`/`pass`)

3. **Configure the application:**
   Edit `api/src/main/resources/application.properties` to adjust settings as needed. The default configuration connects to the Docker MongoDB instance.

4. **Build the project:**
   ```bash
   mvn clean install
   ```

5. **Run the API:**
   ```bash
   cd api
   mvn spring-boot:run
   ```
   The API will be available at `http://localhost:8080`

### Configuration

Project configuration is located in `api/src/main/resources/application.properties`. Key settings include:

* **Database connection:** MongoDB host, port, credentials
* **Admin user:** Default admin user credentials (configure on first startup)
* **Data directories:** Locations for Lucene indexes, backups, exports, and corpus data
* **JWT secret:** For authentication tokens

## Data Directory

The `data/` directory contains various runtime and persistent data:

* **backup/** - Automated backups of dictionary databases
* **corpus/** - Text corpus files from La Quotidiana
* **db_dump/** - Database dump files for export/import
* **export/** - Generated export files (dictionary exports)
* **hunspell/** - Spellchecker files and Git repository
* **lucene_index/** - Apache Lucene search indexes


## Project Structure

The repository consists of several modules:

* **api:** Spring Boot application handling web requests to the API.
* **common:** Data definitions used across different modules.
* **corpus:** A simple corpus of La Quotidiana texts used to allow the editors to search for examples.
* **dictionary:** Handles dictionary data persistence in MongoDB, including data models and repository layer.
* **inflection:** Logic for inflecting words (conjugation, pluralization) in various Romansh idioms. Conjugation generation partially relies on a copy of the Maalr's `maalr.conjugator` module.
* **lucene:** Creates Apache Lucene indexes for dictionary data. This includes one main index and two suggestion indexes (German and Romansh) for cases where no exact search results are found.
* **names:** Manages storage of names (typical Romansh first/last names, geographical names, etc.) used by spellcheckers.
* **parent:** Parent module for all project modules, defining common pom entries.
* **pronunciation:** Handles audio pronunciation generation and conversion using JAVE (Java Audio Video Encoder).
* **spellchecker:** Handles generation of spellchecker files.
* **users:** Manages user data and authentication in MongoDB.

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.
