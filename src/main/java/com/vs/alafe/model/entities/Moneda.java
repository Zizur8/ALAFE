package com.vs.alafe.model.entities;

import com.vs.alafe.model.interfaces.ALAFEEntity;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "tipo_moneda")
public class Moneda implements ALAFEEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_moneda", nullable = false)
    private Integer idMoneda;

    @Column(name = "abreviacion")
    private String abreaviacion;
    @Column(name = "nombre_moneda_es")
    private String nombreMonedaEs;
    @Column(name = "nombre_moneda_en")
    private String nombreMonedaEn;

    public Integer getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Integer idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getAbreaviacion() {
        return abreaviacion;
    }

    public void setAbreaviacion(String abreaviacion) {
        this.abreaviacion = abreaviacion;
    }

    public String getNombreMonedaEs() {
        return nombreMonedaEs;
    }

    public void setNombreMonedaEs(String nombreMonedaEs) {
        this.nombreMonedaEs = nombreMonedaEs;
    }

    public String getNombreMonedaEn() {
        return nombreMonedaEn;
    }

    public void setNombreMonedaEn(String nombreMonedaEn) {
        this.nombreMonedaEn = nombreMonedaEn;
    }
}
