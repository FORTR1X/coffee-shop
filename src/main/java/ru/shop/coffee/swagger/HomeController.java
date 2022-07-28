package ru.shop.coffee.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  // Перенаправление с начальной страницы на swagger ui
  @RequestMapping("/")
  public String index() {
    return "redirect:swagger-ui.html";
  }
}
