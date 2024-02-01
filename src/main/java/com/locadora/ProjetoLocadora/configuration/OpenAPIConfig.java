package com.locadora.ProjetoLocadora.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080/");
        localServer.setDescription("URL do Server Local!");

        Contact contato = new Contact()
                .name("Caique")
                .email("caiquediasp@gmail.com");

        Info info = new Info()
                .title("Projeto Locadora")
                .version("1.0")
                .contact(contato)
                .description("API desenvolvida como forma de estudo para aprimorar minhas habilidades" +
                ", utilizando Java + Spring + PostgreSQL, e documentada com Swagger OpenAPI");

        return new OpenAPI().info(info);
    }
}
