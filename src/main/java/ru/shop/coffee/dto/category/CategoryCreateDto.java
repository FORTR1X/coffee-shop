package ru.shop.coffee.dto.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Транспортный объект создания категории")
@ToString
public class CategoryCreateDto {

  @NotNull
  @Schema(description = "Название категории")
  private String title;

  @NotNull
  @Schema(description = "URL адрес категории")
  private String url;

}
