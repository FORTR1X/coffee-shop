package ru.shop.coffee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.shop.coffee.entity.Product;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

  // void deleteAllBySubcatId(Integer subcatId);
  // Page<Product> findAllBySubcatId(Integer subcatId, Pageable pageable);
}
