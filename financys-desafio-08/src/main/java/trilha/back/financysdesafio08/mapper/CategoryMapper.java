package trilha.back.financysdesafio08.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import trilha.back.financysdesafio08.dto.CategoryDto;
import trilha.back.financysdesafio08.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {


    @Mapping(target = "categoryName", source="categoryDto.name")
    @Mapping(target = "description", source = "categoryDto.description")
    public Category mapToCategory(CategoryDto categoryDto);

    @Mapping(target = "name",source="category.categoryName")
    @Mapping(target = "description",source="category.description")
    public CategoryDto mapCategoryToDto(Category category);
}
