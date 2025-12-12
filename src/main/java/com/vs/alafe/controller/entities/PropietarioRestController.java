package com.vs.alafe.controller.entities;

import com.vs.alafe.model.dto.ClienteDTO;
import com.vs.alafe.model.dto.PropietarioDTO;
import com.vs.alafe.model.entities.Propietario;
import com.vs.alafe.service.PropietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alafe/v1")
public class PropietarioRestController {

    @Autowired
    PropietarioService propietarioService;

    @GetMapping("/propietario/{id}")
    public ResponseEntity<PropietarioDTO> findById(@PathVariable Integer id) {

        return propietarioService.findById(id)
                .map(propietario -> ResponseEntity.ok(new PropietarioDTO(propietario)))
                .orElse(ResponseEntity.notFound().build());
    }

}
