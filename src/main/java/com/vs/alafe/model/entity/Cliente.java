package com.vs.alafe.model.entity;

import com.vs.alafe.model.entity.interfaces.ALAFEEntity;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "cliente")
public class Cliente implements ALAFEEntity,Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "telefono")
    private String telefono;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_colonia", nullable = false, insertable = false, updatable = false)
    private Colonia colonia;
    @Column(name = "numero_exterior")
    private String numeroExterior;
    @Column(name = "calle")
    private String calle;
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;
    @OneToOne(mappedBy = "cliente", fetch = FetchType.LAZY)
    private CorreoCliente correo;

    public Cliente() {
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
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

    public String getApellidoMaterno() {return apellidoMaterno;}

    public void setApellidoMaterno(String apellidoMaterno) {this.apellidoMaterno = apellidoMaterno;}
    public Colonia getColonia() {
        return colonia;
    }

    public void setColonia(Colonia idColonia) {
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

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public CorreoCliente getCorreo() {return correo;}

    public void setCorreo(CorreoCliente correo) {this.correo = correo;}
}
