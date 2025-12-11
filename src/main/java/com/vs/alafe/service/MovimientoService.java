package com.vs.alafe.service;

import com.vs.alafe.model.dto.MovimientoClienteDTO;
import com.vs.alafe.model.dto.MovimientoNuevoDTO;
import com.vs.alafe.model.entities.*;
import com.vs.alafe.repository.MovimientoRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MovimientoService {


    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EventoService eventoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private TipoMonedaService tipoMonedaService;
    @Autowired
    private TipoOperacionMovimientoService tipoOperacionMovimientoService;

    private Usuario usuarioSession;

    @PostConstruct
    public void init() {
        usuarioSession = usuarioService.findById(1)
                .orElseThrow(() -> new RuntimeException("usuario session no encontrado"));
    }

    @Transactional
    public Movimiento save(MovimientoNuevoDTO movimientoNuevoDTO) {

        Movimiento movimiento = new Movimiento();

        Optional<Evento> evento = eventoService.findById(movimientoNuevoDTO.getIdEvento());
        if (evento.isEmpty()) {
            throw new IllegalArgumentException("Evento no encontrado con id: " + movimientoNuevoDTO.getIdEvento());
        }

        Optional<Cliente> cliente = clienteService.findById(movimientoNuevoDTO.getIdCliente());
        if (cliente.isEmpty()) {
            throw new IllegalArgumentException("Cliente no encontrado con id: " + movimientoNuevoDTO.getIdCliente());
        }
        Optional<TipoMoneda> moneda = tipoMonedaService.findById(movimientoNuevoDTO.getIdTipoMoneda());
        Optional<TipoOperacionMovimiento> tipoOperacionMovimiento = tipoOperacionMovimientoService.findById(movimientoNuevoDTO.getIdTipoOperacionMovimiento());

        movimiento.setCliente(cliente.get());
        movimiento.setMoneda(moneda.get());
        movimiento.setMonto(movimientoNuevoDTO.getMonto());
        movimiento.setTipoOperacionMovimiento(tipoOperacionMovimiento.get());
        movimiento.setTasaCambio(movimientoNuevoDTO.getTasaCambio());
        movimiento.setEvento(evento.get());
        movimiento.setUsuario(usuarioSession);

        Integer maxSecuencial = movimientoRepository.findMaxSecuencial(movimiento.getEvento().getIdEvento());
        movimiento.setSecuencial(maxSecuencial + 1);

        System.out.println(movimiento.toString());
        movimiento.setFecha(LocalDateTime.now());
        return movimientoRepository.save(movimiento);

    }

    @Transactional(readOnly = true)
    public Optional<Movimiento> findById(Integer id) {
        return movimientoRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByEvento_IdEvento(Integer idEvento) {
        if (idEvento == null) {
            throw new IllegalArgumentException("El idEvento no puede ser nulo");
        }
        // Primero valida que el evento exista
        if (!eventoService.existsById(idEvento)) {
            throw new EntityNotFoundException("No existe el evento con id " + idEvento);
        }
        return movimientoRepository.existsByEvento_IdEvento(idEvento);
    }

}
