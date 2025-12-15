package com.vs.alafe.model.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "propietario")
public class Propietario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propietario",nullable = false)
    private Integer idPropietario;

    @Column(name = "telefono",nullable = true)
    private String telefono;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_colonia")
    private Colonia colonia;
    @Column(name = "numero_exterior")
    private String numeroExterior;
    @Column(name = "calle")
    private String calle;
    @Column(name = "fecha_alta")
    private java.sql.Timestamp fechaAlta;
    @Column(name = "correo", columnDefinition = "nvarchar")
    private String correo;

    public Propietario(){}

    public Integer getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Integer idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Colonia getColonia() {
        return colonia;
    }

    public void setColonia(Colonia colonia) {
        this.colonia = colonia;
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

    public java.sql.Timestamp getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(java.sql.Timestamp fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Propietario{" +
                "idPropietario=" + idPropietario +
                ", telefono='" + telefono + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidoPaterno='" + apellidoPaterno + '\'' +
                ", apellidoMaterno='" + apellidoMaterno + '\'' +
                ", numeroExterior='" + numeroExterior + '\'' +
                ", calle='" + calle + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", correo='" + correo + '\'' +
                '}';
    }
}
