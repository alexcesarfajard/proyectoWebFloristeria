package com.floristeria.floristeria.dao;

import com.floristeria.floristeria.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoDAO 
        extends JpaRepository<Producto, Long>{
    
}
