package com.floristeria.floristeria.dao;

import com.floristeria.floristeria.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaDAO 
        extends JpaRepository<Categoria, Long>{
    
}
