package com.angelgarcia.tienda.service;

import com.angelgarcia.tienda.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listar();
    Usuario crear(Usuario usuario);
    Usuario actualizar(Integer id, Usuario usuario);
    Usuario buscarPorId(Integer id);
    void eliminar(Integer id);

    Usuario buscarPorEmail(String email);
}