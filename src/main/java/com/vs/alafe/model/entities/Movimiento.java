package com.vs.alafe.model.entities;

import com.vs.alafe.model.interfaces.ALAFEEntity;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento")
public class Movimiento implements ALAFEEntity, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_movimiento", nullable = false)
    private BigInteger idMovimiento;

    @Column(name = "id_cliente", nullable = false)
    private Cliente cliente;
    @Column(name = "id_evento", nullable = false)
    private Evento evento;
    @Column(name = "secuencial", nullable = false)
    private Integer secuencial;
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;
    @Column(name = "id_aprobacion_usuario", nullable = false)
    private Integer idAprobacionUsuario;
    @Column(name = "monto", nullable = false)
    private BigDecimal monto;
    @Column(name = "id_tipo_moneda", nullable = false)
    private Moneda moneda;
    @Column(name = "id_tipo_operacion_movimiento", nullable = false)
    private TipoOperacionMovimiento tipoOperacionMovimiento;
    @Column(name = "tasa_cambio", nullable = false)
    private BigDecimal tasaCambio;
    @Column(name = "monto_equivalente", nullable = false)
    private BigDecimal montoEquivalente;
}
