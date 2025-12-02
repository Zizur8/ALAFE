package com.vs.alafe.service;

import com.vs.alafe.model.entities.Cliente;
import com.vs.alafe.model.entities.Evento;
import com.vs.alafe.model.entities.EventoNota;
import com.vs.alafe.model.entities.Usuario;
import com.vs.alafe.repository.EventoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;

    public EventoService(EventoRepository eventoRepository, ClienteService clienteService, UsuarioService usuarioService) {
        this.eventoRepository = eventoRepository;
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
    }


    @Transactional
    public Evento save(Evento evento) {
        Usuario usuarioSession = usuarioService.findById(1)
                .orElseThrow(() -> new RuntimeException("usuario session no encontrado"));

        Cliente cliente = clienteService.findById(evento.getCliente().getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));


        if (evento.getCliente().getColonia() == null) {
            evento.getCliente().setColonia(cliente.getColonia());
        }

        //BeanUtils.copyProperties(evento.getCliente(), cliente, "idCliente");
        clienteService.save(cliente);
        evento.setCliente(cliente);



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

    @Transactional(readOnly = true)
    public Optional<Evento> findById(Integer id) {
        return eventoRepository.findById(id);
    }

    @Transactional
    public void delete(Evento evento) {
        eventoRepository.delete(evento);
    }

    @Transactional(readOnly = true)
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Evento> findByDate(LocalDateTime horarioInicio, LocalDateTime horarioFin) {
        return eventoRepository.findByHorarioInicioBetween(horarioInicio,horarioFin);
    }

    public List<Evento> findEventosDecoracion(Boolean decoracion) {
        return eventoRepository.findEventosDecoracion(decoracion);
    }


}
