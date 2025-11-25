package com.vs.alafe.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vs.alafe.model.entities.EventoNota;
import com.vs.alafe.repository.EventoNotaRepository;

@Service
public class EventoNotaService {

    @Autowired
    private EventoNotaRepository eventoNotaRepository;

    @Transactional
    public EventoNota save(EventoNota eventoNota) {
        return eventoNotaRepository.save(eventoNota);
    }

    @Transactional(readOnly = true)
    public Optional<EventoNota> findById(Integer id) {
        return eventoNotaRepository.findById(id);
    }

    @Transactional
    public void delete(EventoNota eventoNota) {
        eventoNotaRepository.delete(eventoNota);
    }

    @Transactional(readOnly = true)
    public List<EventoNota> findAll() {
        return eventoNotaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<EventoNota> findByEvento_IdEvento(Integer idEvento) {
        return eventoNotaRepository.findByEvento_IdEvento(idEvento);
    }

}
