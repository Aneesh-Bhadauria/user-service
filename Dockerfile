FROM amazoncorretto:17
COPY target/userservice-0.0.1-SNAPSHOT.jar userservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/userservice-0.0.1-SNAPSHOT.jar"]

