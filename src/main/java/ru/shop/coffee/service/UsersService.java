package ru.shop.coffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shop.coffee.dto.users.UsersDto;
import ru.shop.coffee.mapper.UsersMapper;
import ru.shop.coffee.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class UsersService {

  private final UsersMapper usersMapper;

  private final UsersRepository usersRepository;

  public UsersDto getAdmin() {
    return usersMapper.usersToUsersDto(usersRepository.findByUsername("admin"));
  }

  public UsersDto getUserById(Integer id) {
    return usersMapper.usersToUsersDto(usersRepository.findById(id).orElseThrow());
  }

}
