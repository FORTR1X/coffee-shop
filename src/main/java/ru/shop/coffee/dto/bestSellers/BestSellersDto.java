package ru.shop.coffee.dto.bestSellers;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.shop.coffee.dto.product.ProductDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Транспортный объект топа продаж")
public class BestSellersDto {

  @Schema(description = "id")
  private Integer id;

  @Schema(description = "Relationship with Product")
  private ProductDto product;

}
