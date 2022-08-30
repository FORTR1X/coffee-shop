package ru.shop.coffee.dto.subcategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.shop.coffee.dto.category.CategoryDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Транспортный объект подкатегории")
@ToString
public class SubcategoryDto {

  @Schema(description = "ID подкатегории")
  private Integer id;

  @Schema(description = "Заголовок подкатегории")
  private String title;

  @Schema(description = "ID категории подкатегории")
  private CategoryDto category;

  @Schema(description = "URL подкатегории")
  private String url;

}
