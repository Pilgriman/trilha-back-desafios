package trilha.back.financysdesafio07.service;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trilha.back.financysdesafio07.dto.EntryDTO;
import trilha.back.financysdesafio07.entities.Category;
import trilha.back.financysdesafio07.entities.Entry;
import trilha.back.financysdesafio07.exceptions.EntryAlreadyRegisteredException;
import trilha.back.financysdesafio07.exceptions.EntryNotFoundException;
import trilha.back.financysdesafio07.mapper.CategoryMapper;
import trilha.back.financysdesafio07.mapper.EntryMapper;
import trilha.back.financysdesafio07.repositories.CategoryRepository;
import trilha.back.financysdesafio07.repositories.EntryRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EntryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final CategoryMapper categoryMapper;

    @Autowired
    private final EntryMapper entryMapper;

    @Autowired
    private EntryRepository entryRepository;



    public EntryDTO addEntry(EntryDTO entryDTO) throws EntryAlreadyRegisteredException {

        Category category = categoryRepository.findByCategoryName(entryDTO.getCategoryId().getCategoryName());

        validateEntryById(category.getId());
        Entry entry = entryMapper.toModel(entryDTO) ;
        entry.setCategoryId(category);
        Entry saveEntry = entryRepository.save(entry);
        return entryMapper.toDTO(saveEntry);


    }

    public List<EntryDTO> getAllEntries(){
        System.out.println("Get all entries");
        return entryRepository.findAll()
                .stream()
                .map(entryMapper::toDTO).collect(Collectors.toList());
    }


    public EntryDTO getEntryById(Long id) {
        System.out.println("Get Entries");
        Entry requestedEntry = entryRepository.findById(id).orElseThrow(()->new EntryNotFoundException(String.format("Entry with id: '%s' not found", id)));

        return entryMapper.toDTO(requestedEntry);
    }

    public void updateEntry(Long id, EntryDTO entryDTO) {
        Entry entry = entryRepository.findById(id).orElseThrow(() -> new EntryNotFoundException("Entry not found"));
//        entry.setName(entryDTO.getName());
//        entry.setType(entryDTO.getType());
//        entry.setAmount(entryDTO.getAmount());

        Category category = categoryRepository.findByCategoryName(entryDTO.getName());
        entry.setCategoryId(category);


        entryRepository.save(entry);
        System.out.println("Entry Updated");
    }

    public void deleteEntryById(Long id) {
        entryRepository.deleteById(id);
        System.out.println("Entry deleted");
    }

    public void validateEntryById(Long id) throws EntryAlreadyRegisteredException {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new EntryAlreadyRegisteredException(id));
    }
}


