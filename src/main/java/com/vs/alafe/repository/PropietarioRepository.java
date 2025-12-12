package com.vs.alafe.repository;

import com.vs.alafe.model.entities.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropietarioRepository extends JpaRepository<Propietario,Integer> {
}
