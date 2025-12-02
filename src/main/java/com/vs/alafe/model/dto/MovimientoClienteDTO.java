package com.vs.alafe.model.dto;

import com.vs.alafe.model.entities.Movimiento;

import java.math.BigDecimal;

public class MovimientoClienteDTO {

    private Integer idCliente;
    private Integer idEvento;
    private BigDecimal monto;
    private Integer idTipoMoneda;
    private BigDecimal tasaCambio;
    private Integer idTipoOperacionMovimiento;
    private BigDecimal montoEquivalente;
    private Integer idUsuario;

    public MovimientoClienteDTO() {}

//    public MovimientoClienteDTO(MovimientoClienteDTO movimientoClienteDTO) {
//        this.idCliente = movimientoClienteDTO.getIdCliente();
//        this.idEvento = movimientoClienteDTO.getIdEvento();
//        this.monto = movimientoClienteDTO.getMonto();
//        this.idTipoMoneda = movimientoClienteDTO.getIdTipoMoneda();
//        this.tasaCambio = movimientoClienteDTO.get
//        this.montoEquivalente = movimientoClienteDTO.getMontoEquivalente();
//        this.idUsuario = movimientoClienteDTO.getIdUsuario();
//    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public Integer getIdTipoMoneda() {
        return idTipoMoneda;
    }

    public void setIdTipoMoneda(Integer idTipoMoneda) {
        this.idTipoMoneda = idTipoMoneda;
    }

    public BigDecimal getTasaCambio() {
        return tasaCambio;
    }

    public void setTasaCambio(BigDecimal tasaCambio) {
        this.tasaCambio = tasaCambio;
    }

    public Integer getIdTipoOperacionMovimiento() {
        return idTipoOperacionMovimiento;
    }

    public void setIdTipoOperacionMovimiento(Integer idTipoOperacionMovimiento) {
        this.idTipoOperacionMovimiento = idTipoOperacionMovimiento;
    }

    public BigDecimal getMontoEquivalente() {
        return montoEquivalente;
    }

    public void setMontoEquivalente(BigDecimal montoEquivalente) {
        this.montoEquivalente = montoEquivalente;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
