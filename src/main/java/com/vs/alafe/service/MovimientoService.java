package com.vs.alafe.service;

import com.vs.alafe.model.dto.MovimientoClienteDTO;
import com.vs.alafe.model.dto.MovimientoNuevoDTO;
import com.vs.alafe.model.entities.*;
import com.vs.alafe.repository.EventoNotaRepository;
import com.vs.alafe.repository.EventoRepository;
import com.vs.alafe.repository.MovimientoRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MovimientoService {


    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private EventoRepository eventoRepository;
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


    @Transactional
    public Movimiento save(MovimientoNuevoDTO movimientoNuevoDTO) {

        Movimiento movimiento = new Movimiento();

        Evento evento = eventoService.findById(movimientoNuevoDTO.getIdEvento())
                .orElseThrow(() -> new IllegalArgumentException("Evento no existente."));
        Cliente cliente = clienteService.findById(movimientoNuevoDTO.getIdCliente())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id: " + movimientoNuevoDTO.getIdCliente()));
        TipoMoneda moneda = tipoMonedaService.findById(movimientoNuevoDTO.getIdTipoMoneda())
                .orElseThrow(() -> new IllegalArgumentException("ID Tipo de moneda invalida: " + movimientoNuevoDTO.getIdTipoMoneda()));
        TipoOperacionMovimiento tipoOperacionMovimiento = tipoOperacionMovimientoService.findById(movimientoNuevoDTO.getIdTipoOperacionMovimiento())
                .orElseThrow(() -> new IllegalArgumentException("ID tipo operacion movimiento inexistente: " + movimientoNuevoDTO.getIdTipoOperacionMovimiento().toString()));
        Usuario usuarioSession = usuarioService.findById(movimientoNuevoDTO.getIdUsuarioIngreso())
                .orElseThrow(() -> new IllegalArgumentException("Usuario Ingreso Invalido o inexistente."));
        BigDecimal montoPagos = eventoRepository.calcularMontoPagosEvento(evento.getIdEvento());
        if (montoPagos.equals(evento.getCosto())) {throw new RuntimeException("Pago rechazado: El movimiento ya se encuentra liquidado.");}

        movimiento.setCliente(cliente);
        movimiento.setMoneda(moneda);
        movimiento.setMonto(movimientoNuevoDTO.getMonto());
        movimiento.setTipoOperacionMovimiento(tipoOperacionMovimiento);
        movimiento.setTasaCambio(movimientoNuevoDTO.getTasaCambio());
        movimiento.setEvento(evento);
        movimiento.setUsuario(usuarioSession);
        movimiento.setFecha(LocalDateTime.now());

        Integer maxSecuencial = movimientoRepository.findMaxSecuencial(movimiento.getEvento().getIdEvento());
        movimiento.setSecuencial(maxSecuencial + 1);

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
