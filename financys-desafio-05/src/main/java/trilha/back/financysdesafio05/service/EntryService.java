package trilha.back.financysdesafio05.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trilha.back.financysdesafio05.entities.Category;
import trilha.back.financysdesafio05.entities.Entry;
import trilha.back.financysdesafio05.entryDTO.EntryDTO;
import trilha.back.financysdesafio05.exceptions.EntryAlreadyRegisteredException;
import trilha.back.financysdesafio05.exceptions.EntryNotFoundException;
import trilha.back.financysdesafio05.mapper.EntryMapper;
import trilha.back.financysdesafio05.repository.CategoryRepository;
import trilha.back.financysdesafio05.repository.EntryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class EntryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EntryRepository entryRepository;

    @Autowired
    private EntryMapper entryMapper ;

    public EntryDTO createEntry(EntryDTO entryDTO) throws EntryAlreadyRegisteredException {

        validateEntryById(entryDTO.getId());
        Entry entry = entryMapper.toModel(entryDTO);
        Entry saveEntry = entryRepository.save(entry);
        return entryMapper.toDTO(saveEntry);

    }

    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    public Entry getEntryById(Long id) {
        Entry requestedEntry = entryRepository.findById(id).orElseThrow(()->new EntryNotFoundException(String.format("Entry with id: '%s' not found", id)));

        return requestedEntry;
    }

    @Transactional
    public Entry updateEntry(Long id, Entry entryToUpdateRequest) {
        Entry entryFromDatabase = entryRepository.findById(id).orElseThrow(()->new EntryNotFoundException(String.format("Entry with id: '%s' not found", id)));

        Entry entryToUpdate = entryFromDatabase;


        entryToUpdate.setName(entryToUpdateRequest.getName());
        entryToUpdate.setDescription(entryToUpdateRequest.getDescription());
        entryToUpdate.setType(entryToUpdateRequest.getType());
        entryToUpdate.setAmount(entryToUpdateRequest.getAmount());
        entryToUpdate.setDate(entryToUpdateRequest.getDate());
        entryToUpdate.isPaid(entryToUpdateRequest.getPaid());
        entryToUpdate.setCategoryId(entryToUpdateRequest.getCategoryId());

        entryRepository.save(entryToUpdate);

        return entryToUpdate;
    }

    public void deleteEntryById(Long id) {
        entryRepository.deleteById(id);
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







