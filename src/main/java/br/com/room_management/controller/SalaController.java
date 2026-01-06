package br.com.room_management.controller;

import br.com.room_management.dto.sala.AtualizarSalaDTO;
import br.com.room_management.dto.sala.CadastrarSalaDTO;
import br.com.room_management.dto.sala.SalaDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.service.SalaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sala")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid CadastrarSalaDTO dto) {
        try {
            salaService.save(dto);
            return ResponseEntity.ok("Sala criada com sucesso");
        } catch (ErrorValidationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody @Valid AtualizarSalaDTO dto, @PathVariable UUID id) {
        try {
            salaService.update(dto, id);
            return ResponseEntity.ok("Sala atualizada com sucesso");
        } catch (ErrorValidationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        try {
            salaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ErrorValidationException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaDTO> findById(@PathVariable UUID id) {
        try {
            var salaDTO = salaService.findById(id);
            return ResponseEntity.ok(salaDTO);
        } catch (ErrorValidationException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<SalaDTO>> findAll() {
        try {
            var salasDTO = salaService.findAll();
            return ResponseEntity.ok(salasDTO);
        } catch (ErrorValidationException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
