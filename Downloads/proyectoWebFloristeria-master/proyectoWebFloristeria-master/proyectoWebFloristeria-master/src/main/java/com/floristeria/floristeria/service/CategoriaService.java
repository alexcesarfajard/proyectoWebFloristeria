package com.floristeria.floristeria.service;


import com.floristeria.floristeria.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    
    //Se enuncia un metodo que recupera los registros de la tabla categoria dentro de un arraylist,
    //pueden ser todos los registros o sólo los activos
    public List<Categoria> getCategorias(boolean activos);
    
    //Se obtiene un registro de la tabla categoria en un objeto Categoria si el idCategoria existe... si no, 
    //se pasa un null
    public Categoria getCategoria(Categoria categoria);
    
    //Se crea un nuevo registro en categoria si el objeto Categoria NO tiene idCategoria (vacio)
    //Se actualiza el registro en la tabla categoria si el objeto Categoria tiene un idCategoria
    public void save(Categoria categoria);
    
    //Se elimina el registro que tiene idCategoria
    public void delete(Categoria categoria);
}
