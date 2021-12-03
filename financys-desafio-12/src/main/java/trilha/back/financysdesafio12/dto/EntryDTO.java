package trilha.back.financysdesafio12.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import trilha.back.financysdesafio12.entities.Category;
import trilha.back.financysdesafio12.enums.EntryType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryDTO {

    private Long id;

    @NotNull(message = " Campo nome não pode ser vazio ou nulo")
    @Size(min = 3, max = 45, message = "min 3 a 45 caracteres" )
    private String  name;

    @NotNull(message = "Valor montante não pode ser nulo ou vazio")
    @Min(value = 0, message = "Valor mínimo permitido é 0")
    private String amount;

    @NotNull(message = "Campo data não pode ser nulo ou vazio")
    private String date;

    @NotNull(message = "O campo pago não pode ser nulo ou vazio, aceita apenas sim ou não")
    private boolean paid;

    private Category categoryId;
}
