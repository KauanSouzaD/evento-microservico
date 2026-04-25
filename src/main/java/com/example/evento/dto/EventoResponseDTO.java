package com.example.evento.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventoResponseDTO(Long id, String nome, String descricao, String local, LocalDateTime data, BigDecimal preco) {}
