package trilha.back.financysdesafio07.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trilha.back.financysdesafio07.dto.EntryDTO;
import trilha.back.financysdesafio07.exceptions.EntryAlreadyRegisteredException;
import trilha.back.financysdesafio07.service.EntryService;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/lancamentos")
public class EntryController {

    @Autowired
    private final EntryService entryService;


    @PostMapping(path = "/adicionar")
    public ResponseEntity<?> createEntry(@RequestBody @Valid EntryDTO entryDto) throws EntryAlreadyRegisteredException {
        entryService.addEntry(entryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EntryDTO> getCategory(@PathVariable("id") Long id) {
        EntryDTO entryDTO = entryService.getEntryById(id);
        return new ResponseEntity<>(entryDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/consultar")
    public ResponseEntity<List<EntryDTO>> getCategories() {
        return new ResponseEntity<>(entryService.getAllEntries(), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> editEntry(@PathVariable("id") Long id,@RequestBody EntryDTO entryDTO) {
        entryService.updateEntry(id,entryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCategory(@PathVariable("id") Long id) {
        entryService.deleteEntryById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
