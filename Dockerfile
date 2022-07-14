FROM openjdk:8
ADD target/delivery-now-rest-api-0.0.1.jar delivery-now-rest-api.jar
ENTRYPOINT ["java","-jar","delivery-now-rest-api.jar"]