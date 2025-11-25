package com.vs.alafe.model.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agenda",nullable = false)
    private Integer idAgenda;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propietario",nullable = false)
    private Propietario propietario;

    @Column(name = "nombre_agenda")
    private String nombreAgenda;
    @Column(name = "descripcion")
    private String descripcion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_colonia", nullable = true)
    private Colonia colonia;
    @Column(name = "numero_exterior")
    private String numeroExterior;
    @Column(name = "calle")
    private String calle;
    @Column(name = "fecha_alta")
    private LocalDateTime fechaAlta;


}
