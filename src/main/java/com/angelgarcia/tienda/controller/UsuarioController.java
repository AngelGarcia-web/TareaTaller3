package com.angelgarcia.tienda.controller;

import com.angelgarcia.tienda.entity.Usuario;
import com.angelgarcia.tienda.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(Model model) {
        // CORRECCIÓN: Se usa "usuarios" en plural para que el HTML lo reconozca
        model.addAttribute("usuarios", usuarioService.listar());
        return "usuario";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("modoEdicion", false);
        return "usuario-form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("modoEdicion", true);
        return "usuario-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Aseguramos que el formulario sepa que es modo creación si hay errores
            model.addAttribute("modoEdicion", false);
            return "usuario-form";
        }
        usuarioService.crear(usuario);
        return "redirect:/usuario";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute("usuario") Usuario usuario, Model model) {
        Usuario actualizado = usuarioService.actualizar(id, usuario);
        if (actualizado != null) {
            return "redirect:/usuario";
        } else {
            model.addAttribute("error", "No se pudo actualizar el usuario");
            model.addAttribute("modoEdicion", true);
            return "usuario-form";
        }
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam(name = "idUsuario") Integer id, Model model) {
        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario != null) {
            model.addAttribute("usuarioEncontrado", usuario);
        } else {
            model.addAttribute("error", "Error: Usuario con ID " + id + " no encontrado.");
        }
        // IMPORTANTE: Al buscar, también debemos cargar la lista completa abajo
        model.addAttribute("usuarios", usuarioService.listar());
        return "usuario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        usuarioService.eliminar(id);
        return "redirect:/usuario";
    }
}