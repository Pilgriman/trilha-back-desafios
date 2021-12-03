package trilha.back.financysdesafio12.tarafasAgendadas;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduledTask1 {
    /**
     * Task run every 2 seconds
     */

    @Scheduled(fixedRate = 2000)
    public void startTask1(){

        Date date = new Date();

        System.out.println("[Task #1] Time Now " + date);

    }
}
