

FROM maven:3.9.6-eclipse-temurin-17 AS build

#Create app directories
WORKDIR /opt/mod

#Copy project file in stages to leverage Docker cache
RUN mkdir -p /opt/mod/ts-mod-api
COPY ./pom.xml ./ts-mod-api
RUN mkdir -p ~/.m2/repository && mvn -f ./ts-mod-api/pom.xml dependency:go-offline

#RUN mkdir -p ~/.m2/repository && mvn -f ./ts-mod-api/pom.xml clean install

#Copy source code
COPY ./ ./ts-mod-api

#Build application
WORKDIR /opt/mod/ts-mod-api
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine

#Copy compied jar from build stage
COPY --from=build /opt/mod/ts-mod-api/target/ts-mod-api-1.0.0-SNAPSHOT.jar /app/ts-mod-api.jar

#COPY /opt/mod/ts-mod-api/target/ts-mod-api-1.0.0-SNAPSHOT.jar /app/ts-mod-api.jar

#Expose application port
EXPOSE 8080

#Start the application
ENTRYPOINT ["java", "-jar", "/app/ts-mod-api.jar"]

