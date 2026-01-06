package br.com.room_management.validations.usuario;

import br.com.room_management.dto.usuario.CadastrarUsuarioDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationEmailCadastro implements IValidationCadastroUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validation(CadastrarUsuarioDTO dto) {
        var emailExists = usuarioRepository.existsByEmail(dto.email());

        if (emailExists) {
            throw new ErrorValidationException("Esse email já está em uso");
        }
    }
}
