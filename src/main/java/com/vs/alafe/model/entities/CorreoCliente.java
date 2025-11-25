package com.vs.alafe.model.entities;

import com.vs.alafe.model.interfaces.ALAFEEntity;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "correo_cliente")
public class CorreoCliente implements ALAFEEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_correo", nullable = false)
    private Integer id_correo;

    @ManyToOne(fetch = FetchType.LAZY) // o LAZY
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "correo",nullable = true)
    private String correo;

    public CorreoCliente() {
    }

    public Integer getId_correo() {
        return id_correo;
    }

    public void setId_correo(Integer id_correo) {
        this.id_correo = id_correo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}

