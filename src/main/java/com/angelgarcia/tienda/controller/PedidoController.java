package com.angelgarcia.tienda.controller;

import com.angelgarcia.tienda.entity.Pedido;
import com.angelgarcia.tienda.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pedidos", pedidoService.listar());
        return "pedido";
    }


    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("modoEdicion", false);
        return "pedido-form"; // Busca pedido-form.html
    }

    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("pedido") Pedido pedido, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "pedido-form";
        }
        pedidoService.crear(pedido);
        return "redirect:/pedido";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Integer id, Model model) {
        Pedido pedido = pedidoService.buscarPorId(id);
        model.addAttribute("pedido", pedido);
        model.addAttribute("modoEdicion", true);
        return "pedido-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizar(@PathVariable Integer id, @ModelAttribute("pedido") Pedido pedido, Model model) {
        Pedido actualizado = pedidoService.actualizar(id, pedido);
        if (actualizado != null) {
            return "redirect:/pedido";
        } else {
            model.addAttribute("error", "No se pudo actualizar el pedido");
            model.addAttribute("modoEdicion", true);
            return "pedido-form";
        }
    }


    @GetMapping("/buscar")
    public String buscarPorId(@RequestParam(name = "idPedido") Integer id, Model model) {
        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido != null) {
            model.addAttribute("pedidoEncontrado", pedido);
        } else {
            model.addAttribute("error", "Pedido con ID " + id + " no encontrado.");
        }
        model.addAttribute("pedidos", pedidoService.listar());
        return "pedido";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        pedidoService.eliminar(id);
        return "redirect:/pedido";
    }
}