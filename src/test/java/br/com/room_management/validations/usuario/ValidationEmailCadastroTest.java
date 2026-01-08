package br.com.room_management.validations.usuario;

import br.com.room_management.dto.usuario.CadastrarUsuarioDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationEmailCadastroTest {

    @InjectMocks
    private ValidationEmailCadastro validationEmailCadastro;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private CadastrarUsuarioDTO usuarioDTO;

    @Test
    @DisplayName("Deveria retornar exception se email já existir")
    void validarCenario01() {

        // ARRANGE
        String email = "jose@gmail.com";
        when(usuarioDTO.email()).thenReturn(email);
        given(usuarioRepository.existsByEmail(email)).willReturn(true);

        // ACTION + ASSERT
        assertThrows(ErrorValidationException.class, () -> validationEmailCadastro.validation(usuarioDTO));
    }

    @Test
    @DisplayName("Não deveria retornar exception se email não existir")
    void validarCenario02() {

        // ARRANGE
        String email = "jose@gmail.com";
        when(usuarioDTO.email()).thenReturn(email);
        given(usuarioRepository.existsByEmail(email)).willReturn(false);

        // ACTION + ASSERT
        assertDoesNotThrow(() -> validationEmailCadastro.validation(usuarioDTO));
    }

}