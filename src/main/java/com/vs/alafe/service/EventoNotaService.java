package com.vs.alafe.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vs.alafe.model.dto.EventoNotaDTO;
import com.vs.alafe.model.entities.Evento;
import com.vs.alafe.model.entities.Usuario;
import com.vs.alafe.repository.EventoRepository;
import com.vs.alafe.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vs.alafe.model.entities.EventoNota;
import com.vs.alafe.repository.EventoNotaRepository;

@Service
public class EventoNotaService {

    @Autowired
    private EventoNotaRepository eventoNotaRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EventoRepository eventoRepository;

    @Transactional
    public EventoNota save(EventoNota eventoNota) {

        if (eventoNota.getFechaIngreso() == null) {
            eventoNota.setFechaIngreso(LocalDateTime.now());
        }

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

    public List<EventoNota> toEventoNotasListado(List<EventoNotaDTO> notasDTO) {

        List<EventoNota> notas = new ArrayList<>();
        notasDTO.forEach(nota -> {
            EventoNota eventoNota = new EventoNota();
            if (nota.getIdUsuarioIngreso() == null) {
                throw new IllegalArgumentException("El evento nota debe contar con usuario ingreso, contenido: " + nota.getNota());
            }
            eventoNota.setFechaIngreso(nota.getFechaIngreso() == null ? nota.getFechaIngreso() : LocalDateTime.now());
            eventoNota.setEvento(eventoRepository.findById(nota.getIdEvento()).get());
            eventoNota.setNota(nota.getNota());
            eventoNota.setUsuario(usuarioRepository.findById(nota.getIdUsuarioIngreso()).get());

            if (nota.getFechaIngreso() == null) {
                eventoNota.setFechaIngreso(LocalDateTime.now());
            }

            notas.add(eventoNota);
        });

        return notas;
    }

}
