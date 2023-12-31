#  <p style="color: blue;">Auto Service API</p>

Auto Service API is a web service specifically designed for auto service centers, providing convenient and efficient tools to manage data about mechanics, vehicles, vehicle owners, and service orders. With this API, auto service centers can optimize their workflow, streamline customer interactions, and enhance service quality.

## ![#f03c15](https://via.placeholder.com/15/f03c15/000000?text=+) Key Features and Functionality:

- Mechanics Management: Add new mechanics, edit their details, and retrieve a list of completed jobs.<br>
- Vehicle Information Management: Add new vehicles, edit vehicle details, and retrieve owner information.<br>
- Efficient Service Order Management: Create new service orders, edit order details, and track order status (accepted, in progress, successfully completed, unsuccessfully completed, paid).<br>
- Service Order Cost Calculation: Automatically calculate the total cost of an order, including the cost of services and items included.<br>
- Service Management: Create new services, edit service details, and manage service status (paid, unpaid to mechanic).<br>
- Item Management: Add new items, edit item details.<br>
- Comprehensive Documentation and Documented API: Utilize Swagger for automated API documentation generation, enabling developers to quickly understand and utilize the API.<br>
Auto Service API helps auto service centers improve efficiency, reduce administrative overhead, and streamline customer interactions. By providing convenient and powerful tools for data and order management, it allows service centers to focus on delivering quality services to their customers and enhance customer satisfaction.

## ![#f03c15](https://via.placeholder.com/15/f03c15/000000?text=+) Technologies Used
* ### Spring boot
* ### Spring Security
* ### Hibernate
* ### PostgreSQL
* ### Liquibase
* ### JUnit
* ### Testcontainers
* ### Swagger
* ### Maven
* ### Git

## ![#f03c15](https://via.placeholder.com/15/f03c15/000000?text=+) Installation and Configuration

#### Step 1: Clone the repository
To start, clone the project repository from GitHub by running the following command in your preferred command-line interface:<br>
git clone git@github.com:dimonkoval/auto-service-api.git<br>
This will create a local copy of the project on your machine

#### Step 2: Configure the application
Open the application.properties file located in the project's root directory. This file contains the application configuration settings, including the database connection details. Update the following properties with your specific values:<br>
spring.datasource.url=jdbc:postgresql://localhost:5432/auto_service_db<br>
spring.datasource.username=your-username<br>
spring.datasource.password=your-password<br>
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect<br>
Make sure to replace your-username and your-password with your actual database credentials.

#### Step 3: Create and compile the project
Locate the CarServiceApplication class in your project's source code. It is typically located in the package that corresponds to your project's structure, and to start the application using the CarServiceApplication <br>

#### Step 4: Start using the application
The Auto Service API will now be running on the specified port.<br>
Open a web browser or a tool like cURL, Swagger or Postman.<br>
Enter the URL or endpoint of the program, including the port number, in the address bar of the web browser or set the URL in the request made by cURL, Swagger or Postman.<br>

#### Database setup with Liquibase
The application uses Liquibase for managing database migrations. It will automatically create the required tables and populate them with sample data.

#### Swagger API documentation
You can verify and test the application using Swagger. Once the application is running, open your web browser and visit the following URL:<br>
http://localhost:your-port/swagger-ui.html<br>
Replace your-port with the actual port number where the application is running.

#### User credentials
Upon launching the application for the first time, a user account will be created with the following credentials:

#### Username: _admin@gmail.com_
#### Password: _admin_
These credentials can be used to access the administrative features of the application.

## ![#f03c15](https://via.placeholder.com/15/f03c15/000000?text=+) Test Coverage
The Auto Service API has comprehensive test coverage to ensure the reliability and correctness of the codebase. The testing approach includes writing tests using JUnit 5, a widely-used testing framework for Java applications. The following information provides details about the test coverage and the testing tools utilized:

#### Test Types
The testing strategy for the Auto Service API includes different types of tests to validate various components and functionalities of the application. These test types may include:

* Unit tests: Unit tests focus on testing individual units of code, such as classes, methods, or functions, in isolation. For the Auto Service API, unit tests are written to test repository classes, service classes, and controller classes.
* Integration tests: Integration tests verify the interaction and integration between different components of the API. In the context of the Auto Service API, integration tests are written to ensure that the components, such as repositories, services, and controllers, work together correctly.
* Functional tests: Functional tests assess the overall functionality of the API from an end-to-end perspective. These tests simulate real-world scenarios and test the API's features as a whole, ensuring that it behaves as intended.
#### Testing Tools
  The Auto Service API utilizes the following testing tools and frameworks:

1. [x] JUnit 5
3. [x] Testcontainers
5. [x] SpringBootTest
7. [x] DataJpaTest
