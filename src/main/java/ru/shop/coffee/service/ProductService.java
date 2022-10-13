package ru.shop.coffee.service;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ru.shop.coffee.dto.product.ProductCreateDto;
import ru.shop.coffee.dto.product.ProductDto;
import ru.shop.coffee.dto.product.ProductUpdateDto;
import ru.shop.coffee.entity.Product;
import ru.shop.coffee.entity.Subcategory;
import ru.shop.coffee.mapper.ProductMapper;
import ru.shop.coffee.repository.ProductRepository;
import ru.shop.coffee.repository.SubcategoryRepository;

import javax.mail.FolderNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

  private void makeDirectoryIfNotExist(String imageDirectory) {
    File directory = new File(imageDirectory);
    if (!directory.exists()) {
      directory.mkdir();
    }
  }

  private void deleteImagesByDirectory(File directory) {
    try {
      File[] currentImages = directory.listFiles();
      if (currentImages != null && currentImages.length > 0) {
        for (File image : currentImages) {
          image.delete();
        }
      }
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  private void writeProductImages(String productDir, MultipartFile[] files) {
    try {
      for (int i = 0; i < files.length; i++) {
        Path productDirPath = Paths.get(productDir + "\\" + i + ".jpg");
        Files.write(productDirPath, files[i].getBytes());
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public void uploadImages(MultipartFile[] files, Integer id) {
    final String PRODUCT_DIR = UPLOADS_ABSOLUTE_PATH + "\\product\\" + id;
    try {
      makeDirectoryIfNotExist(PRODUCT_DIR);
      writeProductImages(PRODUCT_DIR, files);
    } catch (Exception e) { System.out.println(e.toString()); }
  }

  public List<String> getImagesProductById(Integer id) {
    try {
      File directory = new File(UPLOADS_ABSOLUTE_PATH + "\\product\\" + id);
      if (!directory.exists()) return new ArrayList<>();

      File[] imageListFiles = directory.listFiles();
      if (imageListFiles == null) return new ArrayList<>();

      List<String> urlPathImages = new ArrayList<>();
      for (File image : imageListFiles) {
        urlPathImages.add("/uploads/product/" + id + "/" + image.getName());
      }

      return urlPathImages;
    } catch (Exception e) {
      System.out.println(e.toString());
    }

    return null;
  }

  public void updateProductImagesById(Integer id, MultipartFile[] files) {
    final String PRODUCT_DIR = UPLOADS_ABSOLUTE_PATH + "\\product\\" + id;
    try {
      File directory = new File(PRODUCT_DIR);

      makeDirectoryIfNotExist(PRODUCT_DIR);
      deleteImagesByDirectory(directory);
      writeProductImages(PRODUCT_DIR, files);

    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

}

// git commit -m "Product: optimize method getProductsBySubcatId, added sort, pagination, find by price for this method"
