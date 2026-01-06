package br.com.room_management.validations.login;

import br.com.room_management.dto.usuario.LoginUsuarioDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationEmailLogin implements IValidationLogin {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validation(LoginUsuarioDTO dto) {
        var emailExists = usuarioRepository.existsByEmail(dto.email());

        if (!emailExists) {
            throw new ErrorValidationException("Esse email de usuário não existe");
        }
    }
}
