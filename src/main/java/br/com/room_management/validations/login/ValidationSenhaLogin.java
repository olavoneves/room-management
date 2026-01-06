package br.com.room_management.validations.login;

import br.com.room_management.dto.usuario.LoginUsuarioDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationSenhaLogin implements IValidationLogin {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validation(LoginUsuarioDTO dto) {
        var senhaExists = usuarioRepository.existsByPassword(dto.password());

        if (!senhaExists) {
            throw new ErrorValidationException("Essa senha de usuário não existe");
        }
    }
}
