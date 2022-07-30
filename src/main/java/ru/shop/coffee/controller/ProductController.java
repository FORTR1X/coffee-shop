package ru.shop.coffee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.shop.coffee.dto.product.ProductCreateDto;
import ru.shop.coffee.dto.product.ProductDto;
import ru.shop.coffee.dto.product.ProductUpdateDto;
import ru.shop.coffee.service.ProductService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "ProductController", description = "API контролера по работе с товаром")
@Validated
public class ProductController {

  private final ProductService productService;

  @Operation(description = "Создание товара")
  @PostMapping(
          value = "/product",
          produces = {"application/json"},
          consumes = {"application/json"})
  public ResponseEntity<ProductDto> create(
          @Parameter(description = "Объект в качестве тела запроса")
          @Valid @RequestBody ProductCreateDto request) {
    return new ResponseEntity<>(productService.create(request), HttpStatus.CREATED);
  }

  @Operation(description = "Получение списка товара по id подкатегории")
  @GetMapping(
          value = "/products/subcatId{subcatId}",
          produces = {"application/json"})
  public ResponseEntity<List<ProductDto>> getProductsBySubcatId(
          @Parameter(description = "ID подкатегории")
          @PositiveOrZero @Valid @PathVariable("subcatId") Integer subcatId,
          @Parameter(description = "Количество получаемого товара на странице")
          @Min(1) @Max(10) @RequestParam(value = "limit", required = false) Integer limit,
          @Parameter(description = "Номер страницы")
          @Min(0) @Max(20) @RequestParam(value = "page", required = false) Integer page) {
    return ResponseEntity.ok(productService.getProductsBySubcatId(subcatId, limit, page));
  }

  @Operation(description = "Получение товара по его ID")
  @GetMapping(
          value = "/product/id{id}",
          produces = {"application/json"})
  public ResponseEntity<ProductDto> getById(
          @Parameter(description = "ID товара")
          @PositiveOrZero @Valid @PathVariable("id") Integer id) {
    return ResponseEntity.ok(productService.getById(id));
  }

  @Operation(description = "Редактирование товара по его ID")
  @PutMapping(
          value = "/product/id{id}",
          produces = {"application/json"},
          consumes = {"application/json"})
  public ResponseEntity<ProductDto> updateById(
          @Parameter(description = "ID редактируемого товара")
          @PositiveOrZero @Valid @PathVariable("id") Integer id,
          @Parameter(description = "Объект с новыми значениями для изменяемого товара")
          @Valid @RequestBody ProductUpdateDto request) {
    return ResponseEntity.ok(productService.updateById(id, request));
  }

  @Operation(description = "Удалить товар по ID")
  @DeleteMapping(
          value = "/product/id{id}",
          produces = {"application/json"})
  public ResponseEntity<Void> deleteById(
          @Parameter(description = "ID удаляемого товара")
          @Valid @PositiveOrZero @PathVariable("id") Integer id) {
    productService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
