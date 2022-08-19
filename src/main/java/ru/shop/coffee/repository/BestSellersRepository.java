package ru.shop.coffee.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.shop.coffee.entity.BestSellers;

public interface BestSellersRepository extends PagingAndSortingRepository<BestSellers, Integer> {
}
