package com.angelgarcia.tienda.service;

import com.angelgarcia.tienda.entity.Producto;
import com.angelgarcia.tienda.exception.ResourceNotFoundException;
import com.angelgarcia.tienda.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    @Override
    public Producto crear(Producto producto) {
        producto.setIdProducto(null);
        return productoRepository.save(producto);
    }

    @Override
    public Producto buscarPorId(Integer id) {
        return productoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto con ID, no encontrado: " + id));
    }

    @Override
    public Producto actualizar(Integer id, Producto producto) {
        Producto productoExistente = buscarPorId(id);
        productoExistente.setNombreProducto(producto.getNombreProducto());
        productoExistente.setPrecioProducto(producto.getPrecioProducto());
        productoExistente.setStockProducto(producto.getStockProducto());
        productoExistente.setIdCategoria(producto.getIdCategoria());
        return productoRepository.save(productoExistente);
    }

    @Override
    public void eliminar(Integer id) {
        if(!productoRepository.existsById(id)){
            throw new ResourceNotFoundException("Producto no encontrado con ID: " + id);
        }

        productoRepository.deleteById(id);
    }
}
