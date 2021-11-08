package trilha.back.financysdesafio09.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import trilha.back.financysdesafio09.dto.CategoryDto;
import trilha.back.financysdesafio09.entities.Category;
import trilha.back.financysdesafio09.service.ConverterService;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends ConverterService {


    @Mapping(target = "categoryName", source="categoryDto.name")
    @Mapping(target = "description", source = "categoryDto.description")
    public Category mapToCategory(CategoryDto categoryDto);

    @Mapping(target = "name",source="category.categoryName")
    @Mapping(target = "description",source="category.description")
    public CategoryDto mapCategoryToDto(Category category);
}
