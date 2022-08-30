package ru.shop.coffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shop.coffee.dto.bestSellers.BestSellersDto;
import ru.shop.coffee.entity.BestSellers;
import ru.shop.coffee.mapper.BestSellersMapper;
import ru.shop.coffee.repository.BestSellersRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BestSellersService {

  private final BestSellersRepository bestSellersRepository;

  private final BestSellersMapper bestSellersMapper;

  public List<BestSellersDto> getAllBestSellers() {
    return bestSellersMapper.bestSellersToBestSellersDto(bestSellersRepository.findAll());
  }
}
