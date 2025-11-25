package com.vs.alafe.service;

import com.vs.alafe.model.entities.Cliente;
import com.vs.alafe.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente save(Cliente object) {
        return clienteRepository.save(object);
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public void delete(Cliente object) {
        clienteRepository.delete(object);
    }

    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Cliente> findByTelefono(String telefono) {
        return clienteRepository.findByTelefonoContaining(telefono);
    }
}
