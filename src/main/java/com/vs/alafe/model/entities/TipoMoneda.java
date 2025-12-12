package com.vs.alafe.model.entities;

import com.vs.alafe.model.interfaces.ALAFEEntity;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "tipo_moneda")
public class TipoMoneda implements ALAFEEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_moneda", nullable = false)
    private Short idMoneda;

    @Column(name = "abreviacion",columnDefinition = "CHAR(3)")
    private String abreviacion;
    @Column(name = "nombre_moneda_es")
    private String nombreMonedaEs;
    @Column(name = "nombre_moneda_en")
    private String nombreMonedaEn;

    public Short getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Short idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
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
