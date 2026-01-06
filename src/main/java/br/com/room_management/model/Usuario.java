package br.com.room_management.model;

import br.com.room_management.dto.usuario.AtualizarUsuarioDTO;
import br.com.room_management.dto.usuario.CadastrarUsuarioDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private String email;
    private String password;

    @ManyToMany(mappedBy = "usuarios")
    private List<Reserva> reservas = new ArrayList<>();

    public void cadastrarUsuario(CadastrarUsuarioDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.password = dto.password();
    }

    public void atualizarUsuario(AtualizarUsuarioDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
    }
}
