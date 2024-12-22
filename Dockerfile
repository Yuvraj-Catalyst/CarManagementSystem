FROM jelastic/maven:3.9.5-openjdk-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/CarManagementSystem-0.0.1-SNAPSHOT.jar CarManagementSystem.jar
EXPOSE 5050
ENTRYPOINT [ "java","-jar","demo.jar" ]
