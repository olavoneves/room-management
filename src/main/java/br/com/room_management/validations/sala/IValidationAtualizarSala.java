package br.com.room_management.validations.sala;

import br.com.room_management.dto.sala.AtualizarSalaDTO;

import java.util.UUID;

public interface IValidationAtualizarSala {
    void validation(AtualizarSalaDTO dto, UUID id);
}
