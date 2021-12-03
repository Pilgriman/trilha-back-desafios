package trilha.back.financysdesafio12.testes;


import lombok.Builder;
import trilha.back.financysdesafio12.dto.CategoryDto;


@Builder
public class CategoryBuilderDTO {
    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "testeteste1";

    @Builder.Default
    private  String description = "testetestetesteteste";


    public CategoryDto mapCategoryToDto(){ return new CategoryDto(id, name, description);
    }
}
