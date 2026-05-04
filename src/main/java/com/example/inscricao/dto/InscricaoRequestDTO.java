package com.example.inscricao.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record InscricaoRequestDTO(

        @NotNull(message = "O ID do evento é obrigatório.")
        @Positive(message = "O ID do evento deve ser positivo.")
        Long eventoId,

        @NotBlank(message = "O nome do participante é obrigatório.")
        String nomeParticipante,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "Informe um e-mail válido.")
        String email

) {}
