
# URL Shortener Application

## Overview

This URL Shortener application provides a simple and efficient way to shorten long URLs into shorter, more manageable links. It employs a counter-based mechanism to generate unique short URLs, ensuring that each shortened link maps to a specific original URL. The application is built using Spring Boot and incorporates various technologies such as ZooKeeper, Redis, and SLF4J for logging.

## Counter-Based Mechanism

The counter-based mechanism is a fundamental aspect of this URL shortener application. It operates as follows:

1. **Unique Counter Generation**: Each time a URL is shortened, a unique counter is incremented. This counter acts as a unique identifier for the URL being shortened.

2. **Base62 Encoding**: The unique counter value is then encoded using Base62 encoding. This encoding scheme uses a combination of uppercase letters, lowercase letters, and digits (0-9) to convert the numeric counter value into a shorter alphanumeric string. This string serves as the short URL.

3. **Storage**: The mapping between the shortened URL and the original URL is stored in a distributed cache (Redis), ensuring quick access and retrieval of the original URL when the short URL is accessed.

4. **High Availability**: The counter management is done using ZooKeeper, which provides high availability and consistency in distributed systems. ZooKeeper ensures that the counter value is consistently managed across multiple instances of the application.

## Application Architecture

The architecture of the URL Shortener application consists of the following components:

- **Client Request**: The application exposes RESTful APIs that clients can use to submit URLs for shortening and to retrieve original URLs using shortened links.

- **URL Shortener**: The core component of the application that handles the logic for generating short URLs using the counter-based mechanism. It communicates with Redis for storing and retrieving URL mappings.

- **Redis Cache**: A distributed caching mechanism used to store the mapping between the short URL and the original URL. Redis ensures fast access and retrieval.

- **ZooKeeper**: A distributed coordination service used to manage the unique counter for generating short URLs. It provides a highly available and fault-tolerant mechanism for counter management.

## Libraries Used

The following libraries and technologies are utilized in this application:

- **Spring Boot**: The framework for building the web application, providing various features for easy application setup and configuration.
- **Spring Cloud**: To enable service discovery and cloud-native capabilities.
- **ZooKeeper**: For distributed coordination and managing the unique counter.
- **Redis**: A distributed caching system for storing URL mappings.
- **SLF4J**: A simple logging facade for Java, used for logging application events and errors.
- **Logback**: The logging framework that is the default for Spring Boot, integrated with SLF4J.

## Environment Variables

| Variable Name                 | Description                                          | Default Value                   |
|-------------------------------|------------------------------------------------------|---------------------------------|
| `SERVER_PORT`                 | The port on which the application will run.         | Not specified                   |
| `ZOOKEEPER_HOST`              | The hostname of the ZooKeeper server.               | `localhost:2181`                |
| `REDIS_HOST`                  | The hostname of the Redis server.                   | Not specified                   |
| `REDIS_PORT`                  | The port on which the Redis server is running.      | Not specified                   |
| `ZOOKEEPER_COUNTER_PATH`      | The path for the counter range in ZooKeeper.        | Not specified                   |
| `RANGE_SIZE`                  | The difference in counter range for URL shortening.  | `1000`                          |
| `DB_URL`                      | The JDBC URL for connecting to the database.        | `jdbc:h2:mem:testdb`           |
| `DB_DRIVER`                   | The class name of the database driver.              | `org.h2.Driver`                 |
| `DB_USERNAME`                 | The username for connecting to the database.        | Not specified                   |
| `DB_PASSWORD`                 | The password for connecting to the database.        | Not specified                   |
| `DB_PLATFORM`                 | The platform for the JPA database.                  | Not specified                   |

## Notes

- It is recommended to set the environment variables in your operating system or through a `.env` file before running the application to ensure that the application behaves as expected.
- The values for some environment variables are not specified, and it is essential to define them based on your environment and requirements.

## Usage Example

You can set these environment variables in your terminal or include them in your application's configuration files. For example, in a Unix-like environment, you can export variables like this:

```bash
export SERVER_PORT=8080
export ZOOKEEPER_HOST=localhost:2181
export REDIS_HOST=localhost
export REDIS_PORT=6379
export ZOOKEEPER_COUNTER_PATH=/url-shortener/counter-range
export DB_URL=jdbc:h2:mem:testdb
export DB_DRIVER=org.h2.Driver
export DB_USERNAME=sa
export DB_PASSWORD=password
export DB_PLATFORM=org.hibernate.dialect.H2Dialect
```


## TODO List
- [ ] Integrate Docker Compose to set up ZooKeeper and Redis along with the required prerequisites.
- [ ] Implement a client UI to integrate with the backend.
- [ ] Add the application's HLD

## Getting Started

To run the application locally, follow these steps:

1. Clone the repository:
   ```bash
   git clone https://github.com/thompsonnaidu/url-shortener.git
   cd url-shortener
   ```
