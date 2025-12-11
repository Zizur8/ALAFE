package com.vs.alafe.service;

import com.vs.alafe.model.dto.EventoDTO;
import com.vs.alafe.model.entities.*;
import com.vs.alafe.repository.EventoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;
    private final EventoNotaService eventoNotaService;
    private final AgendaService agendaService;

    public EventoService(EventoRepository eventoRepository, ClienteService clienteService, UsuarioService usuarioService,
                         EventoNotaService eventoNotaService, AgendaService agendaService) {
        this.eventoRepository = eventoRepository;
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
        this.eventoNotaService = eventoNotaService;
        this.agendaService = agendaService;
    }

    @Transactional
    public Evento save(Evento evento) {

        System.out.println("Mi evento  depsues del cotnroller antes del service: " + evento.toString());
        Usuario usuarioSession = usuarioService.findById(1)
                .orElseThrow(() -> new RuntimeException("usuario session no encontrado"));

        if (evento.getCliente() != null) {
            Cliente cliente = clienteService.findById(evento.getCliente().getIdCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        }

        Usuario ingreso = usuarioService.findById(evento.getUsuarioIngreso().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario ingreso no encontrado"));

        if (evento.getUsuarioIngreso() == null || evento.getUsuarioIngreso().getIdUsuario() == null) {
            try{
                evento.setUsuarioIngreso(usuarioSession);
            } catch (Exception e) {
                throw new RuntimeException("Usuario ingreso es obligatorio");
            }
        }

        if (evento.getUsuarioModificacion() != null && evento.getUsuarioModificacion().getIdUsuario() != null) {
            Usuario modificacion = usuarioService.findById(evento.getUsuarioModificacion().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario modificación no encontrado"));
        } else {
            evento.setUsuarioModificacion(null);
        }

        evento.setFechaAlta(LocalDateTime.now());
        evento.setFechaUltimaModificacion(LocalDateTime.now());
        evento.setEliminado(false);
        if (evento.getNotas() != null) {
            evento.getNotas().forEach(nota -> {
                nota.setEvento(evento);
                nota.setUsuario(usuarioSession);
                System.out.println("Nota:" + nota);
                //EventoNota eventoNota = eventoNotaService.save(nota);
            });
        }


        return eventoRepository.save(evento);
    }



    @Transactional
    public Evento update(Evento evento) {

        Usuario usuarioSession = usuarioService.findById(1)
                .orElseThrow(() -> new RuntimeException("usuario session no encontrado"));

        Evento eventoExistente = eventoRepository.findById(evento.getIdEvento())
                .orElseThrow(() -> new RuntimeException("Evento no existente para actualizar."));

        evento.setCliente(eventoExistente.getCliente());
        evento.setFechaAlta(eventoExistente.getFechaAlta());
        evento.setEliminado(eventoExistente.getEliminado());
        evento.setFechaUltimaModificacion(LocalDateTime.now());
        evento.setUsuarioModificacion(usuarioSession);

        return eventoRepository.save(evento);
    }

    public Evento toEntity(EventoDTO eventoDTO) {
        System.out.println(eventoDTO.toString());
        Evento evento = new Evento(eventoDTO);

        Usuario usuarioSession = usuarioService.findById(1)
                .orElseThrow(() -> new RuntimeException("usuario session no encontrado"));

        if (eventoDTO.getIdCliente() != null) {
            Cliente cliente = clienteService.findById(eventoDTO.getIdCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            evento.setCliente(cliente);
        }

        if (eventoDTO.getIdEvento() != null) {
            boolean eventoExistente = eventoRepository.existsById(eventoDTO.getIdEvento());
            if (!eventoExistente) {
                throw new IllegalArgumentException("El id evento no existe.");
            }
            evento.setIdEvento(eventoDTO.getIdEvento());
        }

        Agenda agenda = agendaService.findById(1).orElseThrow(() ->  new RuntimeException("Agenda referencia no encontrada"));

        evento.setAgenda(agenda);
        evento.setNotas(eventoNotaService.toEventoNotasListado(eventoDTO.getNotas()));
        evento.setHorarioInicio(eventoDTO.getHorarioInicio());
        evento.setHorarioFinal(eventoDTO.getHorarioFinal());
        evento.setDecoracion(eventoDTO.getDecoracion());
        if (evento.getDecoracion()) {
            System.out.println("es deocracioooon");
            if (eventoDTO.getHorarioDecoracion() == null) {
                System.out.println("errororooasdasd");
                throw new IllegalArgumentException("Horario decoracion no fue ingresado cuando el campo decoracion se encuentra marcado.");
            }
            evento.setHorarioDecoracion(eventoDTO.getHorarioDecoracion());
        }
        else {
            evento.setHorarioDecoracion(null);
        }
        evento.setCosto(eventoDTO.getCosto());
        evento.setEspecial(eventoDTO.getEspecial());
        evento.setHorasExtras(eventoDTO.getHorasExtras());
        evento.setCostoHoraExtra(eventoDTO.getCostoHoraExtra());
        if (evento.getUsuarioIngreso() == null) {
            evento.setUsuarioIngreso(usuarioSession);
        }
        return evento;
    }



    @Transactional()
    public Evento eliminarEvento(Evento evento) {
        boolean movimientosRegistrados = eventoRepository.existsByMovimientos_Evento_IdEvento(evento.getIdEvento());
        System.out.println(movimientosRegistrados + "asdasda---");

        if (!movimientosRegistrados) {
            //Eliminacion fisica
            eventoRepository.delete(evento);
        }
        else {
            //Eliminacion logica
            evento.setEliminado(true);
            eventoRepository.save(evento);
        }

        return evento;
    }

    @Transactional(readOnly = true)
    public Optional<Evento> findById(Integer id) {
        return eventoRepository.findById(id);
    }

    @Transactional
    public void     delete(Evento evento) {
        eventoRepository.delete(evento);
    }

    @Transactional(readOnly = true)
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Evento> findEventosEnRango(LocalDateTime horarioInicio, LocalDateTime horarioFin) {
        System.out.println(horarioInicio);
        System.out.println(horarioFin);
        return eventoRepository.findEventosEnRango(horarioInicio,horarioFin);
    }

    public List<Evento> findEventosDecoracion(Boolean decoracion) {
        return eventoRepository.findEventosDecoracion(decoracion);
    }

    public boolean existsById(Integer idEvento) {
        if (idEvento == null) {
            throw new IllegalArgumentException("El idEvento no puede ser nulo");
        }
        return eventoRepository.existsById(idEvento);
    }

}
