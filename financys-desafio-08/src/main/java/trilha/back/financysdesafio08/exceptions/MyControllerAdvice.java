package trilha.back.financysdesafio08.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(CalculateException.class)
    public ResponseEntity<String> handleCalculateByZero(CalculateException calculateException){
        return new ResponseEntity<String>("Zero cannot divide any number", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException categoryNotFoundException){
        return new ResponseEntity<String>("Category Not Found.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<String> handleEntryNotFoundException(EntryNotFoundException entryNotFoundException){
        return new ResponseEntity<String>("Entry not found",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntryAlreadyRegisteredException.class)
    public ResponseEntity<String> handleEntryAlreadyRegisteredExption( EntryAlreadyRegisteredException entryAlreadyRegisteredException){
        return new ResponseEntity<String>("Entry with id already registered in the system", HttpStatus.BAD_REQUEST);
    }
}
