package br.com.room_management.dto.sala;

import br.com.room_management.model.TipoSala;
import jakarta.validation.constraints.NotNull;

public record CadastrarSalaDTO(@NotNull Integer capacidade,
                               @NotNull TipoSala tipo) {
}
