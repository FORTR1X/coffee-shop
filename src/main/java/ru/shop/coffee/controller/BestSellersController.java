package ru.shop.coffee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.shop.coffee.dto.bestSellers.BestSellersDto;
import ru.shop.coffee.service.BestSellersService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "BestSellersController", description = "API контролера по работе с топами продаж")
@Validated
public class BestSellersController {

  private final BestSellersService bestSellersService;

  @Operation(description = "Получение списка топа продаж")
  @GetMapping(
          value = "/best-sellers",
          produces = {"application/json"})
  public ResponseEntity<List<BestSellersDto>> getAllBestSellers() {
    return ResponseEntity.ok(bestSellersService.getAllBestSellers());
  }

  @Operation(description = "Удалить продукт из списка")
  @DeleteMapping(
          value = "/best-sellers/id{id}",
          produces = {"application/json"})
  public ResponseEntity<List<BestSellersDto>> deleteById(
          @Parameter(description = "ID удаляемого товара")
          @Valid @PositiveOrZero @PathVariable("id") Integer id) {
    return ResponseEntity.ok(bestSellersService.deleteById(id));
  }

  @Operation(description = "Добавить продукт в список")
  @PostMapping(
          value = "/best-sellers/id{id}",
          produces = {"application/json"})
  public ResponseEntity<List<BestSellersDto>> addProductToBestSellers(
          @Parameter(description = "ID добавляемого товара")
          @Valid @PositiveOrZero @PathVariable("id") Integer id) {
    return ResponseEntity.ok(bestSellersService.addProductToBestSellers(id));
  }
}
