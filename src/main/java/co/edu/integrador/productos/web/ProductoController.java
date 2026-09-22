package co.edu.integrador.productos.web;

import co.edu.integrador.productos.domain.*;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoRepository productos;
    public ProductoController(ProductoRepository productos) { this.productos = productos; }
    @GetMapping public List<Producto> listar() { return productos.findAll(); }
    @GetMapping("/{id}") public Producto detalle(@PathVariable Long id) { return buscar(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Producto crear(@Valid @RequestBody Producto producto) {
        if (productos.findByNombreIgnoreCase(producto.getNombre()).isPresent()) throw new IllegalArgumentException("Ya existe un producto con ese nombre");
        return productos.save(producto);
    }
    @PutMapping("/{id}")
    public Producto editar(@PathVariable Long id, @Valid @RequestBody Producto cambios) {
        Producto actual = buscar(id);
        productos.findByNombreIgnoreCase(cambios.getNombre()).filter(p -> !p.getId().equals(id)).ifPresent(p -> { throw new IllegalArgumentException("Ya existe un producto con ese nombre"); });
        actual.setNombre(cambios.getNombre()); actual.setDescripcion(cambios.getDescripcion()); return productos.save(actual);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { productos.delete(buscar(id)); }
    private Producto buscar(Long id) { return productos.findById(id).orElseThrow(() -> new ProductoNoEncontradoException(id)); }
}
