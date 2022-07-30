package ru.shop.coffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shop.coffee.dto.product.ProductCreateDto;
import ru.shop.coffee.dto.product.ProductDto;
import ru.shop.coffee.dto.product.ProductUpdateDto;
import ru.shop.coffee.entity.Product;
import ru.shop.coffee.mapper.ProductMapper;
import ru.shop.coffee.repository.ProductRepository;
import ru.shop.coffee.repository.SubcategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductMapper productMapper;

  private final ProductRepository productRepository;
  private final SubcategoryRepository subcategoryRepository;

  private static final int DEFAULT_PAGE_LIMIT_SIZE = 10;
  private static final int DEFAULT_PAGE_SIZE = 0;

  public List<ProductDto> getProductsBySubcatId(Integer subcatId, Integer limit, Integer page) {

    limit = limit == null ? DEFAULT_PAGE_LIMIT_SIZE : limit;
    page = page == null ? DEFAULT_PAGE_SIZE : page;

    System.out.println("limit: " + limit + "|| page: " + page);

    List<Product> productList = subcategoryRepository.findById(subcatId)
            .orElseThrow()
            .getProducts();

    if (productList.isEmpty()) return null;

    int fromIndex = Math.min(productList.size(), limit * page);
    int toIndex = Math.min(productList.size(), fromIndex + limit);

    return productMapper.productsToProductsDto(productList.subList(fromIndex, toIndex));
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
}
