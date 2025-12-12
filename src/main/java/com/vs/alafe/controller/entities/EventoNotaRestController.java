package com.vs.alafe.controller.entities;

import com.vs.alafe.model.dto.EventoNotaDTO;
import com.vs.alafe.model.entities.Evento;
import com.vs.alafe.model.entities.EventoNota;
import com.vs.alafe.service.EventoNotaService;
import com.vs.alafe.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alafe/v1")
public class EventoNotaRestController {

    @Autowired
    private EventoNotaService eventoNotaService;
    @Autowired
    private EventoService eventoService;

    @PostMapping("eventoNota")
    public ResponseEntity<EventoNotaDTO> create(@RequestBody EventoNota eventoNota) {

        Evento evento = eventoService.findById(eventoNota.getEvento().getIdEvento())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        eventoNota.setEvento(evento);

        EventoNota guardado = eventoNotaService.save(eventoNota);
        return ResponseEntity.ok(new EventoNotaDTO(guardado));

    }

    @PutMapping("eventoNota/{id}")
    public ResponseEntity<EventoNotaDTO> update(@PathVariable Integer id, @RequestBody EventoNota eventoNota){
        Optional<Evento> existenteEvento = eventoService.findById(eventoNota.getEvento().getIdEvento());
        if (existenteEvento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        eventoNota.setEvento(eventoNota.getEvento());
        eventoNota.setIdEventoNota(id);
        EventoNota guardado = eventoNotaService.save(eventoNota);
        return ResponseEntity.ok(new EventoNotaDTO(guardado));

    }

    @GetMapping("eventoNota/{id}")
    public ResponseEntity<EventoNotaDTO> showById(@PathVariable Integer id){
        Optional<EventoNota> eventoNota = eventoNotaService.findById(id);
        if (eventoNota == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new EventoNotaDTO(eventoNota.orElseThrow()));

    }

    @GetMapping("eventoNota/evento/{idEvento}")
    public ResponseEntity<List<EventoNotaDTO>> showByIdEvento(@PathVariable Integer idEvento) {
        List<EventoNota> eventosNota = eventoNotaService.findByEvento_IdEvento(idEvento);

        if (eventosNota.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<EventoNotaDTO> dtos = eventosNota.stream().map(EventoNotaDTO::new).toList();
        return ResponseEntity.ok(dtos);
    }

}
