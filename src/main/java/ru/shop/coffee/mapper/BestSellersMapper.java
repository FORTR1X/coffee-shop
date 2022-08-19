package ru.shop.coffee.mapper;

import org.mapstruct.Mapper;
import ru.shop.coffee.dto.bestSellers.BestSellersDto;
import ru.shop.coffee.entity.BestSellers;

import java.util.List;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedTargetPolicy = WARN)
public interface BestSellersMapper {

  List<BestSellersDto> bestSellersToBestSellersDto(Iterable<BestSellers> bestSellers);

}
