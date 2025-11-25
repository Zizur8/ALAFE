package com.vs.alafe.repository;

import com.vs.alafe.model.entities.Evento;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoRepository extends EntityRepository<Evento> {
    List<Evento> findByHorarioInicioBetween(LocalDateTime horarioInicio, LocalDateTime horarioFin);
}
