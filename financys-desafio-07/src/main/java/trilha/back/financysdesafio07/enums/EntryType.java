package trilha.back.financysdesafio07.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntryType {
    PAGO("Pago"),
    PENDENTE("Pendente");

    private final String description;
}
