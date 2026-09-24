# Proyecto integrador: catálogo y reservas

API REST desarrollada con Java 17, Spring Boot, JPA y SQL (H2).

## Abrir y ejecutar en IntelliJ

1. Abre esta carpeta como proyecto Maven.
2. Configura un JDK 17 o superior en **File > Project Structure**.
3. Espera a que IntelliJ sincronice Maven.
4. Ejecuta `CatalogoReservasApplication`.
5. La API queda disponible en `http://localhost:8080/api`.

La consola SQL está en `http://localhost:8080/h2-console`. Usa la URL JDBC de `application.properties`.

## Endpoints principales

| Historia | Endpoints |
|---|---|
| Productos | `GET/POST /api/productos`, `GET/PUT/DELETE /api/productos/{id}` |
| Categorías | `GET/POST /api/categorias`, `DELETE /api/categorias/{id}` |
| Características | `GET/POST /api/caracteristicas`, `PUT/DELETE /api/caracteristicas/{id}` |
| Asociaciones | `PUT /api/productos/{productoId}/categoria/{categoriaId}`, `PUT /api/productos/{productoId}/caracteristicas/{caracteristicaId}` |
| Usuarios | `POST /api/usuarios`, `POST /api/auth/login` |
| Reservas | `POST /api/reservas`, `GET /api/productos/{id}/disponibilidad`, `GET /api/productos/disponibles?inicio=2026-10-01&fin=2026-10-05` |

Ejemplo de producto:

```json
{"nombre":"Bicicleta urbana","descripcion":"Bicicleta para recorridos urbanos"}
```

Ejemplo de reserva:

```json
{"productoId":1,"fechaInicio":"2026-10-01","fechaFin":"2026-10-05"}
```

> Nota académica: el inicio de sesión valida credenciales para cumplir la historia de usuario. Antes de publicar una aplicación real, reemplaza el almacenamiento de contraseña en texto plano por hash con BCrypt y autenticación basada en Spring Security.
