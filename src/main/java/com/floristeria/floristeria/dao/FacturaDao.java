package com.floristeria.floristeria.dao;

import com.floristeria.floristeria.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaDao extends JpaRepository<Factura, Long> {

}
