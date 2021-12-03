package trilha.back.financysdesafio12.testes;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import trilha.back.financysdesafio12.entities.Entry;
import trilha.back.financysdesafio12.exceptions.EntryNullResquest;
import trilha.back.financysdesafio12.exceptions.NoContentException;
import trilha.back.financysdesafio12.mappers.EntryMapper;
import trilha.back.financysdesafio12.repositories.EntryRepository;
import trilha.back.financysdesafio12.services.EntryService;

import java.util.ArrayList;
import java.util.List;



import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TrilhaBackTesteService {

    @Mock
    private EntryRepository entryRepository;


    @InjectMocks
    private EntryService entryService;

    @Mock
    private EntryMapper entryMapper;


    @Test
    void shouldCreateEntries() throws NoContentException, EntryNullResquest{
       List<Entry> entries = new ArrayList<>();
       Entry entry =new Entry();
       entry.setDate("26/11/2021");
       entry.setAmount("7.70");
       entry.setPaid(false);
       entries.add(entry);


        //when
        when(entryRepository.findAll()).thenReturn(entries);

        //then
        Assertions.assertFalse(entryService.getAll().isEmpty());
        Assertions.assertEquals(1,entryService.getLancamentosDependentes("26/11/2021","7.70",false).size());
    }

    @Test
    void shouldThrwonRunTimeExceptionWhenDateIsNull(){
        assertThrows(EntryNullResquest.class, () ->
                entryService.getLancamentosDependentes(null, "5.70",false));
    }

    @Test
    void shouldThrwonRunTimeExceptionWhenAmountIsNull(){
        assertThrows(EntryNullResquest.class, () ->
                entryService.getLancamentosDependentes("25/11/2021", null,false)); }

    @Test
    void shouldThrwonRunTimeExceptionWhenEntriesAreEmpty() { assertThrows( NoContentException.class, () ->
                entryService.getLancamentosDependentes("", "", false));
    }
}
