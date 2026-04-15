package com.angelgarcia.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Integer idProducto;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Column(name="nombre_producto")
    private String nombreProducto;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Column(name="precio_producto")
    private BigDecimal precioProducto;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(name="stock")
    private Integer stockProducto;

    @NotNull(message = "Debes asignar una categoría")
    @Column(name="id_categoria")
    private Integer idCategoria;
}