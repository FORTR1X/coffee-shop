package ru.shop.coffee.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.shop.coffee.entity.Users;

public interface UsersRepository extends PagingAndSortingRepository<Users, Integer> {

  Users findByUsername(String username);

}
