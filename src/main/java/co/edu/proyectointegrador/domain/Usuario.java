package co.edu.proyectointegrador.domain;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity public class Usuario {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank private String nombre; @NotBlank private String apellido;
 @Email @Column(nullable=false,unique=true) private String email;
 @JsonProperty(access=JsonProperty.Access.WRITE_ONLY) @NotBlank private String contrasena;
 public Long getId(){return id;} public String getNombre(){return nombre;} public void setNombre(String v){nombre=v;}
 public String getApellido(){return apellido;} public void setApellido(String v){apellido=v;} public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getContrasena(){return contrasena;} public void setContrasena(String v){contrasena=v;}
}
