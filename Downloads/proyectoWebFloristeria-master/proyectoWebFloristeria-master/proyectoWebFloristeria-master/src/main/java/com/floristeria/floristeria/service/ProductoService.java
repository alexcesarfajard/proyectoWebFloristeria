package com.floristeria.floristeria.service;

import com.floristeria.floristeria.domain.Producto;
import java.util.List;

public interface ProductoService {
    
    //Se enuncia un metodo que recupera los registros de la tabla producto dentro de un arraylist,
    //pueden ser todos los registros o sólo los activos
    public List<Producto> getProductos(boolean activos);
    
    //Se obtiene un registro de la tabla producto en un objeto Producto si el idProducto existe... si no, 
    //se pasa un null
    public Producto getProducto(Producto producto);
    
    //Se crea un nuevo registro en producto si el objeto Producto NO tiene idProducto (vacio)
    //Se actualiza el registro en la tabla producto si el objeto Producto tiene un idProducto
    public void save(Producto producto);
    
    //Se elimina el registro que tiene idProducto
    public void delete(Producto producto);
}
