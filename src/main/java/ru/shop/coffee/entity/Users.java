package ru.shop.coffee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Integer id;

  // Имя пользователя
  @Column(name = "username", nullable = false)
  private String username;

  // Email пользователя
  @Email
  @Column(name = "email", nullable = false)
  private String email;

  // Номер телефона пользователя
  @Column(name = "phoneNumber", nullable = false)
  private String phoneNumber;

  // Пароль пользователя
  @Column(name = "password", nullable = false)
  private String password;

  // Проверка включенности пользователя
  @Column(name = "enabled", nullable = false)
  private boolean enabled;

}
