package trilha.back.financysdesafio12.testes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import trilha.back.financysdesafio12.dto.CategoryDto;
import trilha.back.financysdesafio12.dto.EntryDTO;
import trilha.back.financysdesafio12.entities.Category;
import trilha.back.financysdesafio12.entities.Entry;
import trilha.back.financysdesafio12.enums.EntryType;
import trilha.back.financysdesafio12.exceptions.EntryNotFoundException;
import trilha.back.financysdesafio12.mappers.EntryMapper;
import trilha.back.financysdesafio12.repositories.CategoryRepository;
import trilha.back.financysdesafio12.repositories.EntryRepository;
import trilha.back.financysdesafio12.services.EntryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EntryServiceTeste {

    @Mock
    private EntryRepository entryRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private EntryService entryService;

    @Mock
    private EntryMapper entryMapper;


            @Test
            void whenEntryUpdateThenItShouldBeUpdated() throws Exception {
                //given

                List<Entry> entries = new ArrayList<>();
                Category category = new Category(1L, "testeteste1","testetestetesteteste", entries);

                Entry entry = new Entry();
                entry.setId(1L);
                entry.setName("testeteste1");
                entry.setDescription("testetestetesteteste");
                entry.setType(EntryType.PAGO);
                entry.setAmount("5.69");
                entry.setDate("25/11/2021");
                entry.setPaid(false);
                entry.setCategoryId(category);

                entries.add(entry);

                //when
                when(entryRepository.findById(1L)).thenReturn(Optional.of(entry));
                when(entryRepository.save(entry)).thenReturn(entry);


                //then
                Assertions.assertNull(entryService.update(1L));


            }





}
