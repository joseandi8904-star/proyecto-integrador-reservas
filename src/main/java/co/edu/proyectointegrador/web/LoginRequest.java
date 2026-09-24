package co.edu.proyectointegrador.web;
import jakarta.validation.constraints.*;
public record LoginRequest(@Email @NotBlank String email, @NotBlank String contrasena) {}
