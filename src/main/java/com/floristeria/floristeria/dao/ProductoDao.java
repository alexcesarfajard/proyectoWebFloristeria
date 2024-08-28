package com.floristeria.floristeria.dao;

import com.floristeria.floristeria.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


    public interface ProductoDao extends JpaRepository<Producto, Long> {
    public List<Producto> findByPrecioBetweenOrderByNombre(double precioInf, double precioSup);
    
    @Query(value = "SELECT a FROM Producto a WHERE a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.nombre ASC")
    public List<Producto> consultaJPQL(double precioInf, double precioSup);
    
    @Query(nativeQuery = true, value = "SELECT * FROM producto a WHERE a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.nombre ASC")
    public List<Producto> consultaSQL(double precioInf, double precioSup);
    
    public List<Producto> findByPrecioBetweenOrderByDetalle(double precioInf, double PrecioSup);
    
}

