package com.vs.alafe.controller.entities;

import com.vs.alafe.model.dto.MovimientoClienteDTO;
import com.vs.alafe.model.dto.MovimientoDTO;
import com.vs.alafe.model.dto.MovimientoNuevoDTO;
import com.vs.alafe.model.entities.*;
import com.vs.alafe.repository.MovimientoRepository;
import com.vs.alafe.service.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/alafe/v1")
public class MovimientoRestController {


    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private MovimientoService movimientoService;
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


    @GetMapping("/movimiento/{id}")
    public ResponseEntity<MovimientoDTO> getMovimientoById(@PathVariable Integer id) {
        Optional<Movimiento> movimiento = movimientoService.findById(id);
        if (movimiento == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new MovimientoDTO(movimiento.orElseThrow()));
    }

    @PostMapping("/movimiento")
    public ResponseEntity<MovimientoDTO> guardarMovimiento(@RequestBody MovimientoNuevoDTO movimientoNuevoDTO) throws Exception {

        Movimiento nuevoMovimiento = movimientoService.save(movimientoNuevoDTO);
        // Devuelve 201 Created con el DTO
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MovimientoDTO(nuevoMovimiento));
    }


}
