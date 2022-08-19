package ru.shop.coffee.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.shop.coffee.entity.CompanyCategory;

public interface CompanyCategoryRepository extends PagingAndSortingRepository<CompanyCategory, Integer> {
}
