package br.com.room_management.validations.reserva;

import br.com.room_management.dto.reserva.CadastrarReservaDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidationReservaCadastro implements IValidationCadastrarReserva {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public void validation(CadastrarReservaDTO dto) {

        if (dto.usuarios().isEmpty()) {
            throw new ErrorValidationException("Para reservar é obrigatório adicionar usuários");
        }

        if (!dto.sala().isAtiva()) {
            throw new ErrorValidationException("Para reservar é obrigatório ter uma sala ativa");
        }

        if (reservaRepository.existsByInicio(dto.inicio())) {
            throw new ErrorValidationException("Uma sala já está reservada para esse horário");
        }
    }
}
