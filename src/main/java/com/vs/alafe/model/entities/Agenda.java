package com.vs.alafe.model.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agenda",nullable = false)
    private Integer idAgenda;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propietario",nullable = false)
    private Propietario propietario;

    @Column(name = "nombre_agenda")
    private String nombreAgenda;
    @Column(name = "descripcion")
    private String descripcion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_colonia", nullable = true)
    private Colonia colonia;
    @Column(name = "numero_exterior")
    private String numeroExterior;
    @Column(name = "calle")
    private String calle;
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public Colonia getColonia() {
        return colonia;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreAgenda() {
        return nombreAgenda;
    }

    public void setNombreAgenda(String nombreAgenda) {
        this.nombreAgenda = nombreAgenda;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public Integer getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Integer idAgenda) {
        this.idAgenda = idAgenda;
    }
}
