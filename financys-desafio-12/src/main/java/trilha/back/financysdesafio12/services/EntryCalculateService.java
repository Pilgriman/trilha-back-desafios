package trilha.back.financysdesafio12.services;


import org.springframework.stereotype.Service;
import trilha.back.financysdesafio12.exceptions.CalculateException;

@Service
public class EntryCalculateService {
    public Integer getEverage(Integer x,Integer y) {
        try {
            int integer = (x / y);
            return Integer.valueOf(integer);
        } catch (ArithmeticException e) {

            throw new CalculateException("Zero cannot divide any number");
        }
    }

}
