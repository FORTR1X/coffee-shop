package ru.shop.coffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.shop.coffee.dto.product.ProductCreateDto;
import ru.shop.coffee.dto.product.ProductDto;
import ru.shop.coffee.dto.product.ProductUpdateDto;
import ru.shop.coffee.entity.Product;
import ru.shop.coffee.entity.Subcategory;
import ru.shop.coffee.mapper.ProductMapper;
import ru.shop.coffee.repository.ProductRepository;
import ru.shop.coffee.repository.SubcategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductMapper productMapper;

  private final ProductRepository productRepository;
  private final SubcategoryRepository subcategoryRepository;

  private static final int DEFAULT_PAGE_LIMIT_SIZE = 10;
  private static final int DEFAULT_PAGE_SIZE = 0;

  private Sort getSortParam(String sort, String sortBy) {
    if (sort.equals("asc")) return Sort.by(sortBy).ascending();
    if (sort.equals("desc")) return Sort.by(sortBy).descending();
    return Sort.by("id");
  }

  public List<ProductDto> getProductsBySubId(Integer subId, Integer limit, Integer page, String sort) {

    Subcategory subcategory = subcategoryRepository.findById(subId).orElseThrow();

    List<Product> productList = productRepository.findAllBySubcategory(subcategory,
            PageRequest.of(page == null ? DEFAULT_PAGE_SIZE : page, limit == null ? DEFAULT_PAGE_LIMIT_SIZE : limit,
                    getSortParam(sort == null ? "id" : sort, "price")));

    return productMapper.productsToProductsDto(productList);
  }

  public ProductDto create(ProductCreateDto request) {

    Product product = productMapper.productCreateDtoToProduct(request);
    productRepository.save(product);

    return productMapper.toProductDto(product);

  }

  public ProductDto getById(Integer id) {
    return productMapper.toProductDto(productRepository.findById(id).orElseThrow());
  }

  public ProductDto updateById(Integer id, ProductUpdateDto request) {

    Product prevProduct = productRepository.findById(id).orElseThrow();

    Product newProduct = productMapper.productUpdateRequestToProductView(prevProduct.getId(), request);
    productRepository.save(newProduct);

    return productMapper.toProductDto(newProduct);

  }

  public void deleteById(Integer id) {

    productRepository.deleteById(id);

  }

  public List<ProductDto> getProductsByIds(List<Integer> productsIdsList) {
    List<Product> productList = new ArrayList<>();

    productsIdsList.forEach(productRepository::findById);

    productsIdsList.forEach(integer -> {
      if (productRepository.findById(integer).isPresent())
        productList.add(productRepository.findById(integer).orElseThrow());
    });

    return productMapper.productsToProductsDto(productList);
  }

  public Integer getCountProductsBySubcatId(Integer subcatId) {
    Subcategory subcategory = subcategoryRepository.findById(subcatId).orElseThrow();

    return productRepository.findAllBySubcategory(subcategory).size();
  }

  public List<ProductDto> getProductsBySubcatIdAndPriceBetween(Integer subcatId, Integer priceFrom,
                                                               Integer priceTo, Integer limit,
                                                               Integer page, String sort) {

    Subcategory subcategory = subcategoryRepository.findById(subcatId).orElseThrow();

    List<Product> productList = productRepository.findAllBySubcategoryAndPriceBetween(
            subcategory,
            priceFrom == null ? 0 : priceFrom,
            priceTo == null ? 99999 : priceTo,
            PageRequest.of(page == null ? DEFAULT_PAGE_SIZE : page, limit == null ? DEFAULT_PAGE_LIMIT_SIZE : limit,
                    getSortParam(sort == null ? "id" : sort, "price")));

    return productMapper.productsToProductsDto(productList);
  }
}
