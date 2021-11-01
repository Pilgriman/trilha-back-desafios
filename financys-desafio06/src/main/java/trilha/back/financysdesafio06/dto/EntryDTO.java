package trilha.back.financysdesafio06.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import trilha.back.financysdesafio06.entities.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryDTO {
    private Long id;

    private String  name;

    private String type;

    private String amount;

}

