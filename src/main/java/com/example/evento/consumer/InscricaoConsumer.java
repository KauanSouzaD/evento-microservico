package com.example.evento.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class InscricaoConsumer {

    @RabbitListener(queues = "email.inscricao.queue")
    public void receberMensagem(String mensagem) {

        System.out.println("Mensagem recebida: " + mensagem);

    }
}