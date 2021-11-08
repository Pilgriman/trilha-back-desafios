package trilha.back.financysdesafio09.controllers;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trilha.back.financysdesafio09.dto.EntryDTO;
import trilha.back.financysdesafio09.exceptions.EntryAlreadyRegisteredException;
import trilha.back.financysdesafio09.impl.EntryCalculateServiceImpl;
import trilha.back.financysdesafio09.impl.EntryServiceImpl;


import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/lancamentos")
public class EntryController {

    @Autowired
    private final EntryServiceImpl entryService;

    private final EntryCalculateServiceImpl entryCalculateService;


    @PostMapping(path = "/adicionar")
    public ResponseEntity<?> createEntry(@RequestBody @Valid EntryDTO entryDto) throws EntryAlreadyRegisteredException {
        entryService.save(entryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EntryDTO> getCategory(@PathVariable("id") Long id) {
        EntryDTO entryDTO = entryService.get(id);
        return new ResponseEntity<>(entryDTO, HttpStatus.OK);
    }

    @GetMapping(path = "/consultar")
    public ResponseEntity<List<EntryDTO>> getCategories() {
        return new ResponseEntity<>(entryService.getAll(), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> editEntry(@PathVariable("id") Long id,@RequestBody EntryDTO entryDTO) {
        entryService.update(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCategory(@PathVariable("id") Long id) {
        entryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/calcula/{x}/{y}")
    public ResponseEntity<?>getAverage(@RequestBody @PathVariable("x") Integer x, @PathVariable("y") Integer y ){
        return new ResponseEntity<>(" The result of the division is " +  entryCalculateService.getEverage(x,y) ,HttpStatus.OK);

    }

}
