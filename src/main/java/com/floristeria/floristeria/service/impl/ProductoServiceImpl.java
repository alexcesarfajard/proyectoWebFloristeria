package com.floristeria.floristeria.service.impl;


import com.floristeria.floristeria.dao.ProductoDao;
import com.floristeria.floristeria.domain.Producto;
import com.floristeria.floristeria.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService {

    //La anotacion autowired crea un unico objeto sin hacer new.
    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activos) {
        var lista = productoDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }
    
    //Se implementa el método para recuperar los productos con una consulta ampliada
    @Override
    @Transactional(readOnly = true)
    public List<Producto>buscaProductosPorPrecioEntre(double precioInf, double precioSup) {
        return productoDao.findByPrecioBetweenOrderByDetalle(precioInf, precioSup);
    }
    
    //Se implementa el método para recuperar los productos con una consulta JPQL
    @Override
    @Transactional(readOnly = true)
    public List<Producto>consultaJPQL(double precioInf, double precioSup) {
        return productoDao.consultaJPQL(precioInf, precioSup);
    }
    
    //Se implementa el método para recuperar los productos con una consulta SQL
    @Override
    @Transactional(readOnly = true)
    public List<Producto>consultaSQL(double precioInf, double precioSup) {
        return productoDao.consultaSQL(precioInf, precioSup);
    }
    
}
