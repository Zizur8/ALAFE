package com.vs.alafe.repository;

import com.vs.alafe.model.entities.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

}
