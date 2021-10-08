package trilha.back.financys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trilha.back.financys.entities.Entry;
import trilha.back.financys.exceptions.EntryNotFoundException;
import trilha.back.financys.repository.EntryRepository;

import java.util.List;
import java.util.Optional;


@Service
public class EntryService {
        @Autowired
        private EntryRepository entryRepository;

        public Long createNewEntry(Entry entry) {
            Entry entry1 = new Entry();

            entry1.setId(entry.getId());
            entry1.setName(entry.getName());
            entry1.setDescription(entry.getDescription());
            entry1.setType(entry.getType());
            entry1.setAmount(entry.getAmount());
            entry1.setDate(entry.getDate());
            entry1.isPaid(entry.getPaid());
            entry1.setCategoryId(entry.getCategoryId());

            entry1 = entryRepository.save(entry1);

            return entry1.getId();
        }

        public List<Entry> getAllEntries() {
            return entryRepository.findAll();
        }

        public Entry getEntryById(Long id) {
            Optional<Entry> requestedEntry = entryRepository.findById(id);
            if (requestedEntry.isEmpty()) {
                throw new EntryNotFoundException(String.format("Entry with id: '%s' not found" , id));

            }

            return requestedEntry.get();
        }

        @Transactional
        public Entry updateEntry(Long id, Entry entryToUpdateRequest){
            Optional<Entry> entryFromDatabase = entryRepository.findById(id);
            if (entryFromDatabase.isEmpty()){
                throw new EntryNotFoundException(String.format("Entry with id: '%s' not found" , id));
            }

            Entry entryToUpdate = entryFromDatabase.get();

            entryToUpdate.setId(entryToUpdateRequest.getId());
            entryToUpdate.setName(entryToUpdateRequest.getName());
            entryToUpdate.setDescription(entryToUpdateRequest.getDescription());
            entryToUpdate.setType(entryToUpdateRequest.getType());
            entryToUpdate.setAmount(entryToUpdateRequest.getAmount());
            entryToUpdate.setDate(entryToUpdateRequest.getDate());
            entryToUpdate.isPaid(entryToUpdateRequest.getPaid());
            entryToUpdate.setCategoryId(entryToUpdateRequest.getCategoryId());


            return entryToUpdate;

        }
    
        public void deleteEntryById(Long id) {
        entryRepository.deleteById(id);
    }
}




