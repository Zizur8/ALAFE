package com.vs.alafe.service;

import com.vs.alafe.model.dto.ColoniaDTO;
import com.vs.alafe.model.entities.Colonia;
import com.vs.alafe.repository.ColoniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ColoniaService {

    @Autowired
    private ColoniaRepository coloniaRepository;

    @Transactional
    public Colonia save(Colonia object) {
        return null;
    }

    @Transactional(readOnly = true)
    public Optional<Colonia> findById(Integer id) {
        return coloniaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public void delete(Colonia object) {
    }

    @Transactional(readOnly = true)
    public List<Colonia> findAll() {
        return coloniaRepository.findAll();
    }
}
