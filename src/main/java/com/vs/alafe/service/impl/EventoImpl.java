package com.vs.alafe.service.impl;

import com.vs.alafe.model.dao.EventoDao;
import com.vs.alafe.model.entity.Evento;
import com.vs.alafe.service.IEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventoImpl implements IEvento<Evento> {

    @Autowired
    private EventoDao eventoDao;

    @Transactional
    @Override
    public Evento save(Evento evento) {
        return eventoDao.save(evento);
    }

    @Transactional(readOnly = true)
    @Override
    public Evento findById(Integer id) {
        return eventoDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Evento evento) {
        eventoDao.delete(evento);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Evento> findAll() {
        return eventoDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<Evento> findByDate(LocalDateTime horarioInicio, LocalDateTime horarioFin) {
        return eventoDao.findByHorarioInicioBetween(horarioInicio,horarioFin);
    }
}
