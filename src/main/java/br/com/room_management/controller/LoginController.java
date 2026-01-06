package br.com.room_management.controller;

import br.com.room_management.dto.usuario.LoginUsuarioDTO;
import br.com.room_management.dto.usuario.UsuarioDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import br.com.room_management.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/login")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDTO> login(@RequestBody @Valid LoginUsuarioDTO dto) {
        try {
            usuarioService.login(dto);
            return ResponseEntity.ok(new UsuarioDTO(dto.name(), dto.email()));
        } catch (ErrorValidationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
