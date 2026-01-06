package br.com.room_management.validations.usuario;

import br.com.room_management.dto.usuario.AtualizarUsuarioDTO;

import java.util.UUID;

public interface IValidationAtualizarUsuario {
    void validation(AtualizarUsuarioDTO dto, UUID id);
}
