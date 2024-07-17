package com.floristeria.floristeria.domain;


import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

/*
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
*/

@Data
@Entity
@Table(name="producto")
public class Producto implements Serializable {
    
    private static final long serialVersionUID = 1l;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long idProducto;
    private String nombre;
    private String detalle;
    private double precio;
    private String rutaImagen;
    private boolean activo;
    
    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Categoria categoria;
    
    public Producto() {

    }

    public Producto(String descripcion, String rutaImagen, boolean activo) {
        this.detalle = detalle;
        this.rutaImagen = rutaImagen;
        this.activo = activo;
    }
}
