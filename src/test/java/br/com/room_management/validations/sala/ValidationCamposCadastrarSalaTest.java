package br.com.room_management.validations.sala;

import br.com.room_management.dto.sala.CadastrarSalaDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.model.Sala;
import br.com.room_management.model.TipoSala;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationCamposCadastrarSalaTest {

    @InjectMocks
    private ValidationCamposCadastrarSala validationCadastrarSala;

    @Mock
    private CadastrarSalaDTO cadastrarDTO;

    @Test
    @DisplayName("Deveria retornar erro caso sala do hospital exceda a capacidade")
    void validarCenario01() {

        // ARRANGE
        Sala sala = new Sala();
        sala.setCapacidade(50);
        sala.setTipoSala(TipoSala.HOSPITAL);

        when(cadastrarDTO.tipo()).thenReturn(sala.getTipoSala());
        when(cadastrarDTO.capacidade()).thenReturn(sala.getCapacidade());

         // ACT + ASSERT
        assertThrows(ErrorValidationException.class, () -> validationCadastrarSala.validation(cadastrarDTO));
    }

    @Test
    @DisplayName("Deveria retornar erro caso sala de reunião exceda a capacidade")
    void validarCenario02() {

        // ARRANGE
        Sala sala = new Sala();
        sala.setCapacidade(50);
        sala.setTipoSala(TipoSala.MEET);

        when(cadastrarDTO.tipo()).thenReturn(sala.getTipoSala());
        when(cadastrarDTO.capacidade()).thenReturn(sala.getCapacidade());

        // ACT + ASSERT
        assertThrows(ErrorValidationException.class, () -> validationCadastrarSala.validation(cadastrarDTO));
    }

    @Test
    @DisplayName("Deveria retornar erro caso sala do discord exceda a capacidade")
    void validarCenario03() {

        // ARRANGE
        Sala sala = new Sala();
        sala.setCapacidade(50);
        sala.setTipoSala(TipoSala.DISCORD);

        when(cadastrarDTO.tipo()).thenReturn(sala.getTipoSala());
        when(cadastrarDTO.capacidade()).thenReturn(sala.getCapacidade());

        // ACT + ASSERT
        assertThrows(ErrorValidationException.class, () -> validationCadastrarSala.validation(cadastrarDTO));
    }

    @Test
    @DisplayName("Deveria retornar erro caso sala da escola exceda a capacidade")
    void validarCenario04() {

        // ARRANGE
        Sala sala = new Sala();
        sala.setCapacidade(50);
        sala.setTipoSala(TipoSala.ESCOLA);

        when(cadastrarDTO.tipo()).thenReturn(sala.getTipoSala());
        when(cadastrarDTO.capacidade()).thenReturn(sala.getCapacidade());

        // ACT + ASSERT
        assertThrows(ErrorValidationException.class, () -> validationCadastrarSala.validation(cadastrarDTO));
    }

    @Test
    @DisplayName("Deveria retornar erro caso sala da faculdade exceda a capacidade")
    void validarCenario05() {

        // ARRANGE
        Sala sala = new Sala();
        sala.setCapacidade(50);
        sala.setTipoSala(TipoSala.FACULDADE);

        when(cadastrarDTO.tipo()).thenReturn(sala.getTipoSala());
        when(cadastrarDTO.capacidade()).thenReturn(sala.getCapacidade());

        // ACT + ASSERT
        assertThrows(ErrorValidationException.class, () -> validationCadastrarSala.validation(cadastrarDTO));
    }

    @Test
    @DisplayName("Não deveria retornar erro caso capacidade da sala escolhida esteja de acordo")
    void validarCenario06() {

        // ARRANGE
        Sala sala = new Sala();
        sala.setCapacidade(10);
        sala.setTipoSala(TipoSala.HOSPITAL);

        when(cadastrarDTO.tipo()).thenReturn(sala.getTipoSala());
        when(cadastrarDTO.capacidade()).thenReturn(sala.getCapacidade());

        // ACT + ASSERT
        assertDoesNotThrow(() -> validationCadastrarSala.validation(cadastrarDTO));
    }

}