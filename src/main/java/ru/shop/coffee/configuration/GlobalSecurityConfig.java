package ru.shop.coffee.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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

  /** Драйвер к БД */
  @Autowired
  private DataSource dataSource;

  /** Глобальная конфигурация защиты endpoint`ов */
  @Override
  protected void configure(HttpSecurity http) {
    // Отключаем глобальный фильтр Security -> Используем фильтр Resource Server
  }
}
