package fei.relogio_logico_lamport;

import java.util.Random;
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
    private final Random random = new Random();

    @GetMapping("/time")
    public int getTime() {
        return relogio.get();
    }
    @PostMapping("/increment")
    public int incrementRelogio() {
        int tempoPassado = random.nextInt(10) + 1;
        int novoRelogio = relogio.addAndGet(tempoPassado);
        System.out.println("Relógio incrementado por: " + tempoPassado + " - Novo valor: " + novoRelogio);
        return novoRelogio;
    }

    @PostMapping("/message")
    public int receiveMessage(@RequestParam int senderRelogio) {
        int updatedRelogio = Math.max(relogio.get(), senderRelogio) + 1;
        relogio.set(updatedRelogio);
        System.out.println("Mensagem recebida! Relógio corrigido para: " + updatedRelogio);
        return updatedRelogio;
    }
}
