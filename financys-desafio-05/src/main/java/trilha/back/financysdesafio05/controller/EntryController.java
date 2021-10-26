package trilha.back.financysdesafio05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trilha.back.financysdesafio05.entryDTO.EntryDTO;
import trilha.back.financysdesafio05.entities.Entry;
import trilha.back.financysdesafio05.exceptions.EntryAlreadyRegisteredException;
import trilha.back.financysdesafio05.service.EntryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/lancamentos")
public class EntryController {
    @Autowired
    private EntryService entryService;

    @PostMapping(path = "/adicionar")
    @ResponseStatus(HttpStatus.CREATED)
    public EntryDTO createEntry(@RequestBody @Valid EntryDTO entryDTO) throws EntryAlreadyRegisteredException {
       return entryService.createEntry(entryDTO);

    }

    @GetMapping(path = "/consultar")
    public ResponseEntity<List<Entry>> getAllEntries(){ return ResponseEntity.ok(entryService.getAllEntries());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Entry> getEntryById(@PathVariable("id") Long id){
        return ResponseEntity.ok(entryService.getEntryById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Entry> updateEntry(@PathVariable("id") Long id, @Valid @RequestBody Entry entryRequestDto){
        return ResponseEntity.ok(entryService.updateEntry(id, entryRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteEntryById(@PathVariable("id") Long id){
        entryService.deleteEntryById(id);
        return ResponseEntity.ok().build();
    }
}