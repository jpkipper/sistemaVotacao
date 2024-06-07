package br.com.db.sistema.votacao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class ConfigSwagger
{
    @Bean
    public OpenAPI testOpenAPIDefinition()
    {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Aplicação para controlar um sistema de votação")
                        .contact(new Contact()
                                .name("João Pedro Kipper").email("jpkipper@jotmail.com"))
                        .description("Aplicação desenvolvida para teste técnico")
                        .version("v0.0.1"))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositório Desta API no GitHub")
                        .url("https://github.com/jpkipper/sistemaVotacaoDB"));
    }
}