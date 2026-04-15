package com.angelgarcia.tienda.controller;

import com.angelgarcia.tienda.entity.Categoria;
import com.angelgarcia.tienda.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listar(Model model){
        List<Categoria> categorias = categoriaService.listar();
        model.addAttribute("categorias", categorias);
        return "categoria";
    }

    // --- NUEVO MÉTODO PARA BUSCAR ---
    @GetMapping("/buscar")
    public String buscar(@RequestParam(value = "idCategoria", required = false) Integer idCategoria, Model model) {
        if (idCategoria != null) {
            Categoria categoria = categoriaService.buscarPorId(idCategoria);
            if (categoria != null) {
                model.addAttribute("categoriaEncontrada", categoria);
            } else {
                model.addAttribute("error", "No se encontró la categoría con el ID: " + idCategoria);
            }
        }
        // Recargamos la lista para que la tabla general siga visible
        model.addAttribute("categorias", categoriaService.listar());
        return "categoria";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("modoEdicion", false);
        return "categoria-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("categoria") Categoria categoria,
                        BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "categoria-form";
        }
        categoriaService.crear(categoria);
        return "redirect:/categoria";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model){
        Categoria categoria = categoriaService.buscarPorId(id);
        if (categoria != null) {
            model.addAttribute("categoria", categoria);
            model.addAttribute("modoEdicion", true);
            return "categoria-form";
        }
        return "redirect:/categoria";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id,
                             @Valid @ModelAttribute("categoria") Categoria categoria,
                             BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "categoria-form";
        }
        categoriaService.actualizar(id, categoria);
        return "redirect:/categoria";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        categoriaService.eliminar(id);
        return "redirect:/categoria";
    }
}