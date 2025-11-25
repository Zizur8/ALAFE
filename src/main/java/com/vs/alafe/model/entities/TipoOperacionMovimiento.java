package com.vs.alafe.model.entities;

import com.vs.alafe.model.interfaces.ALAFEEntity;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "tipo_operacion_movimiento")
public class TipoOperacionMovimiento implements ALAFEEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_tipo_operacion", nullable = false)
    private Short idTipoOperacion;

    @Column(name = "movimiento",nullable = false)
    private String movimiento;

    public Short getIdTipoOperacion() {
        return idTipoOperacion;
    }

    public void setIdTipoOperacion(Short idTipoOperacion) {
        this.idTipoOperacion = idTipoOperacion;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }
}
