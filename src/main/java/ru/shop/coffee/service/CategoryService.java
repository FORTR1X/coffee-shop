package ru.shop.coffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shop.coffee.dto.category.CategoryCreateDto;
import ru.shop.coffee.dto.category.CategoryDto;
import ru.shop.coffee.entity.Category;
import ru.shop.coffee.mapper.CategoryMapper;
import ru.shop.coffee.repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryMapper categoryMapper;

  private final CategoryRepository categoryRepository;

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
}
