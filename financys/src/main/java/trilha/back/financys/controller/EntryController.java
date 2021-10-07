package trilha.back.financys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trilha.back.financys.repository.EntryRepository;
import trilha.back.financys.entry.Entry;

import java.util.*;

@RestController
public class EntryController {

    @Autowired
    private EntryRepository entryRepository;

    @PostMapping("/lançamentos")
    public Long criar(@RequestBody Entry entry) {


        System.out.println("lançamento identificado");
        for (Entry date :
                entryRepository.findAll()) {
            if (date.getId() == entry.getId()) {
                System.out.println(entry.getId() + "Id já localizado no banco");
                return -1L;
            }
        }
        return entryRepository.save(entry).getId();

    }


    @GetMapping("/lançamentos")
    public List<Entry> Ler() {

        entryRepository.findAll().sort(Comparator.comparing(Entry::getDate));
        return entryRepository.findAll();


    }

    @GetMapping("/lançamentos/{id}")
    public Optional<Entry> findEntryById(Long id) {
        entryRepository.findAll().sort(Comparator.comparing(Entry::getDate));
        return entryRepository.findById(id);
    }

    @PutMapping("/lançamentos/{id}")
    public ResponseEntity<Entry> atualizar(@RequestBody Entry entry, @PathVariable("id") Long id)
            throws IllegalStateException {
        Entry entry1 = entryRepository.findById(id).orElseThrow(()
                -> new IllegalStateException(
                "Id não encontrada " + id
        ));

        entry1.setId(entry.getId());
        entry1.setName(entry.getName());
        entry1.setDescription(entry.getDescription());
        entry1.setAmount(entry.getAmount());
        entry1.setType(entry.getType());
        entry1.setDate(entry.getDate());
        entry1.isPaid(entry.getPaid());
        entry1.setCategoryId(entry.getCategoryId());
        entryRepository.save(entry1);
        return ResponseEntity.ok().body(entry1);

    }

    @DeleteMapping("/lançamentos/{id}")
    public void delete(Long id){entryRepository.deleteById(id);}
}
