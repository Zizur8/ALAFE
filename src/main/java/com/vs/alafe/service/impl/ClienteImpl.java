package com.vs.alafe.service.impl;

import com.vs.alafe.model.dao.ClienteDao;
import com.vs.alafe.model.dao.EventoDao;
import com.vs.alafe.model.entity.Cliente;
import com.vs.alafe.service.ICliente;
import com.vs.alafe.service.IEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClienteImpl implements ICliente<Cliente> {


    @Autowired
    private ClienteDao clienteDao;

    @Transactional
    @Override
    public Cliente save(Cliente object) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public void delete(Cliente object) {
        clienteDao.delete(object);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Cliente> findAll() {
        return clienteDao.findAll();
    }

}
