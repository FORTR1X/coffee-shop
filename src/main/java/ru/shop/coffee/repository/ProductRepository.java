package ru.shop.coffee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.shop.coffee.entity.Product;
import ru.shop.coffee.entity.Subcategory;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

  List<Product> findAllBySubcategory(Subcategory subcategory, Pageable pageable);
  List<Product> findAllBySubcategory(Subcategory subcategory);
  List<Product> findAllBySubcategoryAndPriceBetween(Subcategory subcategory, int priceFrom, int priceTo, Pageable pageable);

  List<Product> findProductsByHeaderContainsOrDescriptionContains(String header, String description);
}
