package br.com.room_management.service;

import br.com.room_management.dto.usuario.AtualizarUsuarioDTO;
import br.com.room_management.dto.usuario.CadastrarUsuarioDTO;
import br.com.room_management.dto.usuario.LoginUsuarioDTO;
import br.com.room_management.dto.usuario.UsuarioDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.model.Usuario;
import br.com.room_management.repository.UsuarioRepository;
import br.com.room_management.validations.login.IValidationLogin;
import br.com.room_management.validations.usuario.IValidationAtualizarUsuario;
import br.com.room_management.validations.usuario.IValidationCadastroUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<IValidationLogin> validationsLogin;

    @Autowired
    private List<IValidationCadastroUsuario> validationsCadastroUsuario;

    @Autowired
    private List<IValidationAtualizarUsuario> validationsAtualizarUsuario;

    public void save(CadastrarUsuarioDTO dto) {
        validationsCadastroUsuario.forEach(validation -> validation.validation(dto));

        Usuario usuario = new Usuario();
        usuario.cadastrarUsuario(dto);
        usuarioRepository.save(usuario);
    }

    public void login(LoginUsuarioDTO dto) {
        validationsLogin.forEach(validation -> validation.validation(dto));
    }

    public void update(AtualizarUsuarioDTO dto, UUID id) {
        validationsAtualizarUsuario.forEach(validation -> validation.validation(dto, id));

        if (id == null) {
            throw new ErrorValidationException("ID precisa existir");
        }

        var usuario = usuarioRepository.getReferenceById(id);
        usuario.atualizarUsuario(dto);
        usuarioRepository.save(usuario);
    }

    public void delete(UUID id) {
        if (id == null) {
            throw new ErrorValidationException("ID precisa existir");
        }

        var usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
    }

    public UsuarioDTO findById(UUID id) {
        if (id == null) {
            throw new ErrorValidationException("ID precisa existir");
        }

        var usuario = usuarioRepository.getReferenceById(id);
        return new UsuarioDTO(usuario.getName(), usuario.getEmail());
    }

    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDTO = usuarios.stream()
                .map(usuario -> new UsuarioDTO(usuario.getName(), usuario.getEmail()))
                .toList();

        return usuariosDTO;
    }
}
