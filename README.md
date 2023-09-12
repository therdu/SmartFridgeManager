# SmartFridgeManager - Installation instructions

## Prerequisites

Before you start, ensure you have the following software installed on your development machine:
- Node.js and npm (Node Package Manager): https://nodejs.org/
- Java Development Kit (JDK): https://www.oracle.com/java/technologies/javase-downloads.html
- Apache Maven: https://maven.apache.org/
- PostgreSQL Database: https://www.postgresql.org/download/
- Vue CLI: Install globally using npm install -g @vue/cli

## Database Setup

Ensure the PostgreSQL database is up and running.

Create the necessary database (SmartFridgeManagerDB) and table (items). You can use tools like psql or a GUI tool like pgAdmin for this task.

## Backend Setup

Clone the project from GitHub an navigate to the backend directory.

Configure your PostgreSQL database connection in the Spring Boot application.properties. Replace <DB_URL>, <DB_USERNAME>, and <DB_PASSWORD> with your database details:

    spring.datasource.url=jdbc:postgresql://<DB_URL>:your_port/your_database_name
    spring.datasource.username=<DB_USERNAME>
    spring.datasource.password=<DB_PASSWORD>

Build the backend project using Maven:

    mvn clean install

Run the Spring Boot application, which will start on port 8080.


## Frontend Setup

Clone or set up the Vue 3 frontend project.

Navigate to the frontend project directory and install project dependencies:

    cd frontend-directory
    npm install

Start the Vue 3 development server:

    npm run dev
    
This will start the frontend on port 5173. If the frontend is running on another port, you have to change it in the CorsConfig.java class in the backend to prevent cross origin errors.


## Application Testing

Access the SmartFridgeManager in a web browser at http://localhost:5173.
Test the interaction between your frontend, backend, and database to ensure they work as expected.
