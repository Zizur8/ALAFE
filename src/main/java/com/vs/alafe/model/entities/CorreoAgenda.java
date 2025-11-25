package com.vs.alafe.model.entities;

import jakarta.persistence.*;

public class CorreoAgenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_correo")
    private Integer idCorreo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agenda")
    private Integer idAgenda;
    @Column(name = "direccion")
    private String direccion;

}
