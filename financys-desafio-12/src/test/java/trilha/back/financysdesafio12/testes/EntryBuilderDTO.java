package trilha.back.financysdesafio12.testes;






import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import trilha.back.financysdesafio12.dto.EntryDTO;
import trilha.back.financysdesafio12.entities.Category;
import trilha.back.financysdesafio12.repositories.CategoryRepository;

@Builder
public class EntryBuilderDTO {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EntryDTO entryDTO;

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String  name = "testeteste1";

    @Builder.Default
    private String amount ="5.60";

    @Builder.Default
    private String date="17/11/2021" ;

    @Builder.Default
    private boolean paid= false ;

    @Builder.Default
    private Category categoryId ;

    public EntryDTO toDTO(){ return new EntryDTO(id ,name, amount ,date, paid, categoryId);
    }

}

