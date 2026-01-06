package br.com.room_management.dto.reserva;

import br.com.room_management.model.Sala;
import br.com.room_management.model.Status;
import br.com.room_management.model.Usuario;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public record ReservaDTO(@NotNull List<Usuario> usuarios,
                         @NotNull Sala sala,
                         @NotNull LocalDateTime inicio,
                         @NotNull LocalDateTime fim,
                         @NotNull Status status) {
}
