package com.vs.alafe.model.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento")
public class Movimiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento", nullable = false)
    private Long idMovimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = true)
    private Cliente cliente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento", nullable = false)
    private Evento evento;
    @Column(name = "secuencial", nullable = false)
    private Integer secuencial;
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    @Column(name = "monto", nullable = false)
    private BigDecimal monto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_moneda", nullable = false)
    private TipoMoneda moneda;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_operacion_movimiento", nullable = false)
    private TipoOperacionMovimiento tipoOperacionMovimiento;
    @Column(name = "tasa_cambio", nullable = false)
    private BigDecimal tasaCambio;
    @Column(name = "monto_equivalente", insertable = false, updatable = false)
    private BigDecimal montoEquivalente;

    public Long getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Integer getSecuencial() {
        return secuencial;
    }

    public void setSecuencial(Integer secuencial) {
        this.secuencial = secuencial;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public TipoMoneda getMoneda() {
        return moneda;
    }

    public void setMoneda(TipoMoneda moneda) {
        this.moneda = moneda;
    }

    public TipoOperacionMovimiento getTipoOperacionMovimiento() {
        return tipoOperacionMovimiento;
    }

    public void setTipoOperacionMovimiento(TipoOperacionMovimiento tipoOperacionMovimiento) {
        this.tipoOperacionMovimiento = tipoOperacionMovimiento;
    }

    public BigDecimal getTasaCambio() {
        return tasaCambio;
    }

    public void setTasaCambio(BigDecimal tasaCambio) {
        this.tasaCambio = tasaCambio;
    }

    public BigDecimal getMontoEquivalente() {
        return montoEquivalente;
    }

    public void setMontoEquivalente(BigDecimal montoEquivalente) {
        this.montoEquivalente = montoEquivalente;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "idMovimiento=" + idMovimiento +
                ", cliente=" + cliente +
                ", evento=" + evento +
                ", secuencial=" + secuencial +
                ", fecha=" + fecha +
                ", usuario=" + usuario +
                ", monto=" + monto +
                ", moneda=" + moneda +
                ", tipoOperacionMovimiento=" + tipoOperacionMovimiento +
                ", tasaCambio=" + tasaCambio +
                ", montoEquivalente=" + montoEquivalente +
                '}';
    }
}
