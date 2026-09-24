package co.edu.proyectointegrador.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
@Entity public class Caracteristica {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Column(nullable=false,unique=true) private String nombre;
 @NotBlank @Column(nullable=false) private String descripcion;
 public Long getId(){return id;} public String getNombre(){return nombre;} public void setNombre(String v){nombre=v;}
 public String getDescripcion(){return descripcion;} public void setDescripcion(String v){descripcion=v;}
}
