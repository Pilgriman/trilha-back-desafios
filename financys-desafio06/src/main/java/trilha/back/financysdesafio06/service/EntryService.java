package trilha.back.financysdesafio06.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trilha.back.financysdesafio06.dto.EntryDTO;
import trilha.back.financysdesafio06.entities.Category;
import trilha.back.financysdesafio06.entities.Entry;
import trilha.back.financysdesafio06.exceptions.EntryAlreadyRegisteredException;
import trilha.back.financysdesafio06.exceptions.EntryNotFoundException;
import trilha.back.financysdesafio06.mapper.CategoryMapper;
import trilha.back.financysdesafio06.mapper.EntryMapper;
import trilha.back.financysdesafio06.repository.CategoryRepository;
import trilha.back.financysdesafio06.repository.EntryRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EntryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final CategoryMapper categoryMapper;

    @Autowired
    private final EntryMapper entryMapper;

    @Autowired
    private final EntryRepository entryRepository;



    public EntryDTO addEntry(EntryDTO entryDTO) throws EntryAlreadyRegisteredException {

        Category category = categoryRepository.findByCategoryName(entryDTO.getName());

        validateEntryById(entryDTO.getId());
        Entry entry = entryMapper.toModel(entryDTO) ;
        entry.setName(entryDTO.getName());
        entry.setType(entryDTO.getType());
        entry.setAmount(entryDTO.getAmount());
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
        entry.setName(entryDTO.getName());
        entry.setType(entryDTO.getType());
        entry.setAmount(entryDTO.getAmount());

        Category category = categoryRepository.findByCategoryName(entryDTO.getName());
        entry.setCategoryId(category);


        entryRepository.save(entry);
        System.out.println("Entry Updated");
    }

    public void deleteEntryById(Long id) {
        entryRepository.deleteById(id);
        System.out.println("Entry deleted");
    }

    public boolean validateEntryById(Long id) throws EntryAlreadyRegisteredException {


        Entry entry = new Entry();
        Optional<Category> validateId = categoryRepository.findById(entry.getCategoryId());
        if (validateId.isPresent()) {
            Optional<Category> categoryId = categoryRepository.findById(entry.getCategoryId());
            if (categoryId.isPresent()) {
                throw new EntryAlreadyRegisteredException(id);
            }
            entryRepository.save(entry);

        } else {

        }
        return false;
    }
}
