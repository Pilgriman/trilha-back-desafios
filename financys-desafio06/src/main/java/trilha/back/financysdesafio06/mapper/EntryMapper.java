package trilha.back.financysdesafio06.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import trilha.back.financysdesafio06.dto.EntryDTO;
import trilha.back.financysdesafio06.entities.Entry;


@Mapper(componentModel = "spring")
public interface EntryMapper {

    @Mapping(target = "id", source = "entryDTO.id")
    @Mapping(target = "name", source ="entryDTO.name" )
    @Mapping(target = "type", source ="entryDTO.type" )
    @Mapping(target = "amount", source ="entryDTO.amount" )
    Entry toModel(EntryDTO entryDTO);

    @Mapping(target = "id", source = "entry.id")
    @Mapping(target = "name", source ="entry.name" )
    @Mapping(target = "type", source ="entry.type" )
    @Mapping(target = "amount", source ="entry.amount" )
    EntryDTO toDTO(Entry entry);
}
