package com.angelgarcia.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pedido")
    private Integer idPedido;

    @NotBlank(message = "La fecha no puede estar vacia")
    @Size(min = 1,message = "La fecha debe de tener al menos 1 caracter")
    @Column(name="fecha_pedido")
    private String fechaPedido;

    @NotNull(message = "El total no puede estar vacio")
    @Min(value = 0,message = "El total del pedido no puede ser negativo")
    @Column(name = "total_pedido")
    private BigDecimal totalPedido;

    @NotNull(message = "El id Usuario no puede estar vacio")
    @Min(value = 0,message = "EL ID no puede ser negativo")
    @Column(name = "id_usuario")
    private Integer idUsuario;


}
