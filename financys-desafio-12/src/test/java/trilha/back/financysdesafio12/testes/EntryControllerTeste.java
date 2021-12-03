package trilha.back.financysdesafio12.testes;

import io.swagger.models.auth.In;
import lombok.val;
import org.mockito.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import trilha.back.financysdesafio12.controllers.EntryController;
import trilha.back.financysdesafio12.dto.CategoryDto;
import trilha.back.financysdesafio12.dto.EntryDTO;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import trilha.back.financysdesafio12.entities.Category;
import trilha.back.financysdesafio12.entities.Entry;
import trilha.back.financysdesafio12.exceptions.CalculateException;
import trilha.back.financysdesafio12.exceptions.EntryAlreadyRegisteredException;
import trilha.back.financysdesafio12.services.EntryCalculateService;
import trilha.back.financysdesafio12.services.EntryService;


import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static trilha.back.financysdesafio12.testes.JsonConvertionUtils.asJsonString;

@ExtendWith(MockitoExtension.class)
public class EntryControllerTeste {


    private static final String ENTRY_API_URL_PATH = "/lancamentos";


    private MockMvc mockMvc;

    @Mock
    private EntryService entryService;

    @Mock
    private EntryCalculateService entryCalculateService;

    @InjectMocks
    private EntryController entryController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(entryController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();

    }

    @Test
    void whenPOSTIsCalledThenEntryIsCreated() throws Exception {
        // given
        EntryDTO entryDto = EntryBuilderDTO.builder().build().toDTO();

        // when
        when(entryService.save(entryDto)).thenReturn(entryDto);

        // then
        mockMvc.perform(post(ENTRY_API_URL_PATH + "/adicionar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(entryDto)))
                .andExpect(status().isCreated());


    }

    @Test
    void whenGETIsCalledWithValidIdThenOkStatusIsReturned() throws Exception {
        // given
        EntryDTO entryDto = EntryBuilderDTO.builder().build().toDTO();

        //when
        when(entryService.get(entryDto.getId())).thenReturn(entryDto);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(ENTRY_API_URL_PATH  + "/" + entryDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenGETListEntriesIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        EntryDTO entryDto = EntryBuilderDTO.builder().build().toDTO();

        //when
        when(entryService.getAll()).thenReturn(Collections.singletonList(entryDto));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(ENTRY_API_URL_PATH  + "/consultar")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenUpdateIsCalledThenOkStatusIsReturned() throws Exception {
        //given]
        Category category = new Category();
        EntryDTO entryDto = EntryBuilderDTO.builder().build().toDTO();
        entryDto.setCategoryId(category);
        category.setId(1L);
        category.setCategoryName("testeteste1");



        //when
        when(entryService.update(1L)).thenReturn(entryDto);

        //then
        mockMvc.perform(MockMvcRequestBuilders.put(ENTRY_API_URL_PATH  + "/" + entryDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        // given
        EntryDTO entryDto = EntryBuilderDTO.builder().build().toDTO();


        //when
        doNothing().when(entryService).delete(entryDto.getId());

        // then
        mockMvc.perform(MockMvcRequestBuilders.delete( ENTRY_API_URL_PATH + "/" + entryDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


    @Test
    void whenEntryCalculateIsCalledThenOkStatusIsReturned() throws Exception {
        //given
     Integer x = 10;
     Integer y = 5;


        //when
        when(entryCalculateService.getEverage(x,y)).thenReturn(x/y);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get(ENTRY_API_URL_PATH  + "/calcula" + "/" + 10 + "/" + 5   )
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


    }




