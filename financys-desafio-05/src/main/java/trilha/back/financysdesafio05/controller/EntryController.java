package trilha.back.financysdesafio05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import trilha.back.financysdesafio05.entities.Entry;
import trilha.back.financysdesafio05.service.EntryService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EntryController {
    @Autowired
    private EntryService entryService;

    @PostMapping(path = "/lançamentos/adicionar")
    public ResponseEntity<Void> createNewEntry(@Valid @RequestBody Entry entry, UriComponentsBuilder uriComponentsBuilder){
        Long id = entryService.createNewEntry(entry);

        UriComponents uriComponents = uriComponentsBuilder.path("/categorias/{id}").buildAndExpand(id);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

    }

    @GetMapping(path = "/lançamentos/consultar")
    public ResponseEntity<List<Entry>> getAllEntries(){ return ResponseEntity.ok(entryService.getAllEntries());

    }
    @GetMapping("lançamentos/{id}")
    public ResponseEntity<Entry> getEntryById(@PathVariable("id") Long id){
        return ResponseEntity.ok(entryService.getEntryById(id));
    }


    @PutMapping("lançamentos/{id}")
    public ResponseEntity<Entry> updateEntry(@PathVariable("id") Long id, @Valid @RequestBody Entry entryRequestDto){
        return ResponseEntity.ok(entryService.updateEntry(id, entryRequestDto));
    }

    @DeleteMapping("lançamentos/{id}")
    public ResponseEntity<Void>deleteEntryById(@PathVariable("id") Long id){
        entryService.deleteEntryById(id);
        return ResponseEntity.ok().build();
    }
}
