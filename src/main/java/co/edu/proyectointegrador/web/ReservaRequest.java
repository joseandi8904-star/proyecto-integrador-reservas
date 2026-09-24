package co.edu.proyectointegrador.web;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
public record ReservaRequest(@NotNull Long productoId, @NotNull LocalDate fechaInicio, @NotNull LocalDate fechaFin) {}
