package com.angelgarcia.tienda.repository;

import com.angelgarcia.tienda.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
