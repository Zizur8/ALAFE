package com.vs.alafe.model.dto;

import com.vs.alafe.model.entities.Evento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventoDTO {

    private Integer idEvento;
    private Integer idCliente;
    private Integer idAgenda;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFinal;
    private LocalDateTime horarioDecoracion;
    private Boolean decoracion;
    private BigDecimal costo;
    private Boolean especial;
    private Integer idUsuarioIngreso;
    private Integer idUsuarioModificacion;
    private LocalDateTime fechaUltimaModificacion;
    private Short cantidadHoraExtra;
    private LocalDateTime fechaIngreso;
    private BigDecimal costoHoraExtras;

    private String nombreUsuarioIngreso;
    private String usuarioUltimaModificacion;
    private Integer idEventoNota;
    private String notas;

    public EventoDTO(Integer idEvento, Integer idCliente, LocalDateTime horarioInicio, LocalDateTime horarioFin, Boolean decorar, Boolean ocupadaDosDias,
        BigDecimal costo, Boolean esEspecial, String nombreUsuarioIngreso, Integer idUsuarioIngreso, Integer idUsuarioModificacion,
        String usuarioUltimaModificacion, LocalDateTime fechaUltimaModificacion, Short cantidadHoraExtra,
        LocalDateTime fechaIngreso, BigDecimal costoHoraExtras, String notas, Integer idEventoNota) {
        this.idEvento = idEvento;
        this.idCliente = idCliente;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFin;
        this.decoracion = decorar;
        this.costo = costo;
        this.especial = esEspecial;
        this.nombreUsuarioIngreso = nombreUsuarioIngreso;
        this.idUsuarioIngreso = idUsuarioIngreso;
        this.usuarioUltimaModificacion = usuarioUltimaModificacion;
        this.fechaUltimaModificacion = fechaUltimaModificacion;
        this.cantidadHoraExtra = cantidadHoraExtra;
        this.fechaIngreso = fechaIngreso;
        this.costoHoraExtras = costoHoraExtras;
        this.notas = notas;
        this.idEventoNota = idEventoNota;
    }

    public EventoDTO(Evento evento){
        this.idEvento = evento.getIdEvento();
        this.idCliente = evento.getCliente().getIdCliente();
        this.horarioInicio = evento.getHorarioInicio();
        this.horarioFinal = evento.getHorarioFinal();
        this.decoracion = evento.getDecoracion();
        this.costo = evento.getCosto();
        this.especial = evento.getEspecial();
        this.idUsuarioIngreso = evento.getUsuarioIngreso().getIdUsuario();
        this.idUsuarioModificacion = evento.getUsuarioModificacion() != null
                ? evento.getUsuarioModificacion().getIdUsuario()
                : null;
        this.nombreUsuarioIngreso = evento.getUsuarioIngreso().getNombre();
        this.usuarioUltimaModificacion = evento.getUsuarioModificacion() != null ? evento.getUsuarioModificacion().getNombre() : null;
        this.fechaUltimaModificacion = evento.getFechaUltimaModificacion();
        this.cantidadHoraExtra = evento.getCantidadHoraExtra();
        this.fechaIngreso = evento.getFechaIngreso();
        this.costoHoraExtras = evento.getCostoHoraExtra();
//        this.notas = evento.getNotas()
//                .stream()
//                .map(EventoNota::getNota)
//                .collect(Collectors.joining(", "));

        this.notas = evento.getNota() != null ? evento.getNota().getNota() : null;

    }


    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer cliente) {
        this.idCliente = cliente;
    }

    public Boolean getDecoracion() {
        return decoracion;
    }

    public void setDecoracion(Boolean decoracion) {
        this.decoracion = decoracion;
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
        return cantidadHoraExtra;
    }

    public void setCantidadHoraExtra(Short cantidadHoraExtra) {
        this.cantidadHoraExtra = cantidadHoraExtra;
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

    public Integer getIdUsuarioIngreso() {
        return this.idUsuarioIngreso;
    }

    public void setIdUsuarioIngreso(Integer idUsuarioIngreso) {
        this.idUsuarioIngreso = idUsuarioIngreso;
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

    public LocalDateTime getHorarioDecoracion() {
        return horarioDecoracion;
    }

    public void setHorarioDecoracion(LocalDateTime horarioDecoracion) {
        this.horarioDecoracion = horarioDecoracion;
    }

    public Boolean getEspecial() {
        return especial;
    }

    public void setEspecial(Boolean especial) {
        this.especial = especial;
    }
        public Integer getIdUsuarioModificacion() {
        return idUsuarioModificacion;
    }

    public void setIdUsuarioModificacion(Integer idUsuarioModificacion) {
        this.idUsuarioModificacion = idUsuarioModificacion;
    }
}

