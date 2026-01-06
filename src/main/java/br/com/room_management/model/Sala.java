package br.com.room_management.model;

import br.com.room_management.dto.sala.AtualizarSalaDTO;
import br.com.room_management.dto.sala.CadastrarSalaDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "salas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private int capacidade;

    @Enumerated(EnumType.STRING)
    private TipoSala tipoSala;
    private boolean ativa;

    public void cadastrarSala(CadastrarSalaDTO dto) {
        this.capacidade = dto.capacidade();
        this.tipoSala = dto.tipo();
    }

    public void atualizarSala(AtualizarSalaDTO dto) {
        this.capacidade = dto.capacidade();
        this.tipoSala = dto.tipo();
        this.ativa = dto.ativa();
    }
}
