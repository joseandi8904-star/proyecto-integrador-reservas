package co.edu.integrador.productos.web;

public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(Long id) { super("Producto no encontrado: " + id); }
}
