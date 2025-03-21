package fei.relogio_logico_lamport;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/clock")
public class RelogioLogicoLampertController {
    
    private final AtomicInteger relogio = new AtomicInteger(0);

    @GetMapping("/time")
    public int getTime() {
        return relogio.get();
    }

    @PostMapping("/increment")
    public int incrementrelogio() {
        return relogio.incrementAndGet();
    }

    @PostMapping("/message")
    public int receiveMessage(@RequestParam int senderrelogio) {
        int updatedrelogio = Math.max(relogio.get(), senderrelogio) + 1;
        relogio.set(updatedrelogio);
        return updatedrelogio;
    }
}
