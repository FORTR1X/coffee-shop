package ru.shop.coffee.mapper;

import org.mapstruct.Mapper;
import ru.shop.coffee.dto.users.UsersDto;
import ru.shop.coffee.entity.Users;

import static org.mapstruct.ReportingPolicy.IGNORE;
@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface UsersMapper {

  UsersDto usersToUsersDto(Users users);

}
