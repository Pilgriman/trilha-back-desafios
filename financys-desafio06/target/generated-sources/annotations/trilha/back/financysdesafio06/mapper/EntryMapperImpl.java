package trilha.back.financysdesafio06.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import trilha.back.financysdesafio06.dto.EntryDTO;
import trilha.back.financysdesafio06.entities.Entry;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-02T10:49:35-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (JetBrains s.r.o.)"
)
@Component
public class EntryMapperImpl implements EntryMapper {

    @Override
    public Entry toModel(EntryDTO entryDTO) {
        if ( entryDTO == null ) {
            return null;
        }

        Entry entry = new Entry();

        entry.setId( entryDTO.getId() );
        entry.setName( entryDTO.getName() );
        entry.setType( entryDTO.getType() );
        entry.setAmount( entryDTO.getAmount() );

        return entry;
    }

    @Override
    public EntryDTO toDTO(Entry entry) {
        if ( entry == null ) {
            return null;
        }

        EntryDTO entryDTO = new EntryDTO();

        entryDTO.setId( entry.getId() );
        entryDTO.setName( entry.getName() );
        entryDTO.setType( entry.getType() );
        entryDTO.setAmount( entry.getAmount() );

        return entryDTO;
    }
}
