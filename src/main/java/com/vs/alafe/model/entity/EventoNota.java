package com.vs.alafe.model.entity;

import com.vs.alafe.model.entity.interfaces.ALAFEEntity;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import java.io.Serializable;

@Entity
@Table(name = "evento_nota")
public class EventoNota implements ALAFEEntity,Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_evento_nota", nullable = false)
    private Integer idEventoNota;

    @Column(name = "id_evento", nullable = false, insertable = false, updatable = false)
    private Integer idEvento;

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
