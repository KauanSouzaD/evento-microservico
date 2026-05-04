package com.example.inscricao.dto;

import java.time.LocalDateTime;

public record InscricaoResponseDTO(
        Long id,
        Long eventoId,
        String nomeEvento,
        String nomeParticipante,
        String email,
        LocalDateTime dataInscricao
) {}
