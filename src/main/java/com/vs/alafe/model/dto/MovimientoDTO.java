package com.vs.alafe.model.dto;

import com.vs.alafe.model.entities.Movimiento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MovimientoDTO {

    private long idMovimiento;
    private String cliente;
    private Integer idEvento;
    private String fechaEvento;
    private Integer secuencial;
    private String fecha;
    private BigDecimal monto;
    private String moneda;
    private String operacion;
    private BigDecimal tasaCambio;
    private BigDecimal montoEquivalente;
    private String usuario;


    public MovimientoDTO(Movimiento movimiento){
        this.idMovimiento = movimiento.getIdMovimiento();
        this.cliente = movimiento.getCliente().nombreCompleto();
        this.idEvento = movimiento.getEvento().getIdEvento();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.fechaEvento = movimiento.getEvento().getHorarioInicio().format(formatter);
        this.fecha = movimiento.getFecha().format(formatter);
        this.monto = movimiento.getMonto();
        this.secuencial = movimiento.getSecuencial();
        this.moneda = movimiento.getMoneda().getAbreviacion();
        this.operacion = movimiento.getTipoOperacionMovimiento().getMovimiento();
        this.tasaCambio = movimiento.getTasaCambio();
        this.montoEquivalente = movimiento.getMontoEquivalente();
        this.usuario = movimiento.getUsuario().nombreCompleto();
    }




    public long getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Integer getSecuencial() {
        return secuencial;
    }

    public void setSecuencial(Integer secuencial) {
        this.secuencial = secuencial;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
