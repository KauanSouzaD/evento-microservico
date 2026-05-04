package com.example.inscricao.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventoResponse(
        Long id,
        String nome,
        String descricao,
        String local,
        LocalDateTime data,
        BigDecimal preco,
        boolean gratuito
) {}