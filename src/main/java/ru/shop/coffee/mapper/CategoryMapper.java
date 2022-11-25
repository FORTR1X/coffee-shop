package ru.shop.coffee.mapper;

import org.mapstruct.Mapper;
import ru.shop.coffee.dto.category.CategoryCreateDto;
import ru.shop.coffee.dto.category.CategoryDto;
import ru.shop.coffee.entity.Category;

import java.util.List;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedTargetPolicy = WARN)
public interface CategoryMapper {

  Category toCategory(CategoryCreateDto categoryCreateDto);

  CategoryDto categoryToCategoryDto(Category category);

  List<CategoryDto> categoryListToCategoryListDto(List<Category> categoryList);

}
