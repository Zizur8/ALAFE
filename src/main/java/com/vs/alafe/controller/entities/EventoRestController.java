package com.vs.alafe.controller.entities;

import com.vs.alafe.model.dto.EventoDTO;
import com.vs.alafe.model.entities.Cliente;
import com.vs.alafe.model.entities.Evento;
import com.vs.alafe.model.entities.Usuario;
import com.vs.alafe.service.ClienteService;
import com.vs.alafe.service.EventoService;
import com.vs.alafe.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alafe/v1")
public class EventoRestController {

    @Autowired
    private EventoService eventoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("evento/{id}")
    public ResponseEntity<EventoDTO> showById(@PathVariable Integer id) {
        Optional<Evento> evento = eventoService.findById(id);
        if (evento == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new EventoDTO(evento.orElseThrow()));
    }

    @GetMapping("evento")
    public ResponseEntity<List<EventoDTO>> getEventos(
            @RequestParam(value = "horarioInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime horarioInicio,
            @RequestParam(value = "horarioFin", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime horarioFin
    ) {
        List<Evento> eventos = (horarioInicio != null && horarioFin != null)
                ? eventoService.findByDate(horarioInicio, horarioFin)
                : eventoService.findAll();

        List<EventoDTO> dtos = eventos.stream().map(EventoDTO::new).toList();

        return ResponseEntity.ok(dtos);
    }

    @PostMapping("evento")
    public ResponseEntity<EventoDTO> create(@RequestBody Evento evento) {
        System.out.println("Mi evento post: " + evento.toString());
        evento.setFechaIngreso(LocalDateTime.now());
        Evento guardado = eventoService.save(evento);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new EventoDTO(guardado));
    }
//    @PostMapping("evento")
//    public ResponseEntity<EventoDTO> create(@RequestBody Evento evento) {
//        // relaciones obligatorias
//        Cliente cliente = clienteService.findById(evento.getIdCliente())
//                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
//
//        Usuario usuarioIngreso = usuarioService.findById(evento.getUsuarioIngreso().getIdUsuario())
//                .orElseThrow(() -> new RuntimeException("Usuario ingreso no encontrado"));
//        evento.setUsuarioIngreso(usuarioIngreso);
//
//        if (evento.getUsuarioModificacion() != null && evento.getUsuarioModificacion().getIdUsuario() != null) {
//            Usuario usuarioModificacion = usuarioService.findById(evento.getUsuarioModificacion().getIdUsuario())
//                    .orElseThrow(() -> new RuntimeException("Usuario modificacion no encontrado"));
//            evento.setUsuarioModificacion(usuarioModificacion);
//        }
//
//        evento.setFechaIngreso(LocalDateTime.now());
//        evento.setFechaUltimaModificacion(LocalDateTime.now());
//
//        Evento guardado = eventoService.save(evento);
//        return ResponseEntity.ok(new EventoDTO(guardado));
//    }


//    @PutMapping("evento")
//    public Evento update(@RequestBody Evento evento){
//        Cliente cliente = clienteService.findById(evento.getIdCliente())
//                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
//        evento.setCliente(cliente);
//        Usuario usuario = usuarioService.findById(evento.getUsuarioIngreso().getIdUsuario())
//                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
//        evento.setUsuarioIngreso(usuario);
//        Usuario usuarioModifico = usuarioService.findById(evento.getIdUsuarioModifico())
//                .orElseThrow(() -> new RuntimeException("Usuario modifico no encontrado"));
//        evento.setUsuarioModifico(usuarioModifico);
//
//        return eventoService.save(evento);
//    }
// @PutMapping("evento/{id}")
// public ResponseEntity<Evento> update(@PathVariable Integer id, @RequestBody Evento evento){
//     Optional<Evento> existente = eventoService.findById(id);
//     if (existente.isEmpty()) {
//         return ResponseEntity.notFound().build();
//     }

//     // Reutilizas el existente y actualizas campos
//     Evento actual = existente.get();
//     actual.setHorarioInicio(evento.getHorarioInicio());
//     actual.setHorarioFinal(evento.getHorarioFin());
//     actual.setDecoracion(evento.getDecoracion());
//     actual.setCosto(evento.getCosto());
//     actual.setEspecial(evento.getEspecial());
//     actual.setGratuito(evento.getGratuito());
//     actual.setCantidadHoraExtra(evento.getCantidadHoraExtra());
//     actual.setCostoHoraExtra(evento.getCostoHoraExtra());
//     actual.setHorarioDecoracion(evento.getHorarioDecoracion());
//     actual.setFechaUltimaModificacion(LocalDateTime.now());

//     // Relaciones
//     Cliente cliente = clienteService.findById(evento.getIdCliente())
//             .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
//     actual.setCliente(cliente);

//     Usuario usuarioIngreso = usuarioService.findById(evento.getUsuarioIngreso().getIdUsuario())
//             .orElseThrow(() -> new RuntimeException("Usuario ingreso no encontrado"));
//     actual.setUsuarioIngreso(usuarioIngreso);

//     if (evento.getUsuarioModifico() != null && evento.getUsuarioModifico().getIdUsuario() != null) {
//         Usuario usuarioModifico = usuarioService.findById(evento.getUsuarioModifico().getIdUsuario())
//                 .orElseThrow(() -> new RuntimeException("Usuario modifico no encontrado"));
//         actual.setUsuarioModifico(usuarioModifico);
//     }


    //     return ResponseEntity.ok(eventoService.save(actual));
// }


    //    @PostMapping("evento")
//    public Evento create(@RequestBody Evento evento){
//        return eventoService.save(evento);
//    }
    @PutMapping("evento/{id}")
    public ResponseEntity<EventoDTO> update(@PathVariable Integer id, @RequestBody Evento evento) {
        System.out.println("Mi evento put: " + evento);

        Evento existente = eventoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        evento.setIdEvento(id);

        if (evento.getUsuarioIngreso() == null || evento.getUsuarioIngreso().getIdUsuario() == null) {
            evento.setUsuarioIngreso(existente.getUsuarioIngreso());
        } else {
            Usuario ingreso = usuarioService.findById(evento.getUsuarioIngreso().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario ingreso no encontrado"));
            evento.setUsuarioIngreso(ingreso);
        }

        Usuario modificacion = usuarioService.findById(evento.getUsuarioModificacion().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario modificación no encontrado"));
        evento.setUsuarioModificacion(modificacion);

        Cliente cliente = clienteService.findById(evento.getCliente().getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

//        BeanUtils.copyProperties(evento.getCliente(), cliente, "idCliente");
        evento.setCliente(cliente);
        evento.setFechaUltimaModificacion(LocalDateTime.now());
        Evento guardado = eventoService.save(evento);
        return ResponseEntity.ok(new EventoDTO(guardado));
    }
//    @PutMapping("evento/{id}")
//    public ResponseEntity<EventoDTO> update(@PathVariable Integer id, @RequestBody Evento evento) {
//        evento.setIdEvento(id);
//
//        System.out.println("Mi evento put" + evento.toString());
//        Optional<Evento> existente = eventoService.findById(id);
//        if (existente.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        if (evento.getUsuarioIngreso() == null) {
//            evento.setUsuarioIngreso(evento.getUsuarioIngreso());
//        } else {
//            Usuario ingreso = usuarioService.findById(evento.getUsuarioIngreso().getIdUsuario())
//                    .orElseThrow(() -> new RuntimeException("Usuario ingreso no encontrado"));
//            evento.setUsuarioIngreso(ingreso);
//        }
//
//
//        if (evento.getUsuarioModificacion() != null && evento.getUsuarioModificacion().getIdUsuario() != null) {
//            Usuario usuarioModificacion = usuarioService.findById(evento.getUsuarioModificacion().getIdUsuario())
//                    .orElseThrow(() -> new RuntimeException("Usuario modificacion no encontrado"));
//
//        }
//        Cliente cliente = clienteService.findById(evento.getCliente().getIdCliente())
//                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
//        BeanUtils.copyProperties(evento.getCliente(), cliente, "idCliente"); // actualiza campos
//        evento.setCliente(cliente);
//
//        evento.setFechaUltimaModificacion(LocalDateTime.now());
//        Evento guardado = eventoService.save(evento);
//        return ResponseEntity.ok(new EventoDTO(guardado));
//    }

    @DeleteMapping("evento/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Optional<Evento> evento = eventoService.findById(id);
        if (evento == null) return ResponseEntity.notFound().build();
        eventoService.delete(evento.orElseThrow());
        return ResponseEntity.noContent().build();
    }


}
