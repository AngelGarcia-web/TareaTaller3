package com.angelgarcia.tienda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "usuario")
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Integer idUsuario;

    @NotBlank(message = "El nombre del usuario no puede ir vacio")
    @Size(min = 2, max = 60, message = "El nombre debe tener entre 2 y 60 caracteres.")
    @Column(name="nombre_usuario")
    private String nombreUsuario;

    @NotBlank(message = "El apellido del usuario no puede ir vacio")
    @Size(min = 2, max = 60, message = "El apellido debe tener entre 2 y 60 caracteres.")
    @Column(name="apellido_usuario")
    private String apellidoUsuario;

    @NotNull(message = "La edad no puede ir vacia")
    @Min(value = 1, message = "La edad debe de ser mayor o igual a 1")
    @Max(value = 120, message = "La edad máxima es de 120")
    @Column(name="edad_usuario")
    private Integer edadUsuario;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ingresar un correo válido")
    @Column(unique = true, length = 100)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    private String rol = "ROLE_USER";
}