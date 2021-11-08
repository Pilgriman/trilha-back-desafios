package trilha.back.financysdesafio09.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import trilha.back.financysdesafio09.entities.Category;
import trilha.back.financysdesafio09.enums.EntryType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryDTO {

    private Long id;

    @NotNull(message = " Campo nome não pode ser vazio ou nulo")
    @Size(min = 3, max = 45, message = "min 3 a 45 caracteres" )
    private String  name;

    @NotNull(message =  "Campo tipo não pode ser nulo ou vazio")
    @Enumerated(EnumType.STRING)
    private EntryType type;

    @NotNull(message = "Valor montante não pode ser nulo ou vazio")
    @Min(value = 0, message = "Valor mínimo permitido é 0")
    private String amount;

    @NotNull(message = "Campo data não pode ser nulo ou vazio")
    private String date;

    @NotNull(message = "O campo pago não pode ser nulo ou vazio, aceita apenas sim ou não")
    private boolean paid;

    private Category categoryId;
}
