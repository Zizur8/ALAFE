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
    public ResponseEntity<EventoNotaDTO> create(@RequestBody EventoNotaDTO eventoNotaDTO) {

        Evento evento = eventoService.findById(eventoNotaDTO.getIdEvento())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        EventoNota guardado = eventoNotaService.save(eventoNotaDTO,evento);
        return ResponseEntity.ok(new EventoNotaDTO(guardado));

    }

    @PutMapping("eventoNota/{id}")
    public ResponseEntity<EventoNotaDTO> update(@PathVariable Integer id, @RequestBody EventoNotaDTO eventoNotaDTO){
        Evento existenteEvento = eventoService.findById(eventoNotaDTO.getIdEvento())
                .orElseThrow(() -> new IllegalArgumentException("La nota no cuenta con un evento inexistente."));
        EventoNota guardado = eventoNotaService.save(eventoNotaDTO,existenteEvento);
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
