package br.com.room_management.validations.reserva;

import br.com.room_management.dto.reserva.AtualizarReservaDTO;

import java.util.UUID;

public interface IValidationAtualizarReserva {
    void validation(AtualizarReservaDTO dto, UUID id);
}
