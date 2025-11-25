package com.vs.alafe.service;

import com.vs.alafe.model.entities.Cliente;
import com.vs.alafe.model.entities.Evento;
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

    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Evento save(Evento evento) {

        Cliente cliente = clienteService.findById(evento.getCliente().getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        if (evento.getCliente().getColonia() == null) {
            evento.getCliente().setColonia(cliente.getColonia());
        }

        BeanUtils.copyProperties(evento.getCliente(), cliente, "idCliente");
        clienteService.save(cliente);
        evento.setCliente(cliente);

        if (evento.getUsuarioIngreso() == null || evento.getUsuarioIngreso().getIdUsuario() == null) {
            throw new RuntimeException("Usuario ingreso es obligatorio");
        }

        Usuario ingreso = usuarioService.findById(evento.getUsuarioIngreso().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario ingreso no encontrado"));
        evento.setUsuarioIngreso(ingreso);

        if (evento.getUsuarioModificacion() != null && evento.getUsuarioModificacion().getIdUsuario() != null) {
            Usuario modificacion = usuarioService.findById(evento.getUsuarioModificacion().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario modificación no encontrado"));
        } else {
            evento.setUsuarioModificacion(null);
        }

        evento.setFechaIngreso(LocalDateTime.now());
        evento.setFechaUltimaModificacion(LocalDateTime.now());

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


}
