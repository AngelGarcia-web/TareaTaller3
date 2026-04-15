package com.angelgarcia.tienda.repository;

import com.angelgarcia.tienda.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
