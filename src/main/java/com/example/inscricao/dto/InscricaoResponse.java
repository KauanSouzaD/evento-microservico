package com.example.inscricao.dto;

import java.time.LocalDateTime;

public record InscricaoResponse(
        Long id,
        Long eventoId,
        String nomeEvento,
        String nomeParticipante,
        String email,
        LocalDateTime dataInscricao
) {}