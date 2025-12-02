package com.vs.alafe.repository;


import com.vs.alafe.model.entities.EventoNota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoNotaRepository extends JpaRepository<EventoNota,Integer> {

    List<EventoNota> findByEvento_IdEvento(Integer idEvento);
}