package com.vs.alafe.controller.entities;

import com.vs.alafe.model.dto.AgendaDTO;
import com.vs.alafe.model.dto.EventoNotaDTO;
import com.vs.alafe.model.entities.Agenda;
import com.vs.alafe.model.entities.EventoNota;
import com.vs.alafe.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/alafe/v1")
public class AgendaRestController {

    @Autowired
    AgendaService agendaService;

    @GetMapping("agenda/{id}")
    public ResponseEntity<AgendaDTO> showById(@PathVariable Integer id){
        Optional<Agenda> agenda = agendaService.findById(id);
        if (agenda == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new AgendaDTO(agenda.orElseThrow()));

    }
}
