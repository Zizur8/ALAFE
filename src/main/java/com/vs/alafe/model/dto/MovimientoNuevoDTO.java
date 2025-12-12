package com.vs.alafe.model.dto;

import java.math.BigDecimal;

public class MovimientoNuevoDTO {

    private Integer idCliente;
    private Integer idEvento;
    private BigDecimal monto;
    private Integer idTipoMoneda;
    private BigDecimal tasaCambio;
    private Integer idTipoOperacionMovimiento;
    private Integer idUsuarioIngreso;

    public MovimientoNuevoDTO() {}

    public MovimientoNuevoDTO(MovimientoClienteDTO movimientoClienteDTO) {
        this.idCliente = movimientoClienteDTO.getIdCliente();
        this.idEvento = movimientoClienteDTO.getIdEvento();
        this.monto = movimientoClienteDTO.getMonto();
        this.idTipoMoneda = movimientoClienteDTO.getIdTipoMoneda();
        this.tasaCambio = movimientoClienteDTO.getTasaCambio();
        this.idUsuarioIngreso = movimientoClienteDTO.getIdUsuario();
    }

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

    public Integer getIdUsuarioIngreso() {
        return idUsuarioIngreso;
    }

    public void setIdUsuarioIngreso(Integer idUsuario) {
        this.idUsuarioIngreso = idUsuario;
    }

    @Override
    public String toString() {
        return "MovimientoNuevoDTO{" +
                "idCliente=" + idCliente +
                ", idEvento=" + idEvento +
                ", monto=" + monto +
                ", idTipoMoneda=" + idTipoMoneda +
                ", tasaCambio=" + tasaCambio +
                ", idTipoOperacionMovimiento=" + idTipoOperacionMovimiento +
                ", idUsuarioIngreso=" + idUsuarioIngreso +
                '}';
    }
}
