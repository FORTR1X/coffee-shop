package ru.shop.coffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shop.coffee.dto.order.OrderDto;
import ru.shop.coffee.dto.order.OrderProductDto;
import ru.shop.coffee.entity.Product;
import ru.shop.coffee.mapper.ProductMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MailingService {

  private final MailSenderService mailSenderService;

  private final ProductService productService;
  private final ProductMapper productMapper;

  private List<Integer> getProductIdsByProductDtoList(List<OrderProductDto> orderProducts) {
    List<Integer> productIds = new ArrayList<>();

    if (orderProducts.size() == 0) return productIds;

    for (OrderProductDto product : orderProducts) {
      productIds.add(product.getProductId());
    }

    return productIds;
  }

  private String getProductListMailText(List<OrderProductDto> productDtoList) {
    List<Integer> productIds = getProductIdsByProductDtoList(productDtoList);
    List<Product> productDto = productMapper.productsDtoToProducts(productService.getProductsByIds(productIds));

    StringBuilder mailText = new StringBuilder("Список товара: \n");

    mailText.append(String.format("%-5s | %-30s | %-5s\n", "ID", "Название товара", "Количество товара"));

    for (int i = 0; i < productDtoList.size(); i ++) {
      Integer productID = productDtoList.get(i).getProductId();
      String productHeader = productDto.get(i).getHeader();
      Integer productCount = productDtoList.get(i).getCount();

      String productListMail = String.format("%-5s | %-30s | %-5s\n", productID, productHeader, productCount);
      mailText.append(productListMail);
    }

    return mailText.toString();
  }

  private String getOrderNumber(List<OrderProductDto> productDtoList) {
    int dayOfMonth = LocalDate.now().getDayOfMonth();
    int month = LocalDate.now().getMonth().getValue();

    Integer sumProductIds = 0;
    for (OrderProductDto product : productDtoList) {
      sumProductIds += product.getProductId();
    }

    return String.format("%d%d-%d", dayOfMonth, month, sumProductIds);
  }

  public void createOrderMail(OrderDto orderDto) {
    List<OrderProductDto> productDtoList = orderDto.getProductDtoList();

    String mailTextProducts = getProductListMailText(productDtoList);
    String contactInfoCustomer = "Контактные данные: \n"
            + "Имя: " + orderDto.getFirstName() + "\n"
            + "Фамилия: " + orderDto.getLastName() + "\n"
            + "Номер телефона: " + orderDto.getPhoneNumber() + "\n"
            + "Электронная почта: " + orderDto.getEmail() + "\n"
            + "Адрес доставки: " + orderDto.getAddress() + "\n"
            + "Примечание к заказу: " + orderDto.getOrderNote() + "\n"
            + "Способ оплаты: " + orderDto.getMethodPayment();

    String mailTo = "f0rtrix@yandex.ru";
    String mailSubject = String.format("Новый заказ [#%s]", getOrderNumber(productDtoList));
    String mailText = mailTextProducts + "\n" + contactInfoCustomer;

    mailSenderService.sendSimpleMessage(mailTo, mailSubject, mailText);
  }
}
