package trilha.back.financysdesafio07.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import trilha.back.financysdesafio07.dto.EntryDTO;
import trilha.back.financysdesafio07.entities.Category;
import trilha.back.financysdesafio07.entities.Entry;


@Mapper(componentModel = "spring")
public interface EntryMapper {

    @Mapping(target = "id", source = "entryDTO.id")
    @Mapping(target = "name", source ="entryDTO.name" )
    @Mapping(target = "type", source ="entryDTO.type" )
    @Mapping(target = "amount", source ="entryDTO.amount" )
    @Mapping(target = "date", source ="entryDTO.date")
    @Mapping(target = "paid", source = "entryDTO.paid")
    Entry toModel(EntryDTO entryDTO);

    @Mapping(target = "id", source = "entry.id")
    @Mapping(target = "name", source ="entry.name" )
    @Mapping(target = "type", source ="entry.type" )
    @Mapping(target = "amount", source ="entry.amount" )
    @Mapping(target = "date", source = "entry.date")
    @Mapping(target = "paid", source = "entry.paid")
    EntryDTO toDTO(Entry entry);
}
