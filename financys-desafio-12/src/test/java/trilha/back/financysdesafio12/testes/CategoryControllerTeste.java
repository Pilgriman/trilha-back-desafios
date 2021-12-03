package trilha.back.financysdesafio12.testes;

import org.mockito.*;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import trilha.back.financysdesafio12.controllers.CategoryController;
import trilha.back.financysdesafio12.dto.CategoryDto;
import trilha.back.financysdesafio12.dto.EntryDTO;
import trilha.back.financysdesafio12.entities.Category;
import trilha.back.financysdesafio12.services.CategoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.sql.Types;
import java.util.Collections;
import java.util.List;


import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static trilha.back.financysdesafio12.testes.JsonConvertionUtils.asJsonString;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTeste {

    private static final String CATEGORY_API_URL_PATH = "/categorias";


    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();

    }

    @Test
    void whenPOSTIsCalledThenACategoryIsCreated() throws Exception {
        // given
        CategoryDto categoryDto = CategoryBuilderDTO.builder().build().mapCategoryToDto();

        // when
        when(categoryService.create(categoryDto)).thenReturn(categoryDto);

        // then
        mockMvc.perform(post(CATEGORY_API_URL_PATH + "/adicionar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(categoryDto)))
                .andExpect(status().isCreated());


    }


        @Test
        void whenGETIsCalledWithValidIdThenOkStatusIsReturned() throws Exception {
             // given
            CategoryDto categoryDto = CategoryBuilderDTO.builder().build().mapCategoryToDto();

            //when
            when(categoryService.get(categoryDto.getId())).thenReturn(categoryDto);

            // then
            mockMvc.perform(MockMvcRequestBuilders.get(CATEGORY_API_URL_PATH + "/" + categoryDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

        @Test
        void whenGETListCategoriesIsCalledThenOkStatusIsReturned() throws Exception {
            // given
            CategoryDto  categoryDto = CategoryBuilderDTO.builder().build().mapCategoryToDto();

            //when
            when(categoryService.getAll()).thenReturn(Collections.singletonList(categoryDto));

            // then
            mockMvc.perform(MockMvcRequestBuilders.get(CATEGORY_API_URL_PATH + "/consultar")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

        @Test
        void whenUPDATECategoryIsCalledThenOkStatusIsReturned() throws Exception {
            //given
            CategoryDto  categoryDto = CategoryBuilderDTO.builder().build().mapCategoryToDto();


            //when
            when(categoryService.update(1L)).thenReturn(categoryDto);

            //then
            mockMvc.perform(MockMvcRequestBuilders.put( CATEGORY_API_URL_PATH + "/" + categoryDto.getId())
            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

        }

        @Test
         void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
            // given
            CategoryDto  categoryDto = CategoryBuilderDTO.builder().build().mapCategoryToDto();

            //when
            doNothing().when(categoryService).delete(categoryDto.getId());

            // then
            mockMvc.perform(MockMvcRequestBuilders.delete( CATEGORY_API_URL_PATH + "/" + categoryDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }



}







