package com.angelgarcia.tienda.service;


import com.angelgarcia.tienda.entity.DetallePedido;
import com.angelgarcia.tienda.exception.ResourceNotFoundException;
import com.angelgarcia.tienda.repository.DetallePedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {
    private final DetallePedidoRepository detallePedidoRepository;

    public DetallePedidoServiceImpl(DetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    @Override
    public List<DetallePedido> listar() {
        return detallePedidoRepository.findAll();
    }

    @Override
    public DetallePedido crear(DetallePedido detallePedido) {
        detallePedido.setIdDetalle(null);
        return detallePedidoRepository.save(detallePedido);
    }

    @Override
    public DetallePedido buscarPorId(Integer id) {
        return detallePedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Detalle con ID, no encontrado: " + id));
    }

    @Override
    public DetallePedido actualizar(Integer id, DetallePedido detallePedido) {
        DetallePedido detalleExistente = buscarPorId(id);
        detalleExistente.setCantidadDetalle(detallePedido.getCantidadDetalle());
        detalleExistente.setPrecioDetalle(detallePedido.getPrecioDetalle());
        detalleExistente.setIdPedido(detallePedido.getIdPedido());
        detalleExistente.setIdProducto(detallePedido.getIdProducto());
        return detallePedidoRepository.save(detalleExistente);
    }

    @Override
    public void eliminar(Integer id) {
        if(!detallePedidoRepository.existsById(id)){
            throw new ResourceNotFoundException("Detalle con ID, no encontrado: " + id);
        }

        detallePedidoRepository.deleteById(id);

    }
}
