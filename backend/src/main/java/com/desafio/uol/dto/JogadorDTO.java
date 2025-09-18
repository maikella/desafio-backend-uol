package com.desafio.uol.dto;

import jakarta.validation.constraints.NotBlank;

public record JogadorDTO(
                @NotBlank String nome,
                @NotBlank String email,
                String telefone,
                @NotBlank String grupo) {

}
