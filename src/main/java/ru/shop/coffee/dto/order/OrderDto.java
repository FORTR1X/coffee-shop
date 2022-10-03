package ru.shop.coffee.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Транспортный объект заказа")
public class OrderDto {

  @Schema(description = "Имя заказчика")
  private String firstName;

  @Schema(description = "Фамилия заказчика")
  private String lastName;

  @Schema(description = "Номер телефона заказчика")
  private String phoneNumber;

  @Schema(description = "Электронная почта")
  private String email;

  @Schema(description = "Адрес доставки")
  private String address;

  @Schema(description = "Примечание к заказу")
  private String orderNote;

  @Schema(description = "Способ оплаты")
  private String methodPayment;

  @Schema(description = "Список покупаемого товара")
  private List<OrderProductDto> productDtoList;
}
