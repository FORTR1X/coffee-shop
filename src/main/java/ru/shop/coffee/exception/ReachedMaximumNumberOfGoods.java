package ru.shop.coffee.exception;

import javax.validation.ValidationException;

public class ReachedMaximumNumberOfGoods extends ValidationException {

  private static final String DEFAULT_ERROR_MESSAGE = "Максимальное количество товаров в топе продаж - 5";

  // Конструктор, выдающий ошибку
  public ReachedMaximumNumberOfGoods() {
    super(DEFAULT_ERROR_MESSAGE);
  }

}