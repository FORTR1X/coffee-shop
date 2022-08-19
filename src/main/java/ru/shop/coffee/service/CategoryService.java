package ru.shop.coffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shop.coffee.dto.CompanyCategoryDto;
import ru.shop.coffee.dto.category.CategoryCreateDto;
import ru.shop.coffee.dto.category.CategoryDto;
import ru.shop.coffee.dto.subcategory.SubcategoryDto;
import ru.shop.coffee.entity.Category;
import ru.shop.coffee.entity.CompanyCategory;
import ru.shop.coffee.mapper.CategoryMapper;
import ru.shop.coffee.mapper.SubcategoryMapper;
import ru.shop.coffee.repository.CategoryRepository;
import ru.shop.coffee.repository.CompanyCategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryMapper categoryMapper;
  private final SubcategoryMapper subcategoryMapper;

  private final CategoryRepository categoryRepository;
  private final CompanyCategoryRepository companyCategoryRepository;

  public CategoryDto create(CategoryCreateDto request) {

    Category category = categoryMapper.toCategory(request);

    categoryRepository.save(category);

    return categoryMapper.categoryToCategoryDto(category);
  }

  public CategoryDto getById(Integer id) {
    return categoryMapper.categoryToCategoryDto(categoryRepository.findById(id).orElseThrow());
  }

  public Iterable<CategoryDto> getAllCategories() {
    return categoryMapper.categoryListToCategoryListDto(categoryRepository.findAll());
  }

  public void deleteById(Integer id) {
    categoryRepository.deleteById(id);
  }

  public List<SubcategoryDto> getSubcategoriesByCategoryId(Integer id) {

    return subcategoryMapper.subcategoriesToSubcategoriesDto(categoryRepository
            .findById(id)
            .orElseThrow()
            .getSubcategoryList());
  }

  public Iterable<CompanyCategory> getCompanyCategories() {
    return companyCategoryRepository.findAll();
  }
}
