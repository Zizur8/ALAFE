package com.vs.alafe.service;

import com.vs.alafe.model.dto.MovimientoClienteDTO;
import com.vs.alafe.model.entities.*;
import com.vs.alafe.repository.MovimientoRepository;
import jakarta.annotation.PostConstruct;
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
    public Movimiento save(MovimientoClienteDTO movimientoClienteDTO) throws Exception {

        Movimiento movimiento = new Movimiento();

        Optional<Evento> evento = eventoService.findById(movimientoClienteDTO.getIdEvento());
        if (evento.isEmpty()) {
            throw new Exception("Evento no encontrado con id: " + movimientoClienteDTO.getIdEvento());
        }

        Optional<Cliente> cliente = clienteService.findById(movimientoClienteDTO.getIdCliente());
        if (cliente.isEmpty()) {
            throw new Exception("Cliente no encontrado con id: " + movimientoClienteDTO.getIdCliente());
        }
        Optional<TipoMoneda> moneda = tipoMonedaService.findById(movimientoClienteDTO.getIdTipoMoneda());
        Optional<TipoOperacionMovimiento> tipoOperacionMovimiento = tipoOperacionMovimientoService.findById(movimientoClienteDTO.getIdTipoOperacionMovimiento());

        movimiento.setCliente(cliente.get());
        movimiento.setMoneda(moneda.get());
        movimiento.setMonto(movimientoClienteDTO.getMonto());
        movimiento.setTipoOperacionMovimiento(tipoOperacionMovimiento.get());
        movimiento.setTasaCambio(movimientoClienteDTO.getTasaCambio());
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



}
