package trilha.back.financys.controller;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trilha.back.financys.category.Category;
import trilha.back.financys.repository.CategoryRepository;


import java.util.List;
import java.util.Optional;


@RestController
public class CategoryController {

    private CategoryRepository categoryRepository;

    @PostMapping("/categorias{id}")
    public Long criar(@RequestBody Category category) {

        return categoryRepository.save(category).getId();
    }

    @GetMapping("/categorias/{id}")
    public List<Category> ler() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findCategoryById(Long id) {
        System.out.println("Categoria identificada");
        return categoryRepository.findById(id);
    }

    @PutMapping("/categorias")
    public ResponseEntity<Category> atualizar(@RequestBody Category category, @PathVariable("id") Long id)
            throws IllegalStateException {
        Category category1 = categoryRepository.findById(id).orElseThrow(()
                -> new IllegalStateException(
                "Id não encontrada " + id
        ));

        category1.setId(category.getId());
        category1.setName(category.getName());
        category1.setDescription(category.getDescription());
        categoryRepository.save(category1);
        return ResponseEntity.ok().body(category1);

    }

    @DeleteMapping("categorias/{id}")
    public void delete(Long id){categoryRepository.deleteById(id);}

}


