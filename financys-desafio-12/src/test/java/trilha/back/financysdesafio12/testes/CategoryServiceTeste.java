package trilha.back.financysdesafio12.testes;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;
import trilha.back.financysdesafio12.dto.CategoryDto;
import trilha.back.financysdesafio12.entities.Category;
import trilha.back.financysdesafio12.entities.Entry;
import trilha.back.financysdesafio12.enums.EntryType;
import trilha.back.financysdesafio12.exceptions.CategoryAlreadyRegisteredException;
import trilha.back.financysdesafio12.exceptions.EntryNullResquest;
import trilha.back.financysdesafio12.mappers.CategoryMapper;
import trilha.back.financysdesafio12.repositories.CategoryRepository;
import trilha.back.financysdesafio12.services.CategoryService;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTeste {

        @Mock
        private CategoryRepository categoryRepository;


        @InjectMocks
        private CategoryService categoryService;

        @Mock
        private CategoryMapper categoryMapper;




        @Test
        void whenCategoryInformedThenItShouldBeCreated() throws CategoryAlreadyRegisteredException {
            //given
            CategoryDto expectedCategoryDto = CategoryBuilderDTO.builder().build().mapCategoryToDto();
            Category expectedSavedCategory = categoryMapper.mapToCategory(expectedCategoryDto);


            //when

            when(categoryRepository.findByCategoryName(expectedCategoryDto.getName())).thenReturn(null);
            when(categoryRepository.save(expectedSavedCategory)).thenReturn(expectedSavedCategory);

            //then
            CategoryDto createdCategoryDTO = categoryService.create(expectedCategoryDto);

            assertThat(createdCategoryDTO.getId(), is(equalTo(expectedCategoryDto.getId())));
            assertThat(createdCategoryDTO.getName(), is(equalTo(expectedCategoryDto.getName())));
            assertThat(createdCategoryDTO.getDescription(), is(equalTo(expectedCategoryDto.getDescription())));

        }

        @Test
        void whenAlreadyRegisteredCategoryInformedThenAnExceptionShouldBeThrown(){
            // given
            List<Entry> entries = new ArrayList<>();
            Entry entry = new Entry();
            entry.setId(1L);
            entry.setName("testetestetesteteste2");
            entry.setDescription("testetestetesteteste");
            entry.setType(EntryType.PAGO);
            entry.setAmount("5.69");
            entry.setDate("25/11/2021");
            entry.setPaid(false);

            entries.add(entry);

            CategoryDto expectedCategoryDto = CategoryBuilderDTO.builder().build().mapCategoryToDto();
            Category category = new Category(1L, "testeteste1","testetestetesteteste", entries);
            categoryMapper.mapCategoryToDto(category);



            // when
            when(categoryRepository.findByCategoryName(category.getCategoryName())).thenReturn(category);

            // then
            assertThrows(CategoryAlreadyRegisteredException.class, () -> categoryService.create(expectedCategoryDto));
    }


        @Test
        void whenGetAllIsCalledThenReturnAllCategories() {

            List<Entry> entries = new ArrayList<>();


            List<CategoryDto> expectedCategoryDto = new ArrayList<>();
            CategoryDto categoryDto = CategoryBuilderDTO.builder().build().mapCategoryToDto();
            expectedCategoryDto.add(categoryDto);

            Category category = new Category(1L, "testeteste1","testetestetesteteste", entries);
            List<Category> categories = new ArrayList<>();
            categories.add(category);


            Entry entry = new Entry();
            entry.setId(1L);
            entry.setName("testetestetesteteste2");
            entry.setDescription("testetestetesteteste");
            entry.setType(EntryType.PAGO);
            entry.setAmount("5.69");
            entry.setDate("25/11/2021");
            entry.setPaid(false);
            entry.setCategoryId(category);

            entries.add(entry);
            //when
            when(categoryRepository.findAll()).thenReturn(categories);

            //then
            Assertions.assertEquals(1,categoryService.getAll().add(categoryDto));

        }

}
