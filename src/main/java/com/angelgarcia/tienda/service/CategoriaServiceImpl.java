package com.angelgarcia.tienda.service;

import com.angelgarcia.tienda.entity.Categoria;
import com.angelgarcia.tienda.exception.ResourceNotFoundException;
import com.angelgarcia.tienda.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService{
    private final CategoriaRepository categoriaRepository;


    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria crear(Categoria categoria) {
        categoria.setIdCategoria(null);
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria buscarPorId(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Categoria con ID, no encontrado: " + id));
    }

    @Override
    public Categoria actualizar(Integer id, Categoria categoria) {
        Categoria categoriaExistente = buscarPorId(id);
        categoriaExistente.setNombreCategoria(categoria.getNombreCategoria());
        categoriaExistente.setDescripcionCategoria(categoria.getDescripcionCategoria());
        return categoriaRepository.save(categoriaExistente);
    }


    @Override
    public void eliminar(Integer id) {
        if(!categoriaRepository.existsById(id)){
            throw new ResourceNotFoundException("Categoria con ID, no encontrado: " + id);
        }
        Categoria categoriaExistente = buscarPorId(id);
        categoriaRepository.deleteById(id);
    }
}
