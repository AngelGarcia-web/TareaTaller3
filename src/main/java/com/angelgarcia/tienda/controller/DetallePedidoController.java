package com.angelgarcia.tienda.controller;

import com.angelgarcia.tienda.entity.DetallePedido;
import com.angelgarcia.tienda.service.DetallePedidoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/detalle")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("detalles", detallePedidoService.listar());
        return "detalle-pedido";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("detalle", new DetallePedido());
        model.addAttribute("modoEdicion", false);
        return "detalle-pedido-form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("detalle") DetallePedido detalle,
                          BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "detalle-pedido-form";
        }
        detallePedidoService.crear(detalle);
        return "redirect:/detalle";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Integer id, Model model) {
        DetallePedido detalle = detallePedidoService.buscarPorId(id);
        if (detalle != null) {
            model.addAttribute("detalle", detalle);
            model.addAttribute("modoEdicion", true);
            return "detalle-pedido-form";
        }
        return "redirect:/detalle";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id,
                             @Valid @ModelAttribute("detalle") DetallePedido detalle,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "detalle-pedido-form";
        }

        DetallePedido actualizado = detallePedidoService.actualizar(id, detalle);
        if (actualizado != null) {
            return "redirect:/detalle";
        } else {
            model.addAttribute("error", "No se pudo actualizar el detalle");
            model.addAttribute("modoEdicion", true);
            return "detalle-pedido-form";
        }
    }

    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam(name = "idDetalle") Integer id, Model model) {
        DetallePedido detalle = detallePedidoService.buscarPorId(id);

        if (detalle != null) {
            // Se usa "detalleEncontrado" para diferenciarlo de la lista, igual que en Usuario
            model.addAttribute("detalleEncontrado", detalle);
        } else {
            model.addAttribute("error", "Error: Detalle con ID " + id + " no encontrado.");
        }

        // Cargamos la lista completa para mantener la vista consistente
        model.addAttribute("detalles", detallePedidoService.listar());
        return "detalle-pedido";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        detallePedidoService.eliminar(id);
        return "redirect:/detalle";
    }
}