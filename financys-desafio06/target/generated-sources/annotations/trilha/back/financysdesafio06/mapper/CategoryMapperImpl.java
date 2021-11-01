package trilha.back.financysdesafio06.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import trilha.back.financysdesafio06.dto.CategoryDto;
import trilha.back.financysdesafio06.entities.Category;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-01T16:53:29-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (JetBrains s.r.o.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category mapToCategory(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setCategoryName( categoryDto.getName() );
        category.setDescription( categoryDto.getDescription() );

        return category;
    }

    @Override
    public CategoryDto mapCategoryToDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setName( category.getCategoryName() );
        categoryDto.setDescription( category.getDescription() );

        return categoryDto;
    }
}
