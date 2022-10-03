package ru.shop.coffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.shop.coffee.dto.product.ProductCreateDto;
import ru.shop.coffee.dto.product.ProductDto;
import ru.shop.coffee.dto.product.ProductUpdateDto;
import ru.shop.coffee.entity.Product;
import ru.shop.coffee.entity.Subcategory;
import ru.shop.coffee.mapper.ProductMapper;
import ru.shop.coffee.repository.ProductRepository;
import ru.shop.coffee.repository.SubcategoryRepository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductMapper productMapper;

  private final ProductRepository productRepository;
  private final SubcategoryRepository subcategoryRepository;

  private static final int DEFAULT_PAGE_LIMIT_SIZE = 20;
  private static final int DEFAULT_PAGE_SIZE = 0;
  private static final String UPLOADS_ABSOLUTE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploads";

  public List<ProductDto> getProductsBySubId(String subId, String limit, String page, String sort) {
    try {
      Subcategory subcategory = subcategoryRepository.findById(Integer.parseInt(subId)).orElseThrow();

      List<Product> productList = productRepository.findAllBySubcategory(subcategory,
              PageRequest.of(page == null ? DEFAULT_PAGE_SIZE : Integer.parseInt(page), limit == null ? DEFAULT_PAGE_LIMIT_SIZE : Integer.parseInt(limit),
                      getSortParam(sort == null ? "id" : sort, "price")));

      return productMapper.productsToProductsDto(productList);
    } catch (NumberFormatException exception) {
      System.out.println(exception.toString());
    }

    return null;
  }

  private Sort getSortParam(String sort, String sortBy) {
    if (sort.equals("asc")) return Sort.by(sortBy).ascending();
    if (sort.equals("desc")) return Sort.by(sortBy).descending();
    return Sort.by("id");
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

  public void deleteAllById(List<Integer> ids) {
    productRepository.deleteAllById(ids);
  }

  public List<ProductDto> getProductsByIds(List<Integer> productsIdsList) {
    List<Product> productList = new ArrayList<>();

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

  public List<ProductDto> getProductsBySubcatIdAndPriceBetween(String subcatId, String priceFrom,
                                                               String priceTo, String limit,
                                                               String page, String sort) {

    try {
      Subcategory subcategory = subcategoryRepository.findById(Integer.parseInt(subcatId)).orElseThrow();

      List<Product> productList = productRepository.findAllBySubcategoryAndPriceBetween(
              subcategory,
              priceFrom == null ? 0 : Integer.parseInt(priceFrom),
              priceTo == null ? 99999 : Integer.parseInt(priceTo),
              PageRequest.of(page == null ? DEFAULT_PAGE_SIZE :  Integer.parseInt(page), limit == null ? DEFAULT_PAGE_LIMIT_SIZE :  Integer.parseInt(limit),
                      getSortParam(sort == null ? "id" : sort, "price")));

      return productMapper.productsToProductsDto(productList);
    } catch (NumberFormatException exception) { System.out.println(exception.toString()); }

    return null;
  }

  public List<ProductDto> getProductsByRequest(String request) {
    return productMapper.productsToProductsDto(productRepository.findProductsByHeaderContainsOrDescriptionContains(request, request));
  }

  public List<ProductDto> getAllProducts() {
    List<Product> productList = new ArrayList<>();
    Iterable<Product> productIterable = productRepository.findAll();
    productIterable.forEach(productList::add);

    return productMapper.productsToProductsDto(productList);
  }



  public void uploadImages(MultipartFile[] files, Integer id) {
    final String PRODUCT_DIR = UPLOADS_ABSOLUTE_PATH + "\\product\\" + id;
    try {
      makeDirectoryIfNotExist(PRODUCT_DIR);

      for (int i = 0; i < files.length; i++) {g
        Path productDirPath = Paths.get(PRODUCT_DIR + "\\" + i + ".jpg");
        Files.write(productDirPath, files[i].getBytes());
      }
    } catch (Exception e) { System.out.println(e.toString()); }
  }

  private void makeDirectoryIfNotExist(String imageDirectory) {
    File directory = new File(imageDirectory);
    if (!directory.exists()) {
      directory.mkdir();
    }
  }
}

// git commit -m "Product: optimize method getProductsBySubcatId, added sort, pagination, find by price for this method"
