package ru.shop.coffee.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI()
            .info(new Info().title("DOZA shop service")
                    .description("DOZA shop service")
                    .version("v0.0.1"));
  }

}
