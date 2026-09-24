package co.edu.proyectointegrador.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
@Entity public class Categoria {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Column(nullable=false,unique=true) private String titulo;
 @NotBlank @Column(nullable=false) private String descripcion; private String imagenUrl;
 public Long getId(){return id;} public String getTitulo(){return titulo;} public void setTitulo(String v){titulo=v;}
 public String getDescripcion(){return descripcion;} public void setDescripcion(String v){descripcion=v;}
 public String getImagenUrl(){return imagenUrl;} public void setImagenUrl(String v){imagenUrl=v;}
}
