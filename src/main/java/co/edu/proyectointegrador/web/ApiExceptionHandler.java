package co.edu.proyectointegrador.web;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestControllerAdvice public class ApiExceptionHandler {
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<Map<String,String>> invalid(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(jakarta.persistence.EntityNotFoundException.class) ResponseEntity<Map<String,String>> missing(Exception e){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error",e.getMessage()));}
}
