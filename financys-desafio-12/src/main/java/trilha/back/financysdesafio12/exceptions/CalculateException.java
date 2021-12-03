package trilha.back.financysdesafio12.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CalculateException extends RuntimeException{

    public CalculateException(String message){super(message);}


}
