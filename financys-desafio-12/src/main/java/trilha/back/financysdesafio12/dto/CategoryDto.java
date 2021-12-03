package trilha.back.financysdesafio12.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Long id;

    @NotNull(message = "Campo nome não pode ser nulo ou vazio")
    @Size(min = 3, max = 15, message = "min 3 a 15 caracteres")
    private String name;

    @NotNull(message = "Campo de descrição não pode ser nulo ou vazio")
    @Size(min = 15, max = 50, message = "min 15 a 50 caracteres")
    private String description;
}
