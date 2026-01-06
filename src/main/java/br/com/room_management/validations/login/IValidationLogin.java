package br.com.room_management.validations.login;

import br.com.room_management.dto.usuario.LoginUsuarioDTO;

public interface IValidationLogin {
    void validation(LoginUsuarioDTO dto);
}
