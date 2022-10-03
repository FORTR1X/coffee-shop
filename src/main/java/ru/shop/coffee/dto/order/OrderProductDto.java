package ru.shop.coffee.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Транспортный объект товара корзины")
public class OrderProductDto {

  @Schema(description = "ID продукта")
  private Integer productId;

  @Schema(description = "Количество товара")
  private Integer count;

}
