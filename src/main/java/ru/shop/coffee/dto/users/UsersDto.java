package ru.shop.coffee.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Транспортный объект пользователя")
public class UsersDto {

  @Schema(description = "ID категории")
  private Integer id;

  @Schema(description = "Электронная почта")
  private String email;

  @Schema(description = "Номер телефона")
  private String phoneNumber;

  @Schema(description = "Активирован пользователь?")
  private boolean enabled;

}
