package br.com.room_management.validations.reserva;

import br.com.room_management.dto.reserva.CadastrarReservaDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.model.Sala;
import br.com.room_management.model.Usuario;
import br.com.room_management.repository.ReservaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationReservaCadastroTest {

    @InjectMocks
    private ValidationReservaCadastro validationReservaCadastro;

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private CadastrarReservaDTO cadastrarDTO;

    @Test
    @DisplayName("Deveria retornar exception se lista de usuarios estiver vazia")
    void validarCenario01() {

        // ARRANGE
        List<Usuario> usuarios = new ArrayList<>();

        Sala sala = new Sala();
        sala.setAtiva(true);

        LocalDateTime inicio = LocalDateTime.now().plusHours(1);

        when(cadastrarDTO.usuarios()).thenReturn(usuarios);

        // ACT + ASSERT
        assertThrows(ErrorValidationException.class, () -> validationReservaCadastro.validation(cadastrarDTO));
    }

    @Test
    @DisplayName("Deveria retornar exception se a sala não estiver ativa")
    void validarCenario02() {

        // ARRANGE
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario1 = new Usuario();
        usuario1.setName("Usuario 1");
        usuarios.add(usuario1);

        Sala sala = new Sala();
        sala.setAtiva(false);

        LocalDateTime inicio = LocalDateTime.now().plusHours(1);

        when(cadastrarDTO.usuarios()).thenReturn(usuarios);
        when(cadastrarDTO.sala()).thenReturn(sala);

        // ACT + ASSERT
        assertThrows(ErrorValidationException.class, () -> validationReservaCadastro.validation(cadastrarDTO));
    }

    @Test
    @DisplayName("Deveria retornar exception se já existe uma reserva para o mesmo horário")
    void validarCenario03() {

        // ARRANGE
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario1 = new Usuario();
        usuario1.setName("Usuario 1");
        usuarios.add(usuario1);

        Sala sala = new Sala();
        sala.setAtiva(true);

        LocalDateTime inicio = LocalDateTime.now().plusHours(1);

        when(cadastrarDTO.usuarios()).thenReturn(usuarios);
        when(cadastrarDTO.sala()).thenReturn(sala);
        when(cadastrarDTO.inicio()).thenReturn(inicio);
        when(reservaRepository.existsByInicio(any())).thenReturn(true);

        // ACT + ASSERT
        assertThrows(ErrorValidationException.class, () -> validationReservaCadastro.validation(cadastrarDTO));
    }

    @Test
    @DisplayName("Não deveria retornar exception caso exista usuarios, sala esteja ativa e horário permitido")
    void validarCenario04() {

        // ARRANGE
        List<Usuario> usuarios = new ArrayList<>();
        Usuario usuario1 = new Usuario();
        usuario1.setName("Usuario 1");
        usuarios.add(usuario1);

        Sala sala = new Sala();
        sala.setAtiva(true);

        LocalDateTime inicio = LocalDateTime.now().plusHours(1);

        when(cadastrarDTO.usuarios()).thenReturn(usuarios);
        when(cadastrarDTO.sala()).thenReturn(sala);
        when(cadastrarDTO.inicio()).thenReturn(inicio);
        when(reservaRepository.existsByInicio(inicio)).thenReturn(false);

        // ACT + ASSERT
        assertDoesNotThrow(() -> validationReservaCadastro.validation(cadastrarDTO));
    }

}