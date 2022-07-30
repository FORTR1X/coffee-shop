package ru.shop.coffee.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Транспортный объект обновления категории")
public class CategoryUpdateDto {

  @NotNull
  @Schema(description = "Название категории")
  private String title;

  @NotNull
  @Schema(description = "URL адрес категории")
  private String url;

}
