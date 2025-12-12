package com.vs.alafe.model.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "usuario_rol")
public class UsuarioRol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rol", nullable = false)
    private Short idRol;

    @Column(name = "nombre_rol")
    private String rol;

    public Short getIdRol() {
        return idRol;
    }

    public void setIdRol(Short idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
