# URL Shortening System

## Project Name
URL Shortening System

## Description
The URL Shortening System is a service that allows users to shorten long URLs, making them easier to share and manage. It utilizes a REST API for client interaction and processes requests through a centralized service logic.

## Archicteture
![url-shortening-system-img](https://raw.githubusercontent.com/luigivis/url-shortening-system/main/arch-short-url.jpeg)
## Main Components

1. **REST API**: Interface for clients to send and receive data.
2. **ShortURL Service**: Generates short URLs.
3. **Service Logic**: Processes requests and manages communication with databases and remote services.
4. **Databases**: Store long and short URLs along with any associated data.
5. **Remote Services**: Additional components that interact with the service logic to extend functionality.

## Tech Stack

- **Spring Boot**: A Java-based framework that simplifies the development of applications based on the Spring framework.
- **Java 21+**: The programming language used, providing enhancements and additional features compared to earlier versions.
- **Redis**: An open-source, in-memory database used for caching data and accelerating access to frequently used information.
- **PostgreSQL**: A widely used relational database management system for storing and retrieving data.
- **Kafka**: A distributed event streaming platform capable of handling real-time data feeds, used for reliable messaging and event-driven architecture.

## Getting Started

### Prerequisites
- Java 21 or later
- Maven or Gradle
- Redis
- PostgreSQL
- Kafka

### Installation

1. **Clone the Repository**
    ```sh
    git clone https://github.com/yourusername/url-shortening-system.git
    cd url-shortening-system
    ```

2. **Configure the Databases**
    - Set up your PostgreSQL database and update the `application.properties` file with your database credentials.
    - Ensure Redis is running on your system or update the configuration if necessary.
    - Set up Kafka and ensure it is running. Update the `application.properties` file with your Kafka configuration if necessary.

3. **Build the Application**
    ```sh
    ./mvnw clean install
    ```

4. **Run the Application**
    ```sh
    ./mvnw spring-boot:run
    ```

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create a new feature branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Create a new Pull Request.

## License

Distributed under the MIT License. See `LICENSE` for more information.

## Acknowledgements

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Redis](https://redis.io/)
- [PostgreSQL](https://www.postgresql.org/)
- [Kafka](https://kafka.apache.org/)

## Contact

Project Link: [https://github.com/luigivis/url-shortening-system](https://github.com/luigivis/url-shortening-system)

---

This README file provides a comprehensive overview of the URL Shortening System project, including its components, technology stack, and instructions for setting up and running the application.
