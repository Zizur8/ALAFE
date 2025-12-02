package com.vs.alafe.service;

import com.vs.alafe.model.entities.Propietario;
import com.vs.alafe.repository.PropietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PropietarioService {

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Transactional(readOnly = true)
    public Propietario save(Propietario object) {
        return propietarioRepository.save(object);
    }

    @Transactional(readOnly = true)
    public Optional<Propietario> findById(Integer id) {
        return propietarioRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Propietario> findAll() {
        return propietarioRepository.findAll();
    }
}
