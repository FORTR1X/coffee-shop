package ru.shop.coffee.dto.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.shop.coffee.entity.Subcategory;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Транспортный объект изменения товара")
public class ProductUpdateDto {

  @Schema(description = "Заголовок товара")
  private String header;

  @Schema(description = "Цена товара")
  private int price;

  @Schema(description = "Описание товара")
  private String description;

  @JsonBackReference
  @Schema(description = "ID подкатегории")
  private Subcategory subcategory;

}
