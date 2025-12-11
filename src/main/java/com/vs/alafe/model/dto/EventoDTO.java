package com.vs.alafe.model.dto;

import com.vs.alafe.model.entities.Evento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private Short horasExtras;
    private LocalDateTime fechaIngreso;
    private BigDecimal costoHoraExtra;
    private List<EventoNotaDTO> notas;
    private MovimientoNuevoDTO movimientoNuevoDTO;
    private ClienteDTO cliente;


    public EventoDTO(){}
    public EventoDTO(Integer idEvento, Integer idCliente, LocalDateTime horarioInicio, LocalDateTime horarioDecoracion, LocalDateTime horarioFin, Boolean decorar,
        BigDecimal costo, Boolean esEspecial, Integer idUsuarioIngreso, LocalDateTime fechaUltimaModificacion, Short horasExtras,
        LocalDateTime fechaIngreso,ClienteDTO cliente,BigDecimal costoHoraExtras, List<EventoNotaDTO> notas ,MovimientoNuevoDTO movimientoNuevoDTO) {
        this.idEvento = idEvento;
        this.idCliente = idCliente;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFin;
        this.horarioDecoracion = horarioDecoracion;
        this.decoracion = decorar;
        this.costo = costo;
        this.especial = esEspecial;
        this.idUsuarioIngreso = idUsuarioIngreso;
        this.fechaUltimaModificacion = fechaUltimaModificacion;
        this.horasExtras = horasExtras;
        this.fechaIngreso = fechaIngreso;
        this.costoHoraExtra = costoHoraExtras;
        this.notas = notas;
        this.movimientoNuevoDTO = movimientoNuevoDTO;
        this.cliente = cliente;
    }

    public EventoDTO(Evento evento){
        this.idEvento = evento.getIdEvento();
        this.cliente = evento.getCliente() != null ? new ClienteDTO(evento.getCliente()) : null;
        this.idCliente = evento.getCliente() != null ? evento.getCliente().getIdCliente() : null;
        this.horarioInicio = evento.getHorarioInicio();
        this.horarioFinal = evento.getHorarioFinal();
        this.horarioDecoracion = evento.getHorarioDecoracion();
        this.decoracion = evento.getDecoracion();
        this.costo = evento.getCosto();
        this.especial = evento.getEspecial();
        this.idUsuarioIngreso = evento.getUsuarioIngreso().getIdUsuario();
        this.idUsuarioModificacion = evento.getUsuarioModificacion() != null
                ? evento.getUsuarioModificacion().getIdUsuario()
                : null;
        this.fechaUltimaModificacion = evento.getFechaUltimaModificacion();
        this.horasExtras = evento.getHorasExtras();
        this.fechaIngreso = evento.getFechaAlta();
        this.costoHoraExtra = evento.getCostoHoraExtra();
        this.notas = evento.getNotas().stream()
                .map(eventoNota -> {
                    EventoNotaDTO eventoNotaDTO = new EventoNotaDTO(eventoNota);
                    return eventoNotaDTO;
                })
                .collect(Collectors.toList());

//        this.notas = evento.getNota() != null ? evento.getNota().getNota() : null;

    }



    public Boolean getDecoracion() {
        return decoracion;
    }

    public void setDecoracion(Boolean decoracion) {
        this.decoracion = decoracion;
    }

    public LocalDateTime getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    public void setFechaUltimaModificacion(LocalDateTime fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    public Short getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(Short cantidadHoraExtra) {
        this.horasExtras = cantidadHoraExtra;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public BigDecimal getCostoHoraExtra() {
        return costoHoraExtra;
    }

    public void setCostoHoraExtra(BigDecimal costoHoraExtra) {
        this.costoHoraExtra = costoHoraExtra;
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

    public List<EventoNotaDTO> getNotas() {
        return notas;
    }

    public void setNotas(List<EventoNotaDTO> notas) {
        this.notas = notas;
    }

    public MovimientoNuevoDTO getMovimientoNuevoDTO() {
        return movimientoNuevoDTO;
    }

    public void setMovimientoNuevoDTO(MovimientoNuevoDTO movimientoNuevoDTO) {
        this.movimientoNuevoDTO = movimientoNuevoDTO;
    }

    public LocalDateTime getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(LocalDateTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "EventoDTO{" +
                "idEvento=" + idEvento +
                ", idCliente=" + idCliente +
                ", idAgenda=" + idAgenda +
                ", horarioInicio=" + horarioInicio +
                ", horarioFinal=" + horarioFinal +
                ", horarioDecoracion=" + horarioDecoracion +
                ", decoracion=" + decoracion +
                ", costo=" + costo +
                ", especial=" + especial +
                ", idUsuarioIngreso=" + idUsuarioIngreso +
                ", idUsuarioModificacion=" + idUsuarioModificacion +
                ", fechaUltimaModificacion=" + fechaUltimaModificacion +
                ", horasExtras=" + horasExtras +
                ", fechaIngreso=" + fechaIngreso +
                ", costoHoraExtra=" + costoHoraExtra +
                ", notas=" + notas +
                ", movimientoNuevoDTO=" + movimientoNuevoDTO +
                ", cliente=" + cliente +
                '}';
    }
}

