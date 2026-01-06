package br.com.room_management.validations.sala;

import br.com.room_management.dto.sala.AtualizarSalaDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ValidationCamposAtualizarSala implements IValidationAtualizarSala {

    @Autowired
    private SalaRepository salaRepository;

    @Override
    public void validation(AtualizarSalaDTO dto, UUID id) {
        var sala = salaRepository.getReferenceById(id);

        switch (sala.getTipoSala()) {
            case HOSPITAL -> {
                if (dto.capacidade() > 20 || dto.capacidade() <= 0) {
                    throw new ErrorValidationException("Capacidade da sala hospitalar precisa ser menor que 20 usuários");
                }
            }
            case MEET -> {
                if (dto.capacidade() > 30 || dto.capacidade() <= 0) {
                    throw new ErrorValidationException("Capacidade da sala de reunião precisa ser menor que 30 usuários");
                }
            }
            case DISCORD -> {
                if (dto.capacidade() > 40 || dto.capacidade() <= 0) {
                    throw new ErrorValidationException("Capacidade da sala do discord precisa ser menor que 40 usuários");
                }
            }
            case ESCOLA -> {
                if (dto.capacidade() > 33 || dto.capacidade() <= 0) {
                    throw new ErrorValidationException("Capacidade da sala escolar precisa ser menor que 33 usuários");
                }
            }
            case FACULDADE -> {
                if (dto.capacidade() > 40 || dto.capacidade() <= 0) {
                    throw new ErrorValidationException("Capacidade da sala da faculdade precisa ser menor que 40 usuários");
                }
            }
        }
    }
}
