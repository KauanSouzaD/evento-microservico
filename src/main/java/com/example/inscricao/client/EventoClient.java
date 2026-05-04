package com.example.inscricao.client;

import com.example.inscricao.client.dto.EventoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "evento-service", url = "${evento.service.url}")
public interface EventoClient {

    @GetMapping("/api/v1/eventos/{id}")
    EventoResponseDTO buscarPorId(@PathVariable Long id);
}
