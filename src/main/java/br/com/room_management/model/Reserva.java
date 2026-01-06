package br.com.room_management.model;

import br.com.room_management.dto.reserva.AtualizarReservaDTO;
import br.com.room_management.dto.reserva.CadastrarReservaDTO;
import br.com.room_management.infra.exceptions.ErrorValidationException;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "reservas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "reserva_usuario",
            joinColumns = @JoinColumn(name = "reserva_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "sala_id", referencedColumnName = "id")
    private Sala sala;

    private LocalDateTime inicio;
    private LocalDateTime fim;

    @Enumerated(EnumType.STRING)
    private Status status;

    public void reservarSala(CadastrarReservaDTO dto) {
        if (!dto.sala().isAtiva()) {
            throw new ErrorValidationException("A Sala está inativa no momento");
        }

        if (dto.usuarios().isEmpty()) {
            throw new ErrorValidationException("Não existem usuários para utilização da sala");
        }

        if (dto.usuarios().toArray().length > dto.sala().getCapacidade()) {
            throw new ErrorValidationException("Capacidade da sala é inferior a quantidade de usuários");
        }

        this.usuarios = dto.usuarios();
        this.sala = dto.sala();
        this.inicio = dto.inicio();
        this.fim = inicio.plusHours(2);
    }

    public void atualizarReserva(AtualizarReservaDTO dto) {

        if (!dto.sala().isAtiva()) {
            throw new ErrorValidationException("A Sala está inativa no momento");
        }

        if (dto.usuarios().isEmpty()) {
            throw new ErrorValidationException("Não existem usuários para utilização da sala");
        }

        if (dto.usuarios().toArray().length > dto.sala().getCapacidade()) {
            throw new ErrorValidationException("Capacidade da sala é inferior a quantidade de usuários");
        }

        if (dto.status() == Status.CANCELADA) {
            throw new ErrorValidationException("Essa reserva está cancelada");
        }

        if (dto.status() == Status.AGUARDANDO) {
            throw new ErrorValidationException("Aguardando finalização da reserva");
        }

        this.usuarios = dto.usuarios();
        this.sala = dto.sala();
        this.inicio = dto.inicio();
        this.fim = inicio.plusHours(2);
        this.status = dto.status();
    }
}
