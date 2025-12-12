package com.vs.alafe.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vs.alafe.model.dto.EventoNotaDTO;
import com.vs.alafe.model.interfaces.ALAFEEntity;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "evento_nota")
public class EventoNota implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento_nota", nullable = false)
    private Integer idEventoNota;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento", nullable = false)
    @JsonBackReference
    private Evento evento;
    @Column(name = "nota", nullable = false)
    private String nota;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_ingreso", nullable = false)
    private Usuario usuario;
    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso;

    public EventoNota(){}

    public Integer getIdEventoNota() {
        return idEventoNota;
    }

    public void setIdEventoNota(Integer idEventoNota) {
        this.idEventoNota = idEventoNota;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }


    @Override
    public String toString() {
        return "EventoNota{" +
                "idEventoNota=" + idEventoNota +
                ", evento=" + evento +
                ", nota='" + nota + '\'' +
                ", usuario=" + usuario +
                ", fechaIngreso=" + fechaIngreso +
                '}';
    }
}
