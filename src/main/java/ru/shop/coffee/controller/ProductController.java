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
import org.springframework.web.multipart.MultipartFile;
import ru.shop.coffee.dto.product.ProductCreateDto;
import ru.shop.coffee.dto.product.ProductDto;
import ru.shop.coffee.dto.product.ProductUpdateDto;
import ru.shop.coffee.service.ProductService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import java.util.Arrays;
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
          value = "/products",
          produces = {"application/json"})
  public ResponseEntity<List<ProductDto>> getProductsBySubcatId(
          @Parameter(description = "ID подкатегории")
          @Valid @RequestParam("subcatId") String subcatId,
          @Parameter(description = "Цена поиска от")
          @Valid @RequestParam(value = "price-from", required = false) String priceFrom,
          @Parameter(description = "Цена поиска до")
          @Valid @RequestParam(value = "price-to", required = false) String priceTo,
          @Parameter(description = "Количество получаемого товара на странице")
          @Min(1) @Max(20) @RequestParam(value = "limit", required = false) String limit,
          @Parameter(description = "Номер страницы")
          @Min(0) @Max(20) @RequestParam(value = "page", required = false) String page,
          @Parameter(description = "Тип сортировки")
          @RequestParam(value = "sort", required = false) String sort) {
    if (priceFrom.length() > 0 || priceTo.length() > 0)
      return ResponseEntity.ok(productService.getProductsBySubcatIdAndPriceBetween(subcatId, priceFrom, priceTo, limit, page, sort));

    return ResponseEntity.ok(productService.getProductsBySubId(subcatId, limit, page, sort));
  }

  @Operation(description = "Получение количества товара по id подкатегории")
  @GetMapping(
          value = "/products/subcat-id{subcatId}/count",
          produces = {"application/json"})
  public ResponseEntity<Integer> getCountProductsBySubcatId(
          @Parameter(description = "ID подкатегории")
          @PositiveOrZero @Valid @PathVariable("subcatId") Integer subcatId) {
    return ResponseEntity.ok(productService.getCountProductsBySubcatId(subcatId));
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

  @Operation(description = "Получение товаров корзины по ID")
  @GetMapping(
          value = "/products-by-ids",
          produces = {"application/json"})
  public ResponseEntity<List<ProductDto>> getProductsByIds(
          @Parameter(description = "Список ID товаров")
          @Valid @RequestParam(value = "id") List<Integer> integerList) {
    return ResponseEntity.ok(productService.getProductsByIds(integerList));
  }

  @Operation(description = "Поиск по названию и описанию")
  @GetMapping(
          value = "/search",
          produces = {"application/json"})
  public ResponseEntity<List<ProductDto>> getProductsByRequest(
          @Parameter(description = "Поисковой запрос")
          @RequestParam(value = "q") String request) {
    return ResponseEntity.ok(productService.getProductsByRequest(request));
  }

  @Operation(description = "Получение всего списка товаров")
  @GetMapping(
          value = "/all-products",
          produces = {"application/json"})
  public ResponseEntity<List<ProductDto>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @Operation(description = "Удаление товаров по списку id")
  @DeleteMapping(
          value = "/products",
          produces = {"application/json"})
  public ResponseEntity<Void> deleteProductsById (
          @Parameter(description = "Список id")
          @Valid @RequestParam(value = "id") List<Integer> ids) {
    productService.deleteAllById(ids);
    return ResponseEntity.noContent().build();
  }

  @Operation(description = "Добавление изображений товара")
  @PostMapping(
          value = "/upload/images{id}",
          produces = {"application/json"})
  public ResponseEntity<Void> uploadImages (
          @Parameter(description = "ID продукта, которому создаются изображения")
          @PositiveOrZero @Valid @PathVariable("id") Integer id,
          @Parameter(description = "Список изображений")
          @RequestParam("img") MultipartFile[] files) {

    productService.uploadImages(files, id);
    return ResponseEntity.noContent().build();
  }

  @Operation(description = "Получить список изображений товара")
  @GetMapping(
          value = "/upload/product/images-title{id}",
          produces = {"application/json"})
  public ResponseEntity<List<String>> getImagesNameById(
          @Parameter(description = "ID продукта, которому создаются изображения")
          @PositiveOrZero @Valid @PathVariable("id") Integer id) {
    return ResponseEntity.ok(productService.getImagesProductById(id));
  }

}
