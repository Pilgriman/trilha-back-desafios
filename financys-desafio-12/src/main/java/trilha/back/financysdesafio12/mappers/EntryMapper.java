package trilha.back.financysdesafio12.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import trilha.back.financysdesafio12.dto.EntryDTO;
import trilha.back.financysdesafio12.entities.Entry;

@Mapper(componentModel = "spring")
public interface EntryMapper {

    @Mapping(target = "id", source = "entryDTO.id")
    @Mapping(target = "name", source = "entryDTO.name")
    @Mapping(target = "amount", source ="entryDTO.amount" )
    @Mapping(target = "date", source ="entryDTO.date")
    @Mapping(target = "paid", source = "entryDTO.paid")
    Entry toModel(EntryDTO entryDTO);

    @Mapping(target = "id", source = "entry.id")
    @Mapping(target = "name", source = "entry.name")
    @Mapping(target = "amount", source ="entry.amount" )
    @Mapping(target = "date", source = "entry.date")
    @Mapping(target = "paid", source = "entry.paid")
    EntryDTO toDTO(Entry entry);

    //EntryDTO toDTO(String date, String amount, boolean paid);
}
