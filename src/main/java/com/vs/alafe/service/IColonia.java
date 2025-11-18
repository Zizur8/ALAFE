package com.vs.alafe.service;

import java.util.List;

public interface IColonia<Colonia> extends IEntity<Colonia>{
    @Override
    Colonia save(Colonia object);

    @Override
    Colonia findById(Integer id);

    @Override
    void delete(Colonia object);

    @Override
    List<Colonia> findAll();
}
