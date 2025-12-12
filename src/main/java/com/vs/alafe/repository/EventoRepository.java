package com.vs.alafe.repository;

import com.vs.alafe.model.entities.Evento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoRepository extends EntityRepository<Evento> {
    List<Evento> findByHorarioInicioBetweenAndEliminadoFalse(LocalDateTime horarioInicio, LocalDateTime horarioFin);

    @Query("SELECT e FROM Evento e WHERE e.decoracion = :decoracion")
    List<Evento> findEventosDecoracion(@Param("decoracion") Boolean isDecoracion);

    boolean existsById(Integer idEvento);

    boolean existsByMovimientos_Evento_IdEvento(Integer idEvento);

    @Query("SELECT e FROM Evento e " +
            "WHERE e.eliminado = false " +
            "AND e.horarioInicio <= :horarioFin " +
            "AND e.horarioFinal >= :horarioInicio")
    List<Evento> findEventosEnRango(@Param("horarioInicio") LocalDateTime inicio,
                                    @Param("horarioFin") LocalDateTime fin);


    @Query("SELECT COALESCE(SUM(m.monto), 0) FROM Movimiento m WHERE m.evento.idEvento = :idEvento")
    BigDecimal calcularMontoPagosEvento(@Param("idEvento") Integer idEvento);
}
