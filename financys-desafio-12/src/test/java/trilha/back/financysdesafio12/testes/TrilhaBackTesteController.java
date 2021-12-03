package trilha.back.financysdesafio12.testes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import trilha.back.financysdesafio12.controllers.EntryController;
import trilha.back.financysdesafio12.dto.EntryDTO;
import trilha.back.financysdesafio12.services.EntryService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TrilhaBackTesteController {
    private static final String ENTRY_API_URL_PATH = "/lancamentos";


    private MockMvc mockMvc;

    @Mock
    private EntryService entryService;

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
    void whenGetFILTERIsCalledThenOkStatusIsReturned() throws Exception {
        // given
        EntryDTO entryDto = new EntryDTO();
        entryDto.setDate(entryDto.getDate());
        entryDto.setAmount(entryDto.getAmount());
        entryDto.setPaid(entryDto.isPaid());



        //when
        when(entryService.getLancamentosDependentes(entryDto.getDate(), entryDto.getAmount(), entryDto.isPaid())).thenReturn(Collections.singletonList(entryDto));

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(ENTRY_API_URL_PATH  + "/filter")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    }


