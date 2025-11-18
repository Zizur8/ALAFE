package com.vs.alafe.controller.entitys;

import com.vs.alafe.model.dto.EventoDTO;
import com.vs.alafe.model.entity.Evento;
import com.vs.alafe.service.IEvento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/alafe/v1")
public class EventoController {

    @Autowired
    private IEvento<Evento> eventoService;

    @PostMapping("evento")
    public Evento create(@RequestBody Evento evento){
        return eventoService.save(evento);
    }

    @PutMapping("evento")
    public Evento update(@RequestBody Evento evento){
        return eventoService.save(evento);
    }

    @DeleteMapping("evento/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Evento evento = eventoService.findById(id);
        if (evento == null) return ResponseEntity.notFound().build();
        eventoService.delete(evento);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("evento/{id}")
    public ResponseEntity<EventoDTO> showById(@PathVariable Integer id){
        Evento evento = eventoService.findById(id);
        if (evento == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new EventoDTO(evento));
    }


    @GetMapping("evento")
    public ResponseEntity<List<EventoDTO>> getEventos(
            @RequestParam(value = "horarioInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime horarioInicio,
            @RequestParam(value = "horarioFin", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime horarioFin
    ){
        List<Evento> eventos = (horarioInicio != null && horarioFin != null)
                ? eventoService.findByDate(horarioInicio, horarioFin)
                : eventoService.findAll();

        List<EventoDTO> dtos = eventos.stream().map(EventoDTO::new).toList();
        return ResponseEntity.ok(dtos);
    }



}
