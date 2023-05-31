# Introduction

Dynamodb async client; Spring Webflux framework usage and creation of reactive REST API; 
Flux and Mono asynchronous sequence API.

# Docker

cd src/main/docker

docker-compose up -d

# Build and Test


``` Linux BASH ```

mvnw clean test    (run unit tests)

mvnw clean verify  (run unit tests and build a JAR file)

```


``` Windows Environment ```

mvnw.cmd clean test    (run unit tests)

mvnw.cmd clean verify  (run unit tests and build a JAR file)

```

# API endpoint 

http://localhost:8080/reactive/v1/product/


# RequestBody example

{
   "product": {
   "name": "cloud",
   "brand": "aws"
   }
}
