package com.floristeria.floristeria.service.impl;


import com.floristeria.floristeria.dao.CategoriaDAO;
import com.floristeria.floristeria.domain.Categoria;
import com.floristeria.floristeria.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl 
        implements CategoriaService{

    @Autowired 
    private CategoriaDAO categoriaDAO;
    
    @Override
    public List<Categoria> getCategorias(boolean activos) {
        var lista = categoriaDAO.findAll();
        
        if (activos){ // no leer los inactivos
            //lista.removeIf(c -> !c.isActivo());
        }
        
        return lista;
    }

    @Override
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDAO.findById(categoria.getIdCategoria()).orElse(null);
    }

    @Override
    public void save(Categoria categoria) {
        categoriaDAO.save(categoria);
    }

    @Override
    public void delete(Categoria categoria) {
        categoriaDAO.delete(categoria);
    }
}
