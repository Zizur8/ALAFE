package com.vs.alafe.model.dto;

import com.vs.alafe.model.entities.Propietario;

public class PropietarioDTO {

    private Integer idPropietario;
    private String nombre;


    public PropietarioDTO(Integer idPropietario, String nombre) {
        this.idPropietario = idPropietario;
        this.nombre = nombre;
    }

    public PropietarioDTO(Propietario propietario) {
        this.idPropietario = propietario.getIdPropietario();
        this.nombre = propietario.getNombre();
    }

    public Integer getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Integer idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
