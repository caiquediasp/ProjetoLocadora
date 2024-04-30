FROM openjdk:17-jdk-alpine
RUN mkdir /app
WORKDIR /app
COPY target/ProjetoLocadora-0.0.1-SNAPSHOT.jar /app
CMD ["java", "-jar", "ProjetoLocadora-0.0.1-SNAPSHOT.jar"]
