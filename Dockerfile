FROM maven:3.8.5-openjdk-17
WORKDIR /app
COPY . .
RUN mvn clean install

CMD mvn -e spring-boot:run
