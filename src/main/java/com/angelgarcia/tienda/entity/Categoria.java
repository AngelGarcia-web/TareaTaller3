package com.angelgarcia.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @NotBlank(message = "El nombre de la categoria no puede estar vacia")
    @Column(name = "nombre_categoria")
    private String nombreCategoria;

    @NotBlank(message = "La descripcion de la categoria no puede estar vacia")
    @Column(name = "descripcion_categoria")
    private String descripcionCategoria;

}
