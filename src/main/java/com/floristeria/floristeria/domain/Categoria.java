package com.floristeria.floristeria.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long idCategoria;

    private String nombre;

    @Column(name = "descripcion")  // Renombramos 'detalle' a 'descripcion'
    private String descripcion;

    @Column(name = "ruta_imagen")
    private String rutaImagen;

    private boolean activo;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Producto> productos;

    // Método getter para la descripción
    public String getDescripcion() {
        return descripcion;
    }

    // Método setter para la descripción
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
