package com.vs.alafe.service;

import java.time.LocalDateTime;
import java.util.List;

public interface IEvento<Evento> extends IEntity<Evento>{
    @Override
    Evento save(Evento object);

    @Override
    Evento findById(Integer id);

    @Override
    void delete(Evento object);

    @Override
    List<Evento> findAll();

    List<Evento> findByDate(LocalDateTime date1, LocalDateTime date2);

}
