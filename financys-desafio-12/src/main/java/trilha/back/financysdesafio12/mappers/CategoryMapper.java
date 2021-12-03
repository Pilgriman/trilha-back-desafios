package trilha.back.financysdesafio12.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import trilha.back.financysdesafio12.dto.CategoryDto;
import trilha.back.financysdesafio12.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", source="categoryDto.id")
    @Mapping(target = "categoryName", source="categoryDto.name")
    @Mapping(target = "description", source = "categoryDto.description")
    Category mapToCategory(CategoryDto categoryDto);

    @Mapping(target = "id",source="category.id")
    @Mapping(target = "name",source="category.categoryName")
    @Mapping(target = "description",source="category.description")
    CategoryDto mapCategoryToDto(Category category);
}
