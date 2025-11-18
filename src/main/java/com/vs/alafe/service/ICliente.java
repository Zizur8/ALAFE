package com.vs.alafe.service;

import java.util.List;

public interface ICliente<Cliente> extends IEntity<Cliente>{
    @Override
    Cliente save(Cliente object);

    @Override
    Cliente findById(Integer id);

    @Override
    void delete(Cliente object);

    @Override
    List<Cliente> findAll();
}
