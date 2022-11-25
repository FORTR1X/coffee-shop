package ru.shop.coffee.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Конфигурация сервиса ресурсов
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

  /**
   * Конфигурация endpoint`ов по доступу к ресурсам
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers(HttpMethod.POST,
                    "/order-confirm").permitAll()
            .antMatchers(HttpMethod.GET,
                    "/oauth/**",
                    "/oauth/check_token/*",
                    "/v3/api-docs.yaml",
                    "/v3/api-docs/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/categories",
                    "/category/**",
                    "/subcategory/**",
                    "/subcategories",
                    "/product/*",
                    "/products/**",
                    "/company-category",
                    "/admin",
                    "/user/*",
                    "/uploads/**/*.jpg",
                    "/uploads/**/*.png",
                    "/uploads/**.pdf",
                    "/best-sellers",
                    "/products-by-ids",
                    "/search**",
                    "/upload/product/images-title*",
                    "/stocks").permitAll()
            .antMatchers(HttpMethod.DELETE).authenticated()
            .antMatchers(HttpMethod.POST).authenticated()
            .antMatchers(HttpMethod.PUT).authenticated()
            .anyRequest().authenticated()
            .and()
            .rememberMe().key("uniqueAndSecret");
  }
}