package trilha.back.financysdesafio12.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntryNullResquest extends RuntimeException {
    public EntryNullResquest (String message) {
        super(message);
    }
}
