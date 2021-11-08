package trilha.back.financysdesafio09.impl;

import org.springframework.stereotype.Component;
import trilha.back.financysdesafio09.dto.EntryDTO;
import trilha.back.financysdesafio09.exceptions.CalculateException;
import trilha.back.financysdesafio09.service.CalculatorService;

@Component(value = "EntryDTO")
public class EntryCalculateServiceImpl implements CalculatorService<EntryDTO> {
    @Override
    public Integer getEverage(Integer x,Integer y) {
        try {
            int integer = (x / y);
            return Integer.valueOf(integer);
        } catch (ArithmeticException e) {

            throw new CalculateException("Zero cannot divide any number");
        }
    }

}
