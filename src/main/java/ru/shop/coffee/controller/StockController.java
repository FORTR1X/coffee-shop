package ru.shop.coffee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.shop.coffee.service.StockService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "StockController", description = "API контролера по работе с акционным товаром")
@Validated
public class StockController {

  private final StockService stockService;

  @Operation(description = "Получить список акционных изображений")
  @GetMapping(
          value = "/stocks",
          produces = {"application/json"})
  public ResponseEntity<List<String>> getStocks() {
    return ResponseEntity.ok(stockService.getStocks());
  }

  @Operation(description = "Добавить фотографию акции")
  @PostMapping(
          value = "/stock/id{id}",
          produces = {"application/json"})
  public ResponseEntity<Void> addPhotoStock(
          @Parameter(description = "ID акционного товара")
          @Valid @PositiveOrZero @PathVariable("id") Integer id,
          @Parameter(description = "Изображение акции")
          @RequestParam("img") MultipartFile file) {
    stockService.addPhotoStock(id, file);
    return ResponseEntity.noContent().build();
  }

  @Operation(description = "Удалить фотографию акции")
  @DeleteMapping(
          value = "/stock/id{id}",
          produces = {"application/json"})
  public ResponseEntity<Void> deletePhotoStock(
          @Parameter(description = "ID акционного товара")
          @Valid @PositiveOrZero @PathVariable("id") Integer id) {
    stockService.deletePhotoStock(id);
    return ResponseEntity.noContent().build();
  }

}
