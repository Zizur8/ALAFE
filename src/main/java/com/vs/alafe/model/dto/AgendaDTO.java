package com.vs.alafe.model.dto;

import com.vs.alafe.model.entities.Agenda;

import java.time.LocalDateTime;

public class AgendaDTO {

    private Integer idAgenda;
    private String nombreAgenda;
    private String descripcion;
    private String numeroExterior;
    private String calle;
    private LocalDateTime fechaAlta;

    // Constructor que recibe la entidad Agenda
    public AgendaDTO(Agenda agenda) {
        this.idAgenda = agenda.getIdAgenda();
        this.nombreAgenda = agenda.getNombreAgenda();
        this.descripcion = agenda.getDescripcion();
        this.numeroExterior = agenda.getNumeroExterior();
        this.calle = agenda.getCalle();
        this.fechaAlta = agenda.getFechaAlta();
    }

    // Getters y setters
    public Integer getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Integer idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getNombreAgenda() {
        return nombreAgenda;
    }

    public void setNombreAgenda(String nombreAgenda) {
        this.nombreAgenda = nombreAgenda;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }


}
