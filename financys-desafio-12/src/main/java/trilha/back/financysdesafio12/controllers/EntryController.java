package trilha.back.financysdesafio12.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trilha.back.financysdesafio12.dto.EntryDTO;
import trilha.back.financysdesafio12.exceptions.EntryAlreadyRegisteredException;
import trilha.back.financysdesafio12.exceptions.EntryNullResquest;
import trilha.back.financysdesafio12.exceptions.NoContentException;
import trilha.back.financysdesafio12.services.EntryCalculateService;
import trilha.back.financysdesafio12.services.EntryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/lancamentos")
public class EntryController {

    @Autowired
    private final EntryService entryService;


    @Autowired
    private final EntryCalculateService entryCalculateService;


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
    public ResponseEntity<EntryDTO> editEntry(@PathVariable("id") Long id) {
        EntryDTO entryDTO = entryService.update(id);
        return new ResponseEntity<EntryDTO>(entryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCategory(@PathVariable("id") Long id) {
        entryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/calcula/{x}/{y}")
    public ResponseEntity<?>getAverage(@RequestBody @PathVariable("x") Integer x, @PathVariable("y") Integer y ){
        return new ResponseEntity<>(" The result of the division is " +  entryCalculateService.getEverage(x,y) ,HttpStatus.OK);

    }

    @GetMapping(path = "/filter")
    @ResponseBody
    public ResponseEntity <List<EntryDTO>> getLancamentosDependentes(
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "amount", required = false) String amount,
            @RequestParam(value = "paid", required = false) boolean paid)throws EntryNullResquest, NoContentException {

            return new ResponseEntity<>(entryService.getLancamentosDependentes(date, amount, paid), HttpStatus.OK);
        }
    }


