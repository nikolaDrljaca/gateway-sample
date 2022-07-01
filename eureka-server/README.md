# Eureka Server

In the service discovery pattern, a service registry is needed which will keep track of all running instances of other services and their addresses.

This, of course, is crucial information for the API Gateway.

Using the `eureka-server`, there are only a few things that need to be configured for this to act as a server.

Inside the `application.yml`:
```yaml
spring:
  application:
    name: eureka-server

server:
  port: 8761

eureka:
  client:
    fetch-registry: false # don't fetch the registry, the clients will do that
    register-with-eureka: false # the server does not need to register itself
```
