package br.com.room_management.service;

import br.com.room_management.dto.reserva.AtualizarReservaDTO;
import br.com.room_management.dto.reserva.CadastrarReservaDTO;
import br.com.room_management.model.Reserva;
import br.com.room_management.model.Sala;
import br.com.room_management.model.Status;
import br.com.room_management.model.Usuario;
import br.com.room_management.repository.ReservaRepository;
import br.com.room_management.validations.reserva.IValidationAtualizarReserva;
import br.com.room_management.validations.reserva.IValidationCadastrarReserva;
import br.com.room_management.validations.reserva.ValidationReservaAtualizar;
import br.com.room_management.validations.reserva.ValidationReservaCadastro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @InjectMocks
    private ReservaService reservaService;

    @Mock
    private ReservaRepository reservaRepository;

    @Spy
    private List<IValidationCadastrarReserva> validationsCadastrarReserva = new ArrayList<>();

    @Spy
    private List<IValidationAtualizarReserva> validationAtualizarReserva = new ArrayList<>();

    @Mock
    private ValidationReservaCadastro validationCadastro;

    @Mock
    private ValidationReservaAtualizar validationAtualizar;

    @Mock
    private Reserva reserva;

    private CadastrarReservaDTO cadastrarDTO;

    private AtualizarReservaDTO atualizarDTO;

    @Captor
    private ArgumentCaptor<Reserva> reservaCaptor;

    @Test
    @DisplayName("Deveria cadastrar reserva")
    void cadastrarCenario01() {

        // ARRANGE
        List<Usuario> usuariosTest = Arrays.asList(
                new Usuario(), new Usuario()
        );
        Sala salaTest = new Sala();
        salaTest.setAtiva(true);
        salaTest.setCapacidade(10);

        this.cadastrarDTO = new CadastrarReservaDTO(
                usuariosTest,
                salaTest,
                LocalDateTime.now()
        );

        // ACTION
        reservaService.save(cadastrarDTO);

        // ASSERT
        then(reservaRepository).should().save(reservaCaptor.capture());

        Reserva reservaSalvo = reservaCaptor.getValue();
        assertEquals(cadastrarDTO.usuarios(), reservaSalvo.getUsuarios());
        assertEquals(cadastrarDTO.sala(), reservaSalvo.getSala());
        assertEquals(cadastrarDTO.inicio(), reservaSalvo.getInicio());
    }

    @Test
    @DisplayName("Deveria validar as regras de negócio")
    void cadastrarCenario02() {

        // ARRANGE
        List<Usuario> usuariosTest = Arrays.asList(
                new Usuario(), new Usuario()
        );
        Sala salaTest = new Sala();
        salaTest.setAtiva(true);
        salaTest.setCapacidade(10);

        this.cadastrarDTO = new CadastrarReservaDTO(
                usuariosTest,
                salaTest,
                LocalDateTime.now()
        );

        validationsCadastrarReserva.add(validationCadastro);

        // ACTION
        reservaService.save(cadastrarDTO);

        // ASSERT
        then(validationCadastro).should().validation(cadastrarDTO);
    }
}