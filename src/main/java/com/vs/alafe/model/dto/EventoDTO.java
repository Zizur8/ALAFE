package com.vs.alafe.model.dto;

import com.vs.alafe.model.entity.Evento;
import com.vs.alafe.model.entity.EventoNota;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class EventoDTO {

    private Integer idEvento;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFinal;
    private Boolean decorar;
    private Boolean ocupaDosDias;
    private BigDecimal costo;
    private Boolean esEspecial;
    private Integer idCliente;
    private String nombreUsuarioIngreso;
    private String usuarioUltimaModificacion;
    private LocalDateTime fechaUltimaModificacion;
    private Short cantidadHoraExtras;
    private LocalDateTime fechaIngreso;
    private BigDecimal costoHoraExtras;



    private String notas;

    public EventoDTO(Integer idEvento, Integer idCliente, LocalDateTime horarioInicio, LocalDateTime horarioFin, Boolean decorar, Boolean ocupadaDosDias, BigDecimal costo, Boolean esEspecial,
                     String nombreUsuarioIngreso, String usuarioUltimaModificacion, LocalDateTime fechaUltimaModificacion, Short cantidadHoraExtra,
                     LocalDateTime fechaIngreso, BigDecimal costoHoraExtras, String notas) {
        this.idEvento = idEvento;
        this.idCliente = idCliente;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFin;
        this.decorar = decorar;
        this.ocupaDosDias = ocupadaDosDias;
        this.costo = costo;
        this.esEspecial = esEspecial;
        this.nombreUsuarioIngreso = nombreUsuarioIngreso;
        this.usuarioUltimaModificacion = usuarioUltimaModificacion;
        this.fechaUltimaModificacion = fechaUltimaModificacion;
        this.cantidadHoraExtras = cantidadHoraExtra;
        this.fechaIngreso = fechaIngreso;
        this.costoHoraExtras = costoHoraExtras;
        this.notas = notas;
    }

    public EventoDTO(Evento evento){

        this.idEvento = evento.getIdEvento();
        this.idCliente = evento.getIdCliente();
        this.horarioInicio = evento.getHorarioInicio();
        this.horarioFinal = evento.getHorarioFin();
        this.decorar = evento.getDecoracion();
        //this.ocupaDosDias = evento.getOcupadaDosDias();
        this.costo = evento.getCosto();
        this.esEspecial = evento.getEspecial();
        this.nombreUsuarioIngreso = evento.getUsuarioIngreso().getNombre();
        this.usuarioUltimaModificacion = evento.getUsuarioModifico() != null ? evento.getUsuarioModifico().getNombre() : null;
        this.fechaUltimaModificacion = evento.getFechaUltimaModificacion();
        this.cantidadHoraExtras = evento.getCantidadHoraExtra();
        this.fechaIngreso = evento.getFechaIngreso();
        this.costoHoraExtras = evento.getCostoHoraExtra();
        this.notas = evento.getNotas()
                .stream()
                .map(EventoNota::getNota)
                .collect(Collectors.joining(", "));
    }


    public Short getCantidadHoraExtras() {
        return cantidadHoraExtras;
    }

    public void setCantidadHoraExtras(Short cantidadHoraExtras) {
        this.cantidadHoraExtras = cantidadHoraExtras;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer cliente) {
        this.idCliente = cliente;
    }

    public Boolean getDecorar() {
        return decorar;
    }

    public void setDecorar(Boolean decorar) {
        this.decorar = decorar;
    }

    public Boolean getEsEspecial() {
        return esEspecial;
    }

    public void setEsEspecial(Boolean esEspecial) {
        this.esEspecial = esEspecial;
    }

    public String getUsuarioUltimaModificacion() {
        return usuarioUltimaModificacion;
    }

    public void setUsuarioUltimaModificacion(String usuarioUltimaModificacion) {
        this.usuarioUltimaModificacion = usuarioUltimaModificacion;
    }

    public LocalDateTime getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(LocalDateTime fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public Short getCantidadHoraExtra() {
        return cantidadHoraExtras;
    }

    public void setCantidadHoraExtra(Short cantidadHoraExtra) {
        this.cantidadHoraExtras = cantidadHoraExtra;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public BigDecimal getCostoHoraExtras() {
        return costoHoraExtras;
    }

    public void setCostoHoraExtras(BigDecimal costoHoraExtras) {
        this.costoHoraExtras = costoHoraExtras;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public LocalDateTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalDateTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalDateTime getHorarioFin() {
        return horarioFinal;
    }

    public void setHorarioFin(LocalDateTime horarioFin) {
        this.horarioFinal = horarioFin;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getNombreUsuarioIngreso() {
        return nombreUsuarioIngreso;
    }

    public void setNombreUsuarioIngreso(String nombreUsuarioIngreso) {
        this.nombreUsuarioIngreso = nombreUsuarioIngreso;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

}

