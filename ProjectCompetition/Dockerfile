FROM maven:3.9.6-amazoncorretto-21 AS build
WORKDIR /app
COPY src ./src
COPY pom.xml .
RUN mvn clean package -DskipTests
WORKDIR /app/target
CMD [ "java", "-jar" , "ProjectCompetition-0.0.1-SNAPSHOT.jar" ]