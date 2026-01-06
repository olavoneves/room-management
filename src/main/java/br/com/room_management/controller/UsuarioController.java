package br.com.room_management.controller;

import br.com.room_management.dto.usuario.AtualizarUsuarioDTO;
import br.com.room_management.dto.usuario.CadastrarUsuarioDTO;
import br.com.room_management.dto.usuario.UsuarioDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> save(@RequestBody @Valid CadastrarUsuarioDTO dto) {
        try {
            usuarioService.save(dto);
            return ResponseEntity.ok("Usuário criado com sucesso");
        } catch (ErrorValidationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody @Valid AtualizarUsuarioDTO dto, @PathVariable UUID id) {
        try {
            usuarioService.update(dto, id);
            return ResponseEntity.ok("Usuário atualizado com sucesso");
        } catch (ErrorValidationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        try {
            usuarioService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ErrorValidationException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable UUID id) {
        try {
            var usuarioDTO = usuarioService.findById(id);
            return ResponseEntity.ok(usuarioDTO);
        } catch (ErrorValidationException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        try {
            var usuariosDTO = usuarioService.findAll();
            return ResponseEntity.ok(usuariosDTO);
        } catch (ErrorValidationException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
