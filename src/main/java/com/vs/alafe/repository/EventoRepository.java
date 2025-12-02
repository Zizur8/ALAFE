package com.vs.alafe.repository;

import com.vs.alafe.model.entities.Evento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoRepository extends EntityRepository<Evento> {
    List<Evento> findByHorarioInicioBetween(LocalDateTime horarioInicio, LocalDateTime horarioFin);

    @Query("SELECT e FROM Evento e WHERE e.decoracion = :decoracion")
    List<Evento> findEventosDecoracion(@Param("decoracion") Boolean isDecoracion);
}
