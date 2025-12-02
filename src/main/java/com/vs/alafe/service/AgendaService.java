package com.vs.alafe.service;

import com.vs.alafe.model.entities.Agenda;
import com.vs.alafe.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    @Transactional
    public Agenda save(Agenda agenda) {
        return agendaRepository.save(agenda);
    }

    @Transactional(readOnly = true)
    public Optional<Agenda> findById(Integer id) {
        return agendaRepository.findById(id);
    }

}
