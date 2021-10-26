package trilha.back.financysdesafio05.mapper;

import org.mapstruct.Mapper;
import trilha.back.financysdesafio05.entities.Entry;
import trilha.back.financysdesafio05.entryDTO.EntryDTO;

@Mapper(componentModel = "spring")
public interface EntryMapper {

    Entry toModel(EntryDTO entryDTO);

    EntryDTO toDTO(Entry entry);
}
