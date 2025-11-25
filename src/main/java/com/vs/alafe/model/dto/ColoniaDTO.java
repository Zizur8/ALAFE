package com.vs.alafe.model.dto;

import com.vs.alafe.model.entities.Colonia;

public class ColoniaDTO {

    private Integer idColonia;
    private String colonia;

    public ColoniaDTO(Integer idColonia, String colonia) {
        this.idColonia = idColonia;
        this.colonia = colonia;
    }

    public ColoniaDTO(Colonia colonia){
        this.idColonia = colonia.getIdColonia();
        this.colonia = colonia.getNombre();
    }

    public Integer getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(Integer idColonia) {
        this.idColonia = idColonia;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }
}
