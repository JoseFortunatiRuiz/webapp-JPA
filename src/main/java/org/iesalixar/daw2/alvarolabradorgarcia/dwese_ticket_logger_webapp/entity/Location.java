package org.iesalixar.daw2.alvarolabradorgarcia.dwese_ticket_logger_webapp.entity;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * La clase `Location` representa una entidad que modela una ubicación dentro de la base de datos.
 * Contiene cuatro campos: `id`, `address` , `city` , `ID_Province`  y 'ID_Supermarket' , donde `id` es el identificador único de la ubicación,
 * `addres` es la dirección de la ubicación, `city` es el nombre de la ciudad en la que se encuentra la ubicación, ID_Province es el id de la provincia en la que se
 * encuentra la ubicación y ID_Supermarket es el id del supermercado que se encuentra en la ubicación.
 *
 * Las anotaciones de Lombok ayudan a reducir el código repetitivo al generar automáticamente
 * métodos comunes como getters, setters, constructores, y otros métodos estándar de los objetos.
 */
@Data  // Esta anotación de Lombok genera automáticamente los siguientes métodos:
// - Getters y setters para todos los campos (id, code, name).
// - Los métodos `equals()` y `hashCode()` basados en todos los campos no transitorios.
// - El método `toString()` que incluye todos los campos.
// - Un método `canEqual()` que verifica si una instancia puede ser igual a otra.
// Esto evita tener que escribir manualmente todos estos métodos y mejora la mantenibilidad del código.


@NoArgsConstructor  // Esta anotación genera un constructor sin argumentos (constructor vacío),
//  es útil cuando quieres crear un objeto `Location` sin inicializarlo inmediatamente
// con valores. Esto es muy útil en frameworks como Hibernate o JPA,
// que requieren un constructor vacío para la creación de entidades.


@AllArgsConstructor  // Esta anotación genera un constructor que acepta todos los campos como parámetros.
// Este constructor es útil cuando necesitas crear una instancia completamente inicializada de `Location`.


public class Location {


    // Campo que almacena el identificador único de la ubicación. Este campo suele ser autogenerado
    // por la base de datos, lo que lo convierte en un buen candidato para una clave primaria.
    // No añadimos validación en el ID porque en este caso puede ser nulo al insertarse
    @NotNull
    private Integer id;


    // Campo que almacena la dirección de la ubicación
    // Ejemplo: "Pagés del Corro nº 8" para Andalucía.
    @NotEmpty(message = "{msg.location.address.notEmpty}")
    @Size(max = 255, message = "{msg.location.address.size}")
    private String address;


    // Campo que almacena la ciudad de la ubicación, como "Sevilla" o "Barcelona".
    @NotEmpty(message = "{msg.location.city.notEmpty}")
    @Size(max = 100, message = "{msg.location.city.notEmpty}")
    private String city;

    // Campo que agrega las comunidades autónomas
    @NotNull(message = "{msg.location.province.notEmpty}")
    private Province province;

    // Campo que agrega las comunidades autónomas
    @NotNull(message = "{msg.location.supermarket.notEmpty}")
    private Supermarket supermarket;

    /**
     * Este es un constructor personalizado que no incluye el campo `id`.
     * Se utiliza para crear instancias de `Location` cuando no es necesario o no se conoce el `id` de la ubicación
     * (por ejemplo, antes de insertar la ubicación en la base de datos, donde el `id` es autogenerado).
     * @param address dirección de la ubicación.
     * @param city ciudad de la ubicación.
     */
    public Location(String address, String city, Province province) {
        this.address = address;
        this.city = city;
        this.province = province;
    }
}
