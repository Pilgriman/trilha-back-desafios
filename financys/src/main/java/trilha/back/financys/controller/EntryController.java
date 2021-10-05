package trilha.back.financys.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import trilha.back.financys.entities.Entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

@RestController
public class EntryController {

    private List<Entry> lista = new ArrayList<Entry>();

    @PostMapping("/lançamentos")
    public int criar(@RequestBody Entry entry){


        lista.add(entry);
        return lista.indexOf(entry);
    }

    @GetMapping("/lançamentos")
    public List<Entry> Ler(){
        listDates(lista);
        return lista;


    }

    //Método para organizar as datas
    public static void listDates(List<Entry> list){

        //há duas formas de realizar a organização das datas
        list.sort(Comparator.comparing(Entry::getDate));

        //list.sort((valor, valor2) -> valor.getDate().compareTo(valor2.getDate()));
    }
}
