package com.example.evento.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EventoRequestDTO(

        @NotBlank(message = "O nome do evento é obrigatório.")
        String nome,

        String descricao,

        @NotBlank(message = "O local do evento é obrigatório.")
        String local,

        @NotNull(message = "A data do evento é obrigatória.")
        LocalDateTime data,

        @NotNull(message = "O valor da entrada é obrigatório.")
        @PositiveOrZero(message = "O valor deve ser maior ou igual a zero.")
        BigDecimal preco

) {}