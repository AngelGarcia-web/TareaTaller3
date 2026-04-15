package com.angelgarcia.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @NotNull(message = "La cantidad del detalle no puede estar vacia")
    @Min(value = 0, message = "La cantidad debe ser mayor a 0")
    @Column(name = "cantidad")
    private Integer cantidadDetalle;

    @NotNull(message = "El precio del detalle no puede estar vacio")
    @Min(value = -1, message = "El precio no puede ser negativo")
    @Column(name = "precio_unitario")
    private BigDecimal precioDetalle;

    @NotNull(message = "El id del pedido no puede estar vacia")
    @Min(value = 0,message = "El ID del pedido no puede estar en negativo")
    @Column(name = "id_pedido")
    private Integer idPedido;

    @NotNull(message = "El id del producto no puede estar vacia")
    @Min(value = 0, message = "El ID del producto no puede ser negativo")
    @Column(name = "id_producto")
    private Integer idProducto;
}
