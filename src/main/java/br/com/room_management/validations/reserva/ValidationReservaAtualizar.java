package br.com.room_management.validations.reserva;

import br.com.room_management.dto.reserva.AtualizarReservaDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ValidationReservaAtualizar implements IValidationAtualizarReserva {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public void validation(AtualizarReservaDTO dto, UUID id) {

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
