package com.vs.alafe.model.dto;

import com.vs.alafe.model.entities.EventoNota;

public class EventoNotaDTO {

    private Integer idEventoNota;
    private Integer idEvento;
    private String nota;

    public EventoNotaDTO(Integer idEventoNota, Integer idEvento, String nota) {
        this.idEventoNota = idEventoNota;
        this.idEvento = idEvento;
        this.nota = nota;
    }

    public EventoNotaDTO(EventoNota eventoNota) {
        this.idEventoNota = eventoNota.getIdEventoNota();
        this.idEvento = eventoNota.getEvento().getIdEvento();
        this.nota = eventoNota.getNota();
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
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
