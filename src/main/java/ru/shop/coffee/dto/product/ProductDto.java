package ru.shop.coffee.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.shop.coffee.dto.subcategory.SubcategoryDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Транспортный объект товара")
public class ProductDto {

  @Schema(description = "ID товара")
  private Integer id;

  @Schema(description = "Заголовок товара")
  private String header;

  @Schema(description = "Цена товара")
  private int price;

  @Schema(description = "Описание товара")
  private String description;

  @Schema(description = "ID подкатегории")
  private SubcategoryDto subcategory;

}
