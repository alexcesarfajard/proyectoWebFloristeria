package com.floristeria.floristeria.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;
    private String nombre;
    private String detalle;
    private double precio;
    private int existencias;
    private String rutaImagen;
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

//    public Producto() {
//
//    }
//
//    public Producto(String descripcion, String rutaImagen, boolean activo) {
//        this.detalle = detalle;
//        this.rutaImagen = rutaImagen;
//        this.activo = activo;
//    }
}
