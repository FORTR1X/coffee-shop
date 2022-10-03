package ru.shop.coffee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shop.coffee.dto.order.OrderDto;
import ru.shop.coffee.service.MailingService;

import javax.validation.Valid;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "MailingController", description = "API контролера по работе с рассылкой писем")
@Validated
public class MailingController {

  private final MailingService mailingService;

  @Operation(description = "Оформление заказа и отправка данных на почту")
  @PostMapping(
          value = "/order-confirm",
          produces = {"application/json"},
          consumes = {"application/json"})
  public ResponseEntity<Void> createOrderMail(
          @Parameter(description = "Структура данных заказа")
          @Valid  @RequestBody OrderDto orderDto) {

    mailingService.createOrderMail(orderDto);
    return ResponseEntity.noContent().build();
  }

}
