package ru.shop.coffee.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shop.coffee.dto.users.UsersDto;
import ru.shop.coffee.service.UsersService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "UsersController", description = "API контролера по работе с пользователями")
@Validated
public class UsersController {

  private final UsersService usersService;

  @Operation(description = "Получение информации о пользователе")
  @GetMapping(
          value = "/user/id{id}",
          produces = {"application/json"})
  public ResponseEntity<UsersDto> getUserById(
          @Parameter(description = "ID пользователя")
          @PositiveOrZero @Valid @PathVariable("id") Integer id) {
    return ResponseEntity.ok(usersService.getUserById(id));
  }

  @Operation(description = "Получение информации об администраторе")
  @GetMapping(
          value = "/admin",
          produces = {"application/json"})
  public ResponseEntity<UsersDto> getAdmin() {
    return ResponseEntity.ok(usersService.getAdmin());
  }
}
