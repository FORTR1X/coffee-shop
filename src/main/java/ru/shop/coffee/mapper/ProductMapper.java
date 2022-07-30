package ru.shop.coffee.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Pageable;
import ru.shop.coffee.dto.product.ProductCreateDto;
import ru.shop.coffee.dto.product.ProductDto;
import ru.shop.coffee.dto.product.ProductUpdateDto;
import ru.shop.coffee.entity.Product;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ProductMapper {

  List<ProductDto> productsToProductsDto(List<Product> productList);
  ProductDto toProductDto(Product product);
  Product productCreateDtoToProduct(ProductCreateDto productCreateDto);
  Product productUpdateRequestToProductView(Integer id, ProductUpdateDto productUpdateDto);


}