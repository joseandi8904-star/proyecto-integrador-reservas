package co.edu.proyectointegrador.web;

import co.edu.proyectointegrador.domain.*;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api")
public class CatalogoController {
 private final EntityManager em;
 public CatalogoController(EntityManager em){this.em=em;}
 private <T> T find(Class<T> type, Long id){T x=em.find(type,id); if(x==null) throw new EntityNotFoundException(type.getSimpleName()+" no encontrado: "+id); return x;}

 @GetMapping("/productos") public List<Producto> productos(){return em.createQuery("from Producto",Producto.class).getResultList();}
 @GetMapping("/productos/{id}") public Producto producto(@PathVariable Long id){return find(Producto.class,id);}
 @PostMapping("/productos") @ResponseStatus(HttpStatus.CREATED) @Transactional public Producto crearProducto(@Valid @RequestBody Producto p){
  if(!em.createQuery("from Producto p where lower(p.nombre)=lower(:n)",Producto.class).setParameter("n",p.getNombre()).getResultList().isEmpty()) throw new IllegalArgumentException("Ya existe un producto con ese nombre"); em.persist(p); return p;
 }
 @PutMapping("/productos/{id}") @Transactional public Producto editarProducto(@PathVariable Long id,@Valid @RequestBody Producto cambios){Producto p=find(Producto.class,id); p.setNombre(cambios.getNombre());p.setDescripcion(cambios.getDescripcion());return p;}
 @DeleteMapping("/productos/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) @Transactional public void borrarProducto(@PathVariable Long id){em.remove(find(Producto.class,id));}
 @PutMapping("/productos/{productoId}/categoria/{categoriaId}") @Transactional public Producto asignarCategoria(@PathVariable Long productoId,@PathVariable Long categoriaId){Producto p=find(Producto.class,productoId);p.setCategoria(find(Categoria.class,categoriaId));return p;}
 @PutMapping("/productos/{productoId}/caracteristicas/{caracteristicaId}") @Transactional public Producto asignarCaracteristica(@PathVariable Long productoId,@PathVariable Long caracteristicaId){Producto p=find(Producto.class,productoId);p.getCaracteristicas().add(find(Caracteristica.class,caracteristicaId));return p;}
 @GetMapping("/productos/{id}/caracteristicas") public Set<Caracteristica> caracteristicas(@PathVariable Long id){return find(Producto.class,id).getCaracteristicas();}

 @GetMapping("/categorias") public List<Categoria> categorias(){return em.createQuery("from Categoria",Categoria.class).getResultList();}
 @PostMapping("/categorias") @ResponseStatus(HttpStatus.CREATED) @Transactional public Categoria crearCategoria(@Valid @RequestBody Categoria c){em.persist(c);return c;}
 @DeleteMapping("/categorias/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) @Transactional public void borrarCategoria(@PathVariable Long id){em.remove(find(Categoria.class,id));}
 @GetMapping("/caracteristicas") public List<Caracteristica> listarCaracteristicas(){return em.createQuery("from Caracteristica",Caracteristica.class).getResultList();}
 @PostMapping("/caracteristicas") @ResponseStatus(HttpStatus.CREATED) @Transactional public Caracteristica crearCaracteristica(@Valid @RequestBody Caracteristica c){em.persist(c);return c;}
 @PutMapping("/caracteristicas/{id}") @Transactional public Caracteristica editarCaracteristica(@PathVariable Long id,@Valid @RequestBody Caracteristica c){Caracteristica x=find(Caracteristica.class,id);x.setNombre(c.getNombre());x.setDescripcion(c.getDescripcion());return x;}
 @DeleteMapping("/caracteristicas/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) @Transactional public void borrarCaracteristica(@PathVariable Long id){em.remove(find(Caracteristica.class,id));}

 @PostMapping("/usuarios") @ResponseStatus(HttpStatus.CREATED) @Transactional public Usuario registrar(@Valid @RequestBody Usuario u){em.persist(u);return u;}
 @PostMapping("/auth/login") public Usuario login(@Valid @RequestBody LoginRequest r){List<Usuario> us=em.createQuery("from Usuario u where u.email=:e and u.contrasena=:c",Usuario.class).setParameter("e",r.email()).setParameter("c",r.contrasena()).getResultList();if(us.isEmpty()) throw new IllegalArgumentException("Correo o contraseña incorrectos");return us.get(0);}

 @PostMapping("/reservas") @ResponseStatus(HttpStatus.CREATED) @Transactional public Reserva reservar(@Valid @RequestBody ReservaRequest r){
  if(r.fechaFin().isBefore(r.fechaInicio())) throw new IllegalArgumentException("La fecha final debe ser igual o posterior a la inicial");
  long cruces=em.createQuery("select count(r) from Reserva r where r.producto.id=:p and r.fechaInicio<=:fin and r.fechaFin>=:inicio",Long.class).setParameter("p",r.productoId()).setParameter("inicio",r.fechaInicio()).setParameter("fin",r.fechaFin()).getSingleResult();
  if(cruces>0) throw new IllegalArgumentException("El producto no está disponible en ese rango de fechas"); Reserva nueva=new Reserva();nueva.setProducto(find(Producto.class,r.productoId()));nueva.setFechaInicio(r.fechaInicio());nueva.setFechaFin(r.fechaFin());em.persist(nueva);return nueva;
 }
 @GetMapping("/productos/{id}/disponibilidad") public List<Reserva> ocupadas(@PathVariable Long id){find(Producto.class,id);return em.createQuery("from Reserva r where r.producto.id=:p",Reserva.class).setParameter("p",id).getResultList();}
 @GetMapping("/productos/disponibles") public List<Producto> disponibles(@RequestParam LocalDate inicio,@RequestParam LocalDate fin){if(fin.isBefore(inicio))throw new IllegalArgumentException("Rango de fechas inválido");return em.createQuery("select p from Producto p where not exists (select r from Reserva r where r.producto=p and r.fechaInicio<=:fin and r.fechaFin>=:inicio)",Producto.class).setParameter("inicio",inicio).setParameter("fin",fin).getResultList();}
}
