package trilha.back.financysdesafio05.entryDTO;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EntryDTO {

    private Long id;

    private String name;

    private  String description;

    private String type;

    private String amount;

    private  String date;

    private  boolean paid;


}
