package io.github.rafaelaperruci.brasileiras_e_commerce.api.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ECommerce Brasileiras API")
                        .version("v1")
                        .description("Gerenciamento do ECommerce Brasileiras API")
                        .contact(new Contact()
                                .name("Rafaela Perruci")
                                .url("https://github.com/RafaelaPerruci/brasileiras-ecommerce"))

                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                )
                .servers(List.of(
                        new Server().url("/") // ← Isso remove o "generated"
                ));
    }
}
