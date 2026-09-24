package co.edu.proyectointegrador.domain;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity public class Reserva {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) private Producto producto; @Column(nullable=false) private LocalDate fechaInicio; @Column(nullable=false) private LocalDate fechaFin;
 public Long getId(){return id;} public Producto getProducto(){return producto;} public void setProducto(Producto v){producto=v;}
 public LocalDate getFechaInicio(){return fechaInicio;} public void setFechaInicio(LocalDate v){fechaInicio=v;}
 public LocalDate getFechaFin(){return fechaFin;} public void setFechaFin(LocalDate v){fechaFin=v;}
}
