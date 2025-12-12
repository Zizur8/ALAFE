package com.vs.alafe.repository;

import com.vs.alafe.model.entities.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento,Integer> {


    @Query("SELECT COALESCE(MAX(m.secuencial), 0) FROM Movimiento m WHERE m.evento.idEvento = :idEvento")
    Integer findMaxSecuencial(@Param("idEvento") Integer idEvento);

    boolean existsByEvento_IdEvento(Integer idEvento);

}
