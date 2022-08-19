package ru.shop.coffee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shop.coffee.dto.bestSellers.BestSellersDto;
import ru.shop.coffee.service.BestSellersService;

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
}
