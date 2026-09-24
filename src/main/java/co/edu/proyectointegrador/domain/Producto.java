package co.edu.proyectointegrador.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.*;
@Entity public class Producto {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Column(nullable=false,unique=true) private String nombre;
 @NotBlank @Column(nullable=false,length=1000) private String descripcion;
 @ManyToOne private Categoria categoria;
 @ManyToMany private Set<Caracteristica> caracteristicas=new HashSet<>();
 public Long getId(){return id;} public String getNombre(){return nombre;} public void setNombre(String v){nombre=v;}
 public String getDescripcion(){return descripcion;} public void setDescripcion(String v){descripcion=v;}
 public Categoria getCategoria(){return categoria;} public void setCategoria(Categoria v){categoria=v;}
 public Set<Caracteristica> getCaracteristicas(){return caracteristicas;}
}
