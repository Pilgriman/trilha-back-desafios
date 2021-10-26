package trilha.back.financysdesafio05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import trilha.back.financysdesafio05.entities.Category;
import trilha.back.financysdesafio05.service.CategoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping(path = "/adicionar")
    public ResponseEntity<Category> createNewCategory(@Valid @RequestBody Category category, UriComponentsBuilder uriComponentsBuilder){
        Long id = categoryService.createNewCategory(category);

        UriComponents uriComponents = uriComponentsBuilder.path("/categorias/adicionar/{id}").buildAndExpand(id);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<Category>(headers, HttpStatus.CREATED);

    }

    @GetMapping(path = "/consultar")
    public ResponseEntity<List<Category>> getAllCategories(){ return ResponseEntity.ok(categoryService.getAllCategories());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @Valid @RequestBody Category categoryRequestDto){
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteCategoryById(@PathVariable("id") Long id){
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok().build();
    }


}
