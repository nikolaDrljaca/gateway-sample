# API Gateway

The API Gateway pattern is usually applied when the number of services increases as well as the number of clients.
It allows us to consolidate a client facing service that would expose and route all incoming traffic to the necessary services, without the client specify exactly.

More details can be found in the report file.

## Spring Cloud Gateway

Dependencies: `pom.xml`
```xml
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-gateway</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
    </dependencies>
```

`spring-cloud-starter-gateway` provides core gateway functionality. Routing, Load Balancing, Health Checks.

`actuator` provides a REST API we can use to query for gateway and route information.

`eureka-client` for service discovery.

## Routing
Gateway performs routing based on paths and predicates. Any incoming path that satisfies a predicate will be routed to a specified path/endpoint.
Example:
```yaml
spring:
  application:
    name: api-gateway
  cloud:
    gateway:
      routes:
        # Provide routes to other services here
        - id: user
          uri: lb://USER
          predicates:
            - Path=/api/v1/user/**
  
server:
  port: 8083
```
This example assumes that we have an application running, with the name `spring.application.name=user`.
This application is running on port `8090`.
Our router is running on port `8083` as specified above.

Any request coming to `localhost:8083/api/v1/user` will be forwarded to `localhost:8090/api/v1/user`.

Notice that the request endpoint-path has stayed the same. There are options to perform path rewriting, but for this example
port forwarding was enough to showcase functionality.

## Load Balancing
When running multiple instances of applications, the `Gateway` is configured to use `Round Robin` algorithm by default.
Meaning, each instance is hit with a request in a sequential manner. This can be changed in code.

Unfortunately, by default the gateway does not perform health checks before sending a request to the instance.

Example:

With the above in mind, let's also assume that we have the same `user` application running on instances
on ports `8091, 8092, 8093`.

These instances are `eureka-clients` and they can have their IP/port assigned dynamically or statically. In any case
the `eureka-server` will pick them up. 

Now, when a request comes to our gateway `localhost:8083/api/v1/user` it will be forwarded to an instance,
by round-robin rules it will be `8090, 8091, 8092, 8093`.

## Health Check
In terms of health checks, there are two options. We can either perform them using the spring gateway or by using the eureka client.
```yaml
eureka:
  client:
    healthcheck:
      enabled: true
```
This configuration option is with the eureka client. With this set to true, the eureka client will communicate a heartbeat with the server,
and Spring will disable its health checks, forwarding this status to any inquiries.

More detailed health info can be accessed at `localhost:8083`.

Do note that these config options need to be present:
```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: always

```

## Service Discovery
By default, changes to clients are registered in the server after 10-15 seconds.
Meaning if a new instance is added, it will start handling requests after that time.

Same goes for instances that have been deleted, they will be removed from the registry after that time.
