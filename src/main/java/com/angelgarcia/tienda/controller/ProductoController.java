package com.angelgarcia.tienda.controller;

import com.angelgarcia.tienda.entity.Producto;
import com.angelgarcia.tienda.entity.Usuario;
import com.angelgarcia.tienda.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@Controller
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoService.listar());
        return "producto";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("modoEdicion", false);
        return "producto-form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        Producto producto = productoService.buscarPorId(id);
        model.addAttribute("producto", producto);
        model.addAttribute("modoEdicion", true);
        return "producto-form";
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("producto") Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "producto-form";
        }

        productoService.crear(producto);
        return "redirect:/producto";
    }
    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute("producto") Producto producto, Model model) {
        Producto actualizado = productoService.actualizar(id, producto);
        if (actualizado != null) {
            return "redirect:/producto";
        } else {
            model.addAttribute("error", "no se pudo actualizar el producto");
            return "producto-form";
        }
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam(name = "idProducto") Integer id, Model model) {
        Producto producto = productoService.buscarPorId(id);
        if (producto != null) {
            model.addAttribute("productoEncontrado", producto);
        } else {
            model.addAttribute("error", "Error producto no encontrado: ");
        }
        return "producto";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
        return "redirect:/producto";
    }
}