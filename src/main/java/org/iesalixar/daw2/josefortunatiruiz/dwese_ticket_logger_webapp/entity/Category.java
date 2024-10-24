package org.iesalixar.daw2.josefortunatiruiz.dwese_ticket_logger_webapp.entity;

import jakarta.persistence.*; // Anotaciones de JPA
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * La clase `Category` representa una entidad que modela una categoría.
 * Contiene tres campos: `id`, `name` y `image`, donde `id` es el identificador único de la categoría,
 * `name` es el nombre de la categoría  `image` es la imagen de categoria.
 */
@Entity // Marca esta clase como una entidad JPA.
@Table(name = "categories") // Especifica el nombre de la tabla asociada a esta entidad.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    // Identificador único de la categoría. Es autogenerado y clave primaria.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID.
    private Integer id;

    // Nombre de la categoría. No puede estar vacío.
    @NotEmpty(message = "{msg.category.name.notEmpty}")
    @Column(name = "name", nullable = false) // Define la columna correspondiente en la tabla.
    private String name;

    // Imagen de la categoría. No puede estar vacío.
    @NotEmpty(message = "{msg.category.image.notEmpty}")
    @Column(name = "image", nullable = false) // Define la columna correspondiente en la tabla.
    private String image;


    /**
     * Constructor que excluye el campo `id`. Se utiliza para crear instancias de `Category`
     * cuando el `id` aún no se ha generado (por ejemplo, antes de insertarlo en la base de datos).
     * @param name Nombre de la categoría.
     * @param image Image de la categoría.
     */
    public Category(String name, String image) {
        this.name = name;
        this.image=image;
    }
}
