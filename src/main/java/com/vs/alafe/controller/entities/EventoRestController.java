package com.vs.alafe.controller.entities;

import com.vs.alafe.model.dto.MovimientoClienteDTO;
import com.vs.alafe.model.dto.MovimientoDTO;
import com.vs.alafe.model.dto.MovimientoNuevoDTO;
import com.vs.alafe.model.entities.*;
import com.vs.alafe.repository.MovimientoRepository;
import com.vs.alafe.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.vs.alafe.model.dto.EventoDTO;

@RestController
@RequestMapping("/alafe/v1")
public class EventoRestController {


    private final EventoService eventoService;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;
    private final EventoNotaService eventoNotaService;
    private final AgendaService agendaService;
    private final PropietarioService propietarioService;
    private final ColoniaService coloniaService;
    private final MovimientoService movimientoService;

    public EventoRestController(EventoService eventoService, ClienteService clienteService, UsuarioService usuarioService,
                                EventoNotaService eventoNotaService, AgendaService agendaService, PropietarioService propietarioService,
                                ColoniaService coloniaService, MovimientoService movimientoService) {
        this.eventoService = eventoService;
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
        this.eventoNotaService = eventoNotaService;
        this.agendaService = agendaService;
        this.propietarioService = propietarioService;
        this.coloniaService = coloniaService;
        this.movimientoService = movimientoService;
    }



    @GetMapping("evento/prueba/{decoracion}")
    public ResponseEntity<List<EventoDTO>> showEventosDecoracion(@PathVariable Boolean decoracion) {
        List<Evento> eventos = eventoService.findEventosDecoracion(decoracion);
        if (eventos == null) return ResponseEntity.notFound().build();

        List<EventoDTO> dtos = eventos.stream().map(EventoDTO::new).toList();
        return ResponseEntity.ok((dtos));
    }

    @GetMapping("evento/{id}")
    public ResponseEntity<EventoDTO> showById(@PathVariable Integer id) {
        Optional<Evento> evento = eventoService.findById(id);
        if (evento == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new EventoDTO(evento.orElseThrow()));
    }

    @GetMapping("evento")
    public ResponseEntity<List<EventoDTO>> getEventosCalendario(
            @RequestParam(value = "horarioInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime horarioInicio,
            @RequestParam(value = "horarioFin", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime horarioFin
    ) {
        if (horarioInicio != null) {
            horarioInicio = horarioInicio.withHour(0).withMinute(0).withSecond(0).withNano(0);

        }
        if (horarioFin != null) {
            horarioFin = horarioFin.withHour(23).withMinute(59).withSecond(59).withNano(999_000_000);

        }
        System.out.println(horarioInicio);
        System.out.println(horarioFin);
        List<Evento> eventos = (horarioInicio != null && horarioFin != null)
                ? eventoService.findEventosEnRango(horarioInicio, horarioFin)
                : eventoService.findAll();

        List<EventoDTO> dtos = eventos.stream().map(EventoDTO::new).toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("evento")
    public ResponseEntity<EventoDTO> create(@RequestBody EventoDTO eventoDTO) {

        Evento dtoToEvento = eventoService.toEntity(eventoDTO);
        Evento guardado = eventoService.save(dtoToEvento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new EventoDTO(guardado));
    }

    @PutMapping("evento/{id}")
    public ResponseEntity<EventoDTO> update(@PathVariable Integer id, @RequestBody EventoDTO eventoDTO) {

        Evento dtoToEvento = eventoService.toEntity(eventoDTO);
        Evento eventoGuardado = eventoService.update(dtoToEvento);
//
//        MovimientoNuevoDTO MovimientoNuevoDTO = eventoDTO.getMovimientoNuevoDTO();
//        if (MovimientoNuevoDTO != null) {
//            if (guardado.getCliente() == null) {
//                throw new IllegalArgumentException("Un movimiento debe contar con un cliente");
//            }
//            movimientoNuevoDTO.setIdCliente(evento.getCliente().getIdCliente());
//            movimientoNuevoDTO.setIdEvento(evento.getIdEvento());
//            movimientoNuevoDTO.setIdUsuario(evento.getUsuarioModificacion().getIdUsuario());
//            Movimiento nuevoMovimiento = movimientoService.save(movimientoNuevoDTO);
//
//        }

        return ResponseEntity.ok(new EventoDTO(eventoGuardado));
    }



    public void toEntityEvento(EventoDTO evento){
    }



    @DeleteMapping("evento/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Evento evento = eventoService.findById(id).orElseThrow(() -> new IllegalArgumentException("Evento inexistente pard eliminar"));
        eventoService.eliminarEvento(evento);
        return ResponseEntity.noContent().build();
    }

    private void generarMovimiento(MovimientoClienteDTO movimientoClienteDTO) {


    }

}
