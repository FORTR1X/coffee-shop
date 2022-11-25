package ru.shop.coffee.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;


/**
 * Глобальная конфигурация безопасности endpoint`ов приложения
 */
@Configuration
/** Если возникают проблемы в отладке безопасности, то можно прокинуть в аннотацию EnableWebSecurity параметр debug = true, для подробного логирования метаданных по входящим запросам и фильтрам */
@EnableWebSecurity(debug = false)
/** EnableGlobalMethodSecurity обязательно необходит для работы аннотации PreAuthorize */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GlobalSecurityConfig extends WebSecurityConfigurerAdapter {

  /**
   * Драйвер к БД
   */
  @Autowired
  private DataSource dataSource;

  private BCryptPasswordEncoder passwordEncoder;

  /**
   * Менеджер аутентификации
   */
  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * Сервис пользователей.
   */
  @Bean
  @Override
  protected UserDetailsService userDetailsService() {
    return new JdbcUserDetailsManager(this.dataSource);
  }

  /**
   * Глобальная конфигурация защиты endpoint`ов
   */
  @Override
  protected void configure(HttpSecurity http) {
    // Отключаем глобальный фильтр Security -> Используем фильтр Resource Server
  }

  /**
   * Конфигурация менеджера по аутентификации
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService());
  }
}