package trilha.back.financysdesafio09.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trilha.back.financysdesafio09.dto.EntryDTO;
import trilha.back.financysdesafio09.entities.Category;
import trilha.back.financysdesafio09.entities.Entry;
import trilha.back.financysdesafio09.exceptions.EntryAlreadyRegisteredException;
import trilha.back.financysdesafio09.exceptions.EntryNotFoundException;
import trilha.back.financysdesafio09.mapper.CategoryMapper;
import trilha.back.financysdesafio09.mapper.EntryMapper;
import trilha.back.financysdesafio09.repositories.CategoryRepository;
import trilha.back.financysdesafio09.repositories.EntryRepository;
import trilha.back.financysdesafio09.service.CalculatorService;
import trilha.back.financysdesafio09.service.EntryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private EntryMapper entryMapper;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private CalculatorService calculator;

    @Override
    public EntryDTO save(EntryDTO entryDTO) throws EntryAlreadyRegisteredException {

        Category category = categoryRepository.findByCategoryName(entryDTO.getCategoryId().getCategoryName());


        Entry entry = entryMapper.toModel(entryDTO) ;
        entry.setCategoryId(category);
        Entry saveEntry = entryRepository.save(entry);
        return entryMapper.toDTO(saveEntry);


    }

    @Override
    public List<EntryDTO> getAll(){
        System.out.println("Get all entries");
        return entryRepository.findAll()
                .stream()
                .map(entryMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public EntryDTO get(Long id) {
        System.out.println("Get Entries");
        Entry requestedEntry = entryRepository.findById(id).orElseThrow(()->new EntryNotFoundException(String.format("Entry with id: '%s' not found", id)));

        return entryMapper.toDTO(requestedEntry);
    }

    @Override
    public void update(Long id) {

        EntryDTO entryDTO = new EntryDTO();
        Entry entry = entryRepository.findById(id).orElseThrow(() -> new EntryNotFoundException("Entry not found"));
//        entry.setName(entryDTO.getName());
//        entry.setType(entryDTO.getType());
//        entry.setAmount(entryDTO.getAmount());

        Category category = categoryRepository.findByCategoryName(entryDTO.getName());
        entry.setCategoryId(category);


        entryRepository.save(entry);
        System.out.println("Entry Updated");
    }

    @Override
    public void delete(Long id) {
        entryRepository.deleteById(id);
        System.out.println("Entry deleted");
    }

}
