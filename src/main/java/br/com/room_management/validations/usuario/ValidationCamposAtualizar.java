package br.com.room_management.validations.usuario;

import br.com.room_management.dto.usuario.AtualizarUsuarioDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ValidationCamposAtualizar implements IValidationAtualizarUsuario {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validation(AtualizarUsuarioDTO dto, UUID id) {
        var usuario = usuarioRepository.getReferenceById(id);

        if (usuario.getName().isEmpty()) {
            throw new ErrorValidationException("Nome não pode ser vazio");
        }

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new ErrorValidationException("Email já está em uso");
        }
    }
}
