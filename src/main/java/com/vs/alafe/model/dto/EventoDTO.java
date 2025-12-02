package com.vs.alafe.model.dto;

import com.vs.alafe.model.entities.Evento;
import com.vs.alafe.model.entities.EventoNota;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class EventoDTO {

    private Integer idEvento;
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
    private String nombreUsuarioIngreso;
    private String usuarioUltimaModificacion;
    private Integer idEventoNota;
    //private String notas;
    private List<EventoNotaDTO> notas;



    private ClienteDTO cliente;

    public EventoDTO(Integer idEvento, Integer idCliente, LocalDateTime horarioInicio, LocalDateTime horarioFin, Boolean decorar, Boolean ocupadaDosDias,
        BigDecimal costo, Boolean esEspecial, String nombreUsuarioIngreso, Integer idUsuarioIngreso, Integer idUsuarioModificacion,
        String usuarioUltimaModificacion, LocalDateTime fechaUltimaModificacion, Short horasExtras,
        LocalDateTime fechaIngreso, BigDecimal costoHoraExtras, List<EventoNotaDTO> notas, Integer idEventoNota, ClienteDTO cliente) {
        this.idEvento = idEvento;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFin;
        this.decoracion = decorar;
        this.costo = costo;
        this.especial = esEspecial;
        this.nombreUsuarioIngreso = nombreUsuarioIngreso;
        this.idUsuarioIngreso = idUsuarioIngreso;
        this.usuarioUltimaModificacion = usuarioUltimaModificacion;
        this.fechaUltimaModificacion = fechaUltimaModificacion;
        this.horasExtras = horasExtras;
        this.fechaIngreso = fechaIngreso;
        this.costoHoraExtra = costoHoraExtras;
        this.notas = notas;
        this.idEventoNota = idEventoNota;
        this.cliente = cliente;
    }

    public EventoDTO(Evento evento){
        this.idEvento = evento.getIdEvento();
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
        this.horasExtras = evento.getHorasExtras();
        this.fechaIngreso = evento.getFechaAlta();
        this.costoHoraExtra = evento.getCostoHoraExtra();
        this.notas = evento.getNotas().stream()
                .map(eventoNota -> {
                    EventoNotaDTO eventoNotaDTO = new EventoNotaDTO(eventoNota);
                    return eventoNotaDTO;
                })
                .collect(Collectors.toList());
        this.cliente = new ClienteDTO(evento.getCliente());

//        this.notas = evento.getNota() != null ? evento.getNota().getNota() : null;

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

    public String getNombreUsuarioIngreso() {
        return nombreUsuarioIngreso;
    }

    public void setNombreUsuarioIngreso(String nombreUsuarioIngreso) {
        this.nombreUsuarioIngreso = nombreUsuarioIngreso;
    }

//    public String getNotas() {
//        return notas;
//    }
//
//    public void setNotas(String notas) {
//        this.notas = notas;
//    }

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

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }
}

