package br.com.room_management.service;

import br.com.room_management.dto.sala.AtualizarSalaDTO;
import br.com.room_management.dto.sala.CadastrarSalaDTO;
import br.com.room_management.dto.sala.SalaDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.model.Sala;
import br.com.room_management.repository.SalaRepository;
import br.com.room_management.validations.sala.IValidationAtualizarSala;
import br.com.room_management.validations.sala.IValidationCadastrarSala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private List<IValidationCadastrarSala> validationsCadastrarSala;

    @Autowired
    private List<IValidationAtualizarSala> validationsAtualizarSala;

    public void save(CadastrarSalaDTO dto) {
        validationsCadastrarSala.forEach(validation -> validation.validation(dto));

        Sala sala = new Sala();
        sala.cadastrarSala(dto);
        salaRepository.save(sala);
    }

    public void update(AtualizarSalaDTO dto, UUID id) {
        validationsAtualizarSala.forEach(validation -> validation.validation(dto, id));

        if (id == null) {
            throw new ErrorValidationException("ID precisa existir");
        }

        var sala = salaRepository.getReferenceById(id);
        sala.atualizarSala(dto);
        salaRepository.save(sala);
    }

    public void delete(UUID id) {
        if (id == null) {
            throw new ErrorValidationException("ID precisa existir");
        }

        salaRepository.deleteById(id);
    }

    public SalaDTO findById(UUID id) {
        if (id == null) {
            throw new ErrorValidationException("ID precisa existir");
        }

        Sala sala = salaRepository.getReferenceById(id);
        return new SalaDTO(sala.getCapacidade(), sala.getTipoSala());
    }

    public List<SalaDTO> findAll() {
        List<Sala> salas = salaRepository.findAll();
        List<SalaDTO> salasDTO = salas.stream()
                .map(sala -> new SalaDTO(sala.getCapacidade(), sala.getTipoSala()))
                .toList();

        return salasDTO;
    }
}
