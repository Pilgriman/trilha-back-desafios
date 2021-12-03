package trilha.back.financysdesafio12.mappers;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;
import trilha.back.financysdesafio12.dto.EntryDTO;
import trilha.back.financysdesafio12.entities.Entry;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-11-29T22:56:41-0300",
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
        entry.setAmount( entryDTO.getAmount() );
        entry.setDate( entryDTO.getDate() );
        entry.setPaid( entryDTO.isPaid() );
        entry.setCategoryId( entryDTO.getCategoryId() );

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
        entryDTO.setAmount( entry.getAmount() );
        entryDTO.setDate( entry.getDate() );
        entryDTO.setPaid( entry.getPaid() );
        entryDTO.setCategoryId( entry.getCategoryId() );

        return entryDTO;
    }
}
