package trilha.back.financysdesafio07.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntryAlreadyRegisteredException extends  Exception{
    public EntryAlreadyRegisteredException(Long entryId){
        super(String.format("Entry with id is %s already registered in the system.", entryId));
    }
}
