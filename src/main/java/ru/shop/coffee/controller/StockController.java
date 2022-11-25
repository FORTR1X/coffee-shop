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
  public ResponseEntity<List<String>> addPhotoStock(
          @Parameter(description = "ID акционного товара")
          @Valid @PositiveOrZero @PathVariable("id") Integer id,
          @Parameter(description = "Изображение акции")
          @RequestParam("img") MultipartFile file) {
    return ResponseEntity.ok(stockService.addPhotoStock(id, file));
  }

  @Operation(description = "Удалить фотографию акции")
  @DeleteMapping(
          value = "/stock/id{id}",
          produces = {"application/json"})
  public ResponseEntity<List<String>> deletePhotoStock(
          @Parameter(description = "ID акционного товара")
          @Valid @PositiveOrZero @PathVariable("id") Integer id) {
    return ResponseEntity.ok(stockService.deletePhotoStock(id));
  }

  @Operation(description = "Изменить id акции")
  @PutMapping(
          value = "/stock/prev-id{prevId}/new-id{newId}",
          produces = {"application/json"})
  public ResponseEntity<List<String>> editIdStock(
          @Parameter(description = "Предыдущее ID акции")
          @Valid @PositiveOrZero @PathVariable("prevId") Integer prevId,
          @Parameter(description = "Новый ID акции")
          @Valid @PositiveOrZero @PathVariable("newId") Integer newId) {
    return ResponseEntity.ok(stockService.editIdStock(prevId, newId));
  }

  @Operation(description = "Изменить фото акции")
  @PutMapping(
          value = "/stock/id{id}",
          produces = {"application/json"})
  public ResponseEntity<List<String>> editPhotoStock(
          @Parameter(description = "ID изменяемой акции")
          @Valid @PositiveOrZero @PathVariable("id") Integer id,
          @Parameter(description = "Изображение акции")
          @RequestParam("img") MultipartFile file) {
    return ResponseEntity.ok(stockService.editPhotoStock(id, file));
  }
}
