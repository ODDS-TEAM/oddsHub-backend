FROM eclipse-temurin:17-alpine as build
COPY .mvn .mvn                                               
COPY mvnw .                                                  
COPY pom.xml .                                               
COPY src src                                                 
RUN ./mvnw test -Dspring.profiles.active=test
RUN ./mvnw package -Dmaven.test.skip

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]