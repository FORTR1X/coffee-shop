package ru.shop.coffee.mapper;

import org.mapstruct.Mapper;
import ru.shop.coffee.dto.product.ProductCreateDto;
import ru.shop.coffee.dto.product.ProductDto;
import ru.shop.coffee.dto.product.ProductUpdateDto;
import ru.shop.coffee.entity.Product;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ProductMapper {

  List<ProductDto> productsToProductsDto(List<Product> productList);

  List<Product> productsDtoToProducts(List<ProductDto> productDtoList);

  ProductDto toProductDto(Product product);

  Product productCreateDtoToProduct(ProductCreateDto productCreateDto);

  Product productUpdateRequestToProductView(Integer id, ProductUpdateDto productUpdateDto);


}
