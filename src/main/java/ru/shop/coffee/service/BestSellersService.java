package ru.shop.coffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.shop.coffee.dto.bestSellers.BestSellersDto;
import ru.shop.coffee.entity.BestSellers;
import ru.shop.coffee.entity.Product;
import ru.shop.coffee.exception.ReachedMaximumNumberOfGoods;
import ru.shop.coffee.mapper.BestSellersMapper;
import ru.shop.coffee.repository.BestSellersRepository;
import ru.shop.coffee.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BestSellersService {

  private final BestSellersRepository bestSellersRepository;
  private final ProductRepository productRepository;

  private final BestSellersMapper bestSellersMapper;

  public List<BestSellersDto> getAllBestSellers() {
    return bestSellersMapper.bestSellersToBestSellersDto(bestSellersRepository.findAll());
  }

  public List<BestSellersDto> deleteById(Integer id) {
    bestSellersRepository.deleteById(id);
    return bestSellersMapper.bestSellersToBestSellersDto(bestSellersRepository.findAll());
  }

  public List<BestSellersDto> addProductToBestSellers(Integer id) {
    isBestSellersListReachedMaximum();

    BestSellers bestSellers = new BestSellers(id, productRepository.findById(id).orElseThrow());
    bestSellersRepository.save(bestSellers);

    return bestSellersMapper.bestSellersToBestSellersDto(bestSellersRepository.findAll());
  }

  private void isBestSellersListReachedMaximum() {
    int sizeBestSellersList = getSizeBestSellersList(bestSellersRepository.findAll());
    if (sizeBestSellersList >= 5) throw new ReachedMaximumNumberOfGoods();
  }

  private int getSizeBestSellersList(Iterable<BestSellers> bestSellersIterable) {
    int sizeBestSellersList = 0;
    for (BestSellers product : bestSellersIterable) {
      sizeBestSellersList += 1;
    }

    return sizeBestSellersList;
  }
}
