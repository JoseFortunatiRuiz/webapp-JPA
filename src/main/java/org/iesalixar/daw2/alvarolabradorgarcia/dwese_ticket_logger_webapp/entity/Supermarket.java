package org.iesalixar.daw2.alvarolabradorgarcia.dwese_ticket_logger_webapp.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * La clase `Supermarket` representa una entidad que modela un supermercado dentro de la base de datos.
 * Contiene dos campos: `id` y `name`, donde `id` es el identificador único del supermercado,
 * y `name` es el nombre del supermercado.
 *
 * Las anotaciones de Lombok ayudan a reducir el código repetitivo al generar automáticamente
 * métodos comunes como getters, setters, constructores, y otros métodos estándar de los objetos.
 */
@Data  // Genera automáticamente métodos comunes: getters, setters, equals, hashCode, y toString.

@NoArgsConstructor  // Genera un constructor sin argumentos, útil para frameworks como Hibernate o JPA.

@AllArgsConstructor  // Genera un constructor que acepta todos los campos como parámetros (id, name).
public class Supermarket {

    // Campo que almacena el identificador único del supermercado. Este campo suele ser autogenerado
    // por la base de datos, lo que lo convierte en un buen candidato para una clave primaria.
    // No añadimos validación en el ID porque puede ser nulo al insertarse.
    private Integer id;

    // Campo que almacena el nombre del supermercado.
    @NotEmpty(message = "{msg.supermarket.name.notEmpty}")
    @Size(max = 100, message = "{msg.supermarket.name.size}")
    private String name;

    /**
     * Este es un constructor personalizado que no incluye el campo `id`.
     * Se utiliza para crear instancias de `Supermarket` cuando no es necesario o no se conoce el `id` del supermercado
     * (por ejemplo, antes de insertar el supermercado en la base de datos, donde el `id` es autogenerado).
     * @param name Nombre del supermercado.
     */
    public Supermarket(String name) {
        this.name = name;
    }
}
