package com.vs.alafe.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vs.alafe.model.entity.interfaces.ALAFEEntity;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "evento")
public class Evento implements ALAFEEntity,Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_evento", nullable = false)
    private Integer idEvento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    @JsonIgnore
    private Cliente cliente;

    @Column(name = "id_cliente", nullable = false, insertable = false, updatable = false)
    private Integer idCliente;

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
    @Column(name = "es_gratuito")
    private Boolean gratuito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_ingreso", nullable = false)
    private Usuario  usuarioIngreso;
    @Column(name = "id_usuario_ingreso", nullable = false, insertable = false, updatable = false)
    private Integer  idUsuarioIngreso;
    @Column(name = "id_usuario_modifico",nullable = false, insertable = false, updatable = false)
    private Integer idUsuarioModifico;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_modifico",nullable = false)
    private Usuario usuarioModifico;
    @Column(name = "fecha_ultima_modificacion")
    private LocalDateTime fechaUltimaModificacion;
    @Column(name = "cantidad_hora_extra")
    private Short cantidadHoraExtras;
    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso;
    @Column(name = "costo_hora_extra")
    private BigDecimal costoHoraExtra;


    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventoNota> notas;


    public Evento(){
    }

    public Evento(Integer idEvento){
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

    public LocalDateTime getHorarioFin() {
        return horarioFinal;
    }

    public void setHorarioFinal(LocalDateTime horarioFin) {
        this.horarioFinal = horarioFin;
    }

    public Boolean getDecoracion() {
        return decoracion;
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

    public Boolean getGratuito() {
        return gratuito;
    }

    public void setGratuito(Boolean gratuito) {
        this.gratuito = gratuito;
    }

    public Integer getIdUsuarioIngreso() {
        return idUsuarioIngreso;
    }

    public void setIdUsuarioIngreso(Integer idUsuarioIngreso) {
        this.idUsuarioIngreso = idUsuarioIngreso;
    }

    public Integer getIdUsuarioModifico() {
        return idUsuarioModifico;
    }

    public void setIdUsuarioModifico(Integer idUsuarioModifico) {
        this.idUsuarioModifico = idUsuarioModifico;
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

    public BigDecimal getCostoHoraExtra() {
        return costoHoraExtra;
    }

    public void setCostoHoraExtra(BigDecimal costoHoraExtra) {
        this.costoHoraExtra = costoHoraExtra;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Usuario getUsuarioIngreso() {
        return usuarioIngreso;
    }

    public void setUsuarioIngreso(Usuario usuarioIngreso) {
        this.usuarioIngreso = usuarioIngreso;
    }

    public Usuario getUsuarioModifico() {
        return usuarioModifico;
    }

    public void setUsuarioModifico(Usuario usuarioModifico) {
        this.usuarioModifico = usuarioModifico;
    }

    public List<EventoNota> getNotas() {
        return notas;
    }

    public void setNotas(List<EventoNota> notas) {
        this.notas = notas;
    }
}
