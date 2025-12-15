package com.vs.alafe.model.dto;

import com.vs.alafe.model.entities.EventoNota;
import java.time.LocalDateTime;

public class EventoNotaDTO {

    private Integer idEventoNota;
    private Integer idEvento;
    private String nota;
    private Integer idUsuarioIngreso;
    private String nombreUsuarioIngreso;
    private LocalDateTime fechaIngreso;

    public EventoNotaDTO(){}

    public EventoNotaDTO(Integer idEventoNota, Integer idEvento, String nota, Integer idUsuarioIngreso, String nombreUsuarioIngreso, LocalDateTime fechaIngreso) {
        this.idEventoNota = idEventoNota;
        this.idEvento = idEvento;
        this.nota = nota;
        this.idUsuarioIngreso = idUsuarioIngreso;
        this.nombreUsuarioIngreso = nombreUsuarioIngreso;
        this.fechaIngreso = fechaIngreso;
    }

    public EventoNotaDTO(Integer idEventoNota, Integer idEvento, String nota, Integer idUsuarioIngreso) {
        this.idEventoNota = idEventoNota;
        this.idEvento = idEvento;
        this.nota = nota;
        this.idUsuarioIngreso = idUsuarioIngreso;
    }

    public EventoNotaDTO(EventoNota eventoNota) {
        this.idEventoNota = eventoNota.getIdEventoNota();
        this.idEvento = eventoNota.getEvento().getIdEvento();
        this.nota = eventoNota.getNota();
        this.idUsuarioIngreso = eventoNota.getUsuario().getIdUsuario();
        this.nombreUsuarioIngreso = eventoNota.getUsuario().getNombre();
        this.fechaIngreso = eventoNota.getFechaIngreso();
    }


    public Integer getIdEventoNota() {
        return idEventoNota;
    }

    public void setIdEventoNota(Integer idEventoNota) {
        this.idEventoNota = idEventoNota;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getNota() {
        return this.nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Integer getIdUsuarioIngreso() {
        return idUsuarioIngreso;
    }

    public void setIdUsuarioIngreso(Integer idUsuarioIngreso) {
        this.idUsuarioIngreso = idUsuarioIngreso;
    }

    public String getNombreUsuarioIngreso() {
        return nombreUsuarioIngreso;
    }

    public void setNombreUsuarioIngreso(String nombreUsuarioIngreso) {
        this.nombreUsuarioIngreso = nombreUsuarioIngreso;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        return "EventoNotaDTO{" +
                "idEventoNota=" + idEventoNota +
                ", idEvento=" + idEvento +
                ", nota='" + nota + '\'' +
                ", idUsuarioIngreso=" + idUsuarioIngreso +
                ", nombreUsuarioIngreso='" + nombreUsuarioIngreso + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                '}';
    }
}
