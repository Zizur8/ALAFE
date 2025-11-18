package com.vs.alafe.model.dao;

import com.vs.alafe.model.entity.Evento;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoDao extends EntityDao<Evento> {
    List<Evento> findByHorarioInicioBetween(LocalDateTime horarioInicio, LocalDateTime horarioFin);
}
