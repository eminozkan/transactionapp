package eminozkan.transaction.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Transaction Application")
                        .version("1.0")
                        .description("Transaction Handling System")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")
                        )
                        .contact(new Contact()
                                .email("ozkaneminn.01@gmail.com")
                                .name("Geliştirici")
                                .url("https://github.com/eminozkan")
                        )
                );
    }
}
