package com.vs.alafe.model.entities;

import com.vs.alafe.model.interfaces.ALAFEEntity;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "evento_nota")
public class EventoNota implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "id_evento_nota", nullable = false)
    private Integer idEventoNota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;


    @Column(name = "nota", nullable = false)
    private String nota;

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
}
