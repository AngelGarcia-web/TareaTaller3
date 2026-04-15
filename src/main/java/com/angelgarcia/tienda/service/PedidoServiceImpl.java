package com.angelgarcia.tienda.service;

import com.angelgarcia.tienda.entity.Pedido;
import com.angelgarcia.tienda.exception.ResourceNotFoundException;
import com.angelgarcia.tienda.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService{
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido crear(Pedido pedido) {
        pedido.setIdPedido(null);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido buscarPorId(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pedido con ID, no encontrado: " + id));
    }

    @Override
    public Pedido actualizar(Integer id, Pedido pedido) {
        Pedido pedidoExistente = buscarPorId(id);
        pedidoExistente.setFechaPedido(pedido.getFechaPedido());
        pedidoExistente.setTotalPedido(pedido.getTotalPedido());
        pedidoExistente.setIdUsuario(pedido.getIdUsuario());
        return pedidoRepository.save(pedidoExistente);
    }

    @Override
    public void eliminar(Integer id) {
        if(!pedidoRepository.existsById(id)){
            throw new ResourceNotFoundException("Pedido con ID, no encontrado: " + id);
        }

        pedidoRepository.deleteById(id);
    }
}
