package ru.shop.coffee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shop.coffee.dto.category.CategoryDto;
import ru.shop.coffee.dto.subcategory.SubcategoryCreateDto;
import ru.shop.coffee.dto.subcategory.SubcategoryDto;
import ru.shop.coffee.entity.Category;
import ru.shop.coffee.entity.Subcategory;
import ru.shop.coffee.mapper.CategoryMapper;
import ru.shop.coffee.mapper.SubcategoryMapper;
import ru.shop.coffee.repository.SubcategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategoryService {

  private final SubcategoryMapper subcategoryMapper;
  private final CategoryMapper categoryMapper;

  private final SubcategoryRepository subcategoryRepository;

  public SubcategoryDto create(SubcategoryCreateDto request) {

    Subcategory subcategory = subcategoryMapper.toSubcategory(request);
    subcategoryRepository.save(subcategory);

    return subcategoryMapper.subcategoryToSubcategoryDto(subcategory);

  }

  public CategoryDto getCategoryBySubcatId(Integer id) {

    Category category = subcategoryRepository.findById(id).orElseThrow().getCategory();
    return categoryMapper.categoryToCategoryDto(category);

  }

  public SubcategoryDto getSubcategoryById(Integer id) {
    return subcategoryMapper.subcategoryToSubcategoryDto(subcategoryRepository.findById(id).orElseThrow());
  }

  public void deleteById(Integer id) {
    subcategoryRepository.findById(id).orElseThrow();
    subcategoryRepository.deleteById(id);
  }

  public List<SubcategoryDto> getSubcategiries() {
    List<Subcategory> subcategoryList = new ArrayList<>();

    Iterable<Subcategory> subcategories = subcategoryRepository.findAll();
    for (Subcategory subcategory : subcategories) {
      subcategoryList.add(subcategory);
    }
    return subcategoryMapper.subcategoriesToSubcategoriesDto(subcategoryList);
  }
}
