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
import ru.shop.coffee.dto.category.CategoryCreateDto;
import ru.shop.coffee.dto.category.CategoryDto;
import ru.shop.coffee.service.CategoryService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "CategoryController", description = "API контролера по работе с категориями")
@Validated
public class CategoryController {

  private final CategoryService categoryService;

  @Operation(description = "Создание категории")
  @PostMapping(
          value = "/category",
          produces = {"application/json"},
          consumes = {"application/json"})
  public ResponseEntity<CategoryDto> create(
          @Parameter(description = "Объект в качестве тела запроса")
          @Valid @RequestBody CategoryCreateDto request) {
    return new ResponseEntity<>(categoryService.create(request), HttpStatus.CREATED);
  }

  @Operation(description = "Получение категории по id")
  @GetMapping(
          value = "/category/id{id}",
          produces = {"application/json"})
  public ResponseEntity<CategoryDto> getById(
          @Parameter(description = "ID категории")
          @PositiveOrZero @Valid @PathVariable("id") Integer id) {
    return ResponseEntity.ok(categoryService.getById(id));
  }

  @Operation(description = "Получение всего списка категорий")
  @GetMapping(
          value = "/categories",
          produces = {"application/json"})
  public ResponseEntity<Iterable<CategoryDto>> getAllCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories());
  }

  @Operation(description = "Удаление категории по ID")
  @DeleteMapping(
          value = "/category/id{id}",
          produces = {"application/json"})
  public ResponseEntity<Void> deleteById(
          @Parameter(description = "ID удаляемой категории")
          @PositiveOrZero @Valid @PathVariable("id") Integer id) {
    categoryService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
