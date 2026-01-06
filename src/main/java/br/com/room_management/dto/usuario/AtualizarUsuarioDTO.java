package br.com.room_management.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarUsuarioDTO(@NotBlank String name,
                                  @NotNull @Email String email) {
}
