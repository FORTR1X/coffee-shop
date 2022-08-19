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
import ru.shop.coffee.dto.category.CategoryDto;
import ru.shop.coffee.dto.subcategory.SubcategoryCreateDto;
import ru.shop.coffee.dto.subcategory.SubcategoryDto;
import ru.shop.coffee.service.SubcategoryService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "SubcategoryController", description = "API контролера по работе с подкатегориями")
@Validated
public class SubcategoryController {

  private final SubcategoryService subcategoryService;

  @Operation(description = "Создание подкатегории")
  @PostMapping(
          value = "/subcategory",
          produces = {"application/json"},
          consumes = {"application/json"})
  public ResponseEntity<SubcategoryDto> create(
          @Parameter(description = "Объект в качестве тела запроса")
          @Valid @RequestBody SubcategoryCreateDto request) {
    return new ResponseEntity<>(subcategoryService.create(request), HttpStatus.CREATED);
  }

  @Operation(description = "Получение категории подкатегории")
  @GetMapping(
          value = "/subcategory/id{id}/category",
          produces = {"application/json"})
  public ResponseEntity<CategoryDto> getCategoryBySubcatId(
          @Parameter(description = "ID подкатегории")
          @Valid @PositiveOrZero @PathVariable("id") Integer id) {
    return ResponseEntity.ok(subcategoryService.getCategoryBySubcatId(id));
  }

  @Operation(description = "Получить подкатегорию по ID")
  @GetMapping(
          value = "/subcategory/id{id}",
          produces = {"application/json"})
  public ResponseEntity<SubcategoryDto> getSubcategoryById(
          @Parameter(description = "ID подкатегории")
          @PositiveOrZero @Valid @PathVariable("id") Integer id) {
    return ResponseEntity.ok(subcategoryService.getSubcategoryById(id));
  }

  @Operation(description = "Удалить подкатегорию по ID")
  @DeleteMapping(
          value = "/subcategory/id{id}",
          produces = {"application/json"})
  public ResponseEntity<Void> deleteById(
          @Parameter(description = "ID удаляемой подкатегории")
          @Valid @PositiveOrZero @PathVariable("id") Integer id) {
    subcategoryService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @Operation(description = "Получить полный список подкатегорий")
  @GetMapping(
          value = "/subcategories",
          produces = {"application/json"})
  public ResponseEntity<List<SubcategoryDto>> getSubcategiries() {
    return ResponseEntity.ok(subcategoryService.getSubcategiries());
  }
}
