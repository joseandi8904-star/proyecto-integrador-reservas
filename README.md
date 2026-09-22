# Proyecto Integrador de Reservas

## Avance 1 — Gestión de productos

Este repositorio contiene únicamente el primer avance del proyecto. Se implementó la historia de usuario **Registrar producto**, junto con las operaciones necesarias para listar, consultar, editar y eliminar productos.

### Tecnologías

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2, base de datos SQL en memoria
- Maven

### Criterios cubiertos

- Registrar productos con nombre y descripción.
- Evitar productos con nombres duplicados.
- Consultar un producto por identificador.
- Listar todos los productos, incluso cuando la lista esté vacía.
- Actualizar y eliminar productos.
- Responder con error cuando un producto no existe.

### Ejecución en IntelliJ

1. Abrir la carpeta como proyecto Maven.
2. Seleccionar JDK 17.
3. Ejecutar `ProyectoIntegradorReservasApplication`.
4. Consumir la API en `http://localhost:8080/api/productos`.

### Ejemplo: crear producto

`POST /api/productos`

```json
{
  "nombre": "Bicicleta urbana",
  "descripcion": "Bicicleta para recorridos urbanos"
}
```

## Próximo avance

Implementar categorías y la asignación de una categoría a cada producto.
