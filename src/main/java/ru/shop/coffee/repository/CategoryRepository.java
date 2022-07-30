package ru.shop.coffee.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.shop.coffee.entity.Category;

public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer> {



}
