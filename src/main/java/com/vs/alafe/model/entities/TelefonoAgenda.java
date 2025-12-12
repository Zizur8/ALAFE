package com.vs.alafe.model.entities;

import jakarta.persistence.*;

public class TelefonoAgenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_telefono_agenda")
    private Integer idTelefonoAgenda;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agenda")
    private Integer idAgenda;
    @Column(name = "numero")
    private String numero;


    public Integer getIdTelefonoAgenda() {
        return idTelefonoAgenda;
    }

    public void setIdTelefonoAgenda(Integer idTelefonoAgenda) {
        this.idTelefonoAgenda = idTelefonoAgenda;
    }

    public Integer getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Integer idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
