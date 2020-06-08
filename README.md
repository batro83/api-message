# Api-message

## Getting Started

First, you need to install a mongoDb on your computer. Then you can modify mongo connection in application.yaml file.

To run the api, in the root folder of the project run: 

```
 ./mvnw spring-boot:run
```

The api is up now!!

### Run tests

In the root folder of the project: 

```
 ./mvnw test
```

For run the tests you need a mongoDb.


### Swagger

It can be tested with swagger:

[http://localhost:8083/swagger-ui.html](http://localhost:8083/swagger-ui.html)


### Improves

Validation number format for mobile  
Add DockerFile 
More Integration tests 



### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.0.RELEASE/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.0.RELEASE/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.0.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

