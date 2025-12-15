package com.vs.alafe.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.vs.alafe.model.dto.EventoDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "evento")
public class Evento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento", nullable = false)
    private Integer idEvento;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_cliente", nullable = true)
    private Cliente cliente;

    @Column(name = "horario_inicio", nullable = false)
    private LocalDateTime horarioInicio;
    @Column(name = "horario_final", nullable = false)
    private LocalDateTime horarioFinal;
    @Column(name = "decoracion", nullable = false)
    private Boolean decoracion;
    @Column(name = "costo", nullable = false)
    private BigDecimal costo;
    @Column(name = "es_especial", nullable = false)
    private Boolean especial;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario_ingreso", nullable = false)
    private Usuario usuarioIngreso;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario_modificacion", nullable = true)
    private Usuario usuarioModificacion;
    @Column(name = "fecha_ultima_modificacion", nullable = false)
    private LocalDateTime fechaUltimaModificacion;
    @Column(name = "fecha_alta", nullable = false)
    private LocalDateTime fechaAlta;
    @Column(name = "costo_hora_extra", nullable = false)
    private BigDecimal costoHoraExtra;
    @Column(name = "horas_extras", nullable = false)
    private Short horasExtras;
    @Column(name = "horario_decoracion")
    private LocalDateTime horarioDecoracion;
    @OneToMany(mappedBy = "evento", fetch = FetchType.LAZY)
    private List<EventoNota> notas = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agenda", nullable = false)
    private Agenda agenda;
    @Column(name = "eliminado")
    private Boolean eliminado;
    @OneToMany(mappedBy = "evento", fetch = FetchType.LAZY)
    private List<Movimiento> movimientos = new ArrayList<>();
    @Transient
    private BigDecimal montoPagos = BigDecimal.ZERO;
//    @OneToOne(mappedBy = "evento", cascade = CascadeType.ALL)
//    private EventoNota nota;

    public Evento() {
    }

    public Evento(EventoDTO eventoDTO) {
        this.idEvento = eventoDTO.getIdEvento();
        this.costo = eventoDTO.getCosto();
        this.costoHoraExtra = eventoDTO.getCostoHoraExtra();
        this.decoracion = eventoDTO.getDecoracion();
        this.especial = eventoDTO.getEspecial();
        this.horarioInicio = eventoDTO.getHorarioInicio();
        this.horarioFinal = eventoDTO.getHorarioFinal();
        this.horasExtras = eventoDTO.getHorasExtras();
    }

    public Evento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalDateTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalDateTime getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(LocalDateTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public Boolean getDecoracion() {
        return decoracion;
    }

//    public EventoNota getNota() {
//        return nota;
//    }
//
//    public void setNota(EventoNota nota) {
//        this.nota = nota;
//    }

    public void setDecoracion(Boolean decoracion) {
        this.decoracion = decoracion;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Boolean getEspecial() {
        return especial;
    }

    public void setEspecial(Boolean especial) {
        this.especial = especial;
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

    public void setHorasExtras(Short horasExtras) {
        this.horasExtras = horasExtras;
    }

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public BigDecimal getCostoHoraExtra() {
        return costoHoraExtra;
    }

    public void setCostoHoraExtra(BigDecimal costoHoraExtra) {
        this.costoHoraExtra = costoHoraExtra;
    }

    public Usuario getUsuarioIngreso() {
        return usuarioIngreso;
    }

    public void setUsuarioIngreso(Usuario usuarioIngreso) {
        this.usuarioIngreso = usuarioIngreso;
    }

    public Usuario getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(Usuario usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public List<EventoNota> getNotas() {
        return notas;
    }

    public void setNotas(List<EventoNota> notas) {
        this.notas = notas;
    }

    public LocalDateTime getHorarioDecoracion() {
        return horarioDecoracion;
    }

    public void setHorarioDecoracion(LocalDateTime horarioDecoracion) {
        this.horarioDecoracion = horarioDecoracion;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public BigDecimal getMontoPagos() {
        return montoPagos;
    }

    public void setMontoPagos(BigDecimal montoPagos) {
        this.montoPagos = montoPagos;
    }

    @Override
    public String toString() {
        return "Evento{" +
                "idEvento=" + idEvento +
                ", cliente=" + cliente +
                ", horarioInicio=" + horarioInicio +
                ", horarioFinal=" + horarioFinal +
                ", decoracion=" + decoracion +
                ", costo=" + costo +
                ", especial=" + especial +
                ", usuarioIngreso=" + usuarioIngreso +
                ", usuarioModificacion=" + usuarioModificacion +
                ", fechaUltimaModificacion=" + fechaUltimaModificacion +
                ", horasExtras=" + horasExtras +
                ", fechaAlta=" + fechaAlta +
                ", costoHoraExtra=" + costoHoraExtra +
                ", horarioDecoracion=" + horarioDecoracion +
                '}';
    }
}
