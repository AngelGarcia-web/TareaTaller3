package com.angelgarcia.tienda.repository;

import com.angelgarcia.tienda.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
