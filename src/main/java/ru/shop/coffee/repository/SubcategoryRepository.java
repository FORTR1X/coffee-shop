package ru.shop.coffee.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.shop.coffee.entity.Category;
import ru.shop.coffee.entity.Subcategory;

public interface SubcategoryRepository extends PagingAndSortingRepository<Subcategory, Integer> {

  //  List<Subcategory> findAllByCatId(Integer catId);
  void deleteByCategory(Category category);

}
