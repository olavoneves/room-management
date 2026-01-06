package br.com.room_management.repository;

import br.com.room_management.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

    Boolean existsByPassword(String password);
}
