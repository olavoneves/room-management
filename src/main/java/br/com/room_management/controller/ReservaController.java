package br.com.room_management.controller;

import br.com.room_management.dto.reserva.AtualizarReservaDTO;
import br.com.room_management.dto.reserva.CadastrarReservaDTO;
import br.com.room_management.dto.reserva.ReservaDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.service.ReservaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid CadastrarReservaDTO dto) {
        try {
            reservaService.save(dto);
            return ResponseEntity.ok("Reserva criada com sucesso");
        } catch (ErrorValidationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody @Valid AtualizarReservaDTO dto, @PathVariable UUID id) {
        try {
            reservaService.update(dto, id);
            return ResponseEntity.ok("Reserva atualizada com sucesso");
        } catch (ErrorValidationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        try {
            reservaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ErrorValidationException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> findById(@PathVariable UUID id) {
        try {
            var reservaDTO = reservaService.findById(id);
            return ResponseEntity.ok(reservaDTO);
        } catch (ErrorValidationException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> findAll() {
        try {
            var reservasDTO = reservaService.findAll();
            return ResponseEntity.ok(reservasDTO);
        } catch (ErrorValidationException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
