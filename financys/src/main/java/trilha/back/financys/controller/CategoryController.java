package trilha.back.financys.controller;



import org.springframework.web.bind.annotation.*;
import trilha.back.financys.entities.Category;


import java.util.ArrayList;
import java.util.List;


@RestController
public class CategoryController {
    private List<Category> lista = new ArrayList<>();

    @PostMapping("/categorias")
    public int criar(@RequestBody Category category){


        lista.add(category);
        return lista.indexOf(category);
    }

    @GetMapping("/categorias")
    public List<Category>Ler(){
        return lista;
    }

}




