package trilha.back.financysdesafio09.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trilha.back.financysdesafio09.dto.CategoryDto;
import trilha.back.financysdesafio09.impl.CategoryServiceImpl;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoryController {

    @Autowired
    private final CategoryServiceImpl categoryService;

    @PostMapping(path = "/adicionar")
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        categoryService.save(categoryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long id) {
        CategoryDto categoryDto = categoryService.get(id);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @GetMapping(path = "/consultar")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> editCategory(@PathVariable("id") Long id,@RequestBody CategoryDto categoryDto) {
        categoryService.update(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCategory(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
