package ru.shop.coffee.dto.subcategory;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.shop.coffee.entity.Category;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Транспортный объект создания подкатегории")
@ToString
public class SubcategoryCreateDto {

  @NotNull
  @Schema(description = "Заголовок подкатегории")
  private String title;

  @JsonBackReference
  @Schema(description = "ID категории подкатегории")
  private Category category;

  @NotNull
  @Schema(description = "URL подкатегории")
  private String url;

}
