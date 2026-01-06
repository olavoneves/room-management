package br.com.room_management.validations.sala;

import br.com.room_management.dto.sala.CadastrarSalaDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidationCamposCadastrarSala implements IValidationCadastrarSala {

    @Override
    public void validation(CadastrarSalaDTO dto) {
        var tipoSala = dto.tipo();

        switch (tipoSala) {
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
