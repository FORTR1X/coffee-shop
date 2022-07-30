package ru.shop.coffee.mapper;

import org.mapstruct.Mapper;
import ru.shop.coffee.dto.subcategory.SubcategoryCreateDto;
import ru.shop.coffee.dto.subcategory.SubcategoryDto;
import ru.shop.coffee.entity.Subcategory;

import static org.mapstruct.ReportingPolicy.IGNORE;
@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface SubcategoryMapper {

  Subcategory toSubcategory(SubcategoryCreateDto subcategoryCreateDto);
  SubcategoryDto subcategoryToSubcategoryDto(Subcategory subcategory);

}
