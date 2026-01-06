package br.com.room_management.service;

import br.com.room_management.dto.reserva.AtualizarReservaDTO;
import br.com.room_management.dto.reserva.CadastrarReservaDTO;
import br.com.room_management.dto.reserva.ReservaDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.model.Reserva;
import br.com.room_management.repository.ReservaRepository;
import br.com.room_management.validations.reserva.IValidationAtualizarReserva;
import br.com.room_management.validations.reserva.IValidationCadastrarReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private List<IValidationCadastrarReserva> validationsCadastrarReserva;

    @Autowired
    private List<IValidationAtualizarReserva> validationsAtualizarReserva;

    public void save(CadastrarReservaDTO dto) {
        validationsCadastrarReserva.forEach(validation -> validation.validation(dto));

        Reserva reserva = new Reserva();
        reserva.reservarSala(dto);
        reservaRepository.save(reserva);
    }

    public void update(AtualizarReservaDTO dto, UUID id) {
        validationsAtualizarReserva.forEach(validation -> validation.validation(dto, id));

        if (id == null) {
            throw new ErrorValidationException("ID precisa existir");
        }

        Reserva reserva = reservaRepository.getReferenceById(id);
        reserva.atualizarReserva(dto);
        reservaRepository.save(reserva);
    }

    public void delete(UUID id) {
        if (id == null) {
            throw new ErrorValidationException("ID precisa existir");
        }

        reservaRepository.deleteById(id);
    }

    public ReservaDTO findById(UUID id) {
        if (id == null) {
            throw new ErrorValidationException("ID precisa existir");
        }

        var reserva = reservaRepository.getReferenceById(id);
        return new ReservaDTO(reserva.getUsuarios(), reserva.getSala(), reserva.getInicio(), reserva.getFim(), reserva.getStatus());
    }

    public List<ReservaDTO> findAll() {
        List<Reserva> reservas = reservaRepository.findAll();
        List<ReservaDTO> reservasDTO = reservas.stream()
                .map(reserva -> new ReservaDTO(reserva.getUsuarios(), reserva.getSala(), reserva.getInicio(), reserva.getFim(), reserva.getStatus()))
                .toList();

        return reservasDTO;
    }
}
