package com.vs.alafe.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "evento")
public class Evento implements  Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento", nullable = false)
    private Integer idEvento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    @JoinColumn(name = "id_propietario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Propietario propietario;

    @Column(name = "horario_inicio")
    private LocalDateTime horarioInicio;
    @Column(name = "horario_final")
    private LocalDateTime horarioFinal;
    @Column(name = "decoracion")
    private Boolean decoracion;
    @Column(name = "costo")
    private BigDecimal costo;
    @Column(name = "es_especial")
    private Boolean especial;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario_ingreso", nullable = false)
    private Usuario usuarioIngreso;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_usuario_modificacion", nullable = true)
    private Usuario usuarioModificacion;
    @Column(name = "fecha_ultima_modificacion")
    private LocalDateTime fechaUltimaModificacion;
    @Column(name = "cantidad_hora_extra")
    private Short cantidadHoraExtra;
    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso;
    @Column(name = "costo_hora_extra")
    private BigDecimal costoHoraExtra;
    @Column(name = "horario_decoracion")
    private LocalDateTime horarioDecoracion;
    //    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
//    private List<EventoNota notas = new ArrayList<>();
    @OneToOne(mappedBy = "evento", cascade = CascadeType.ALL)
    private EventoNota nota;

    public Evento() {
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

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
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

    public EventoNota getNota() {
        return nota;
    }

    public void setNota(EventoNota nota) {
        this.nota = nota;
    }

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
//
//    public List<EventoNota> getNotas() {
//        return notas;
//    }
//
//    public void setNotas(List<EventoNota> notas) {
//        this.notas = notas;
//    }

    public LocalDateTime getHorarioDecoracion() {
        return horarioDecoracion;
    }

    public void setHorarioDecoracion(LocalDateTime horarioDecoracion) {
        this.horarioDecoracion = horarioDecoracion;
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
                ", cantidadHoraExtra=" + cantidadHoraExtra +
                ", fechaIngreso=" + fechaIngreso +
                ", costoHoraExtra=" + costoHoraExtra +
                ", horarioDecoracion=" + horarioDecoracion +
                ", nota=" + nota +
                '}';
    }
}
