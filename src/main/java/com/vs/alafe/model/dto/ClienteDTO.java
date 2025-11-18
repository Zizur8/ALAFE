package com.vs.alafe.model.dto;

import com.vs.alafe.model.entity.Cliente;

public class ClienteDTO {
    private Long idCliente;
    private String telefono;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Integer idColonia;
    private String numeroExterior;
    private String calle;
    private String correo;

    public ClienteDTO(Long idCliente, String telefono, String nombre, String apellidoPaterno, String apellidoMaterno, Integer idColonia, String numeroExterior, String calle
    ,String correo) {
        this.idCliente = idCliente;
        this.telefono = telefono;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.idColonia = idColonia;
        this.numeroExterior = numeroExterior;
        this.calle = calle;
        this.correo = correo;
    }

    public ClienteDTO(Cliente cliente){
        this.idCliente = cliente.getIdCliente();
        this.telefono = cliente.getTelefono();
        this.nombre = cliente.getNombre();
        this.apellidoPaterno = cliente.getApellidoPaterno();
        this.apellidoMaterno = cliente.getApellidoMaterno();
        this.idColonia = cliente.getColonia().getIdColonia();
        this.numeroExterior = cliente.getNumeroExterior();
        this.calle = cliente.getCalle();
        this.correo = cliente.getCorreo().getCorreo();
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(Integer idColonia) {
        this.idColonia = idColonia;
    }

    public String getNumeroExterior() {
        return numeroExterior;
    }

    public void setNumeroExterior(String numeroExterior) {
        this.numeroExterior = numeroExterior;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
