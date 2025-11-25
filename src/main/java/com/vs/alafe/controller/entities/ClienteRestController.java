package com.vs.alafe.controller.entities;

import com.vs.alafe.model.dto.ClienteDTO;
import com.vs.alafe.model.entities.Cliente;
import com.vs.alafe.model.entities.Colonia;
import com.vs.alafe.service.ClienteService;
import com.vs.alafe.service.ColoniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alafe/v1")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ColoniaService coloniaService;

    @PostMapping("cliente")
    public ResponseEntity<ClienteDTO> create(@RequestBody Cliente cliente){
        Colonia colonia = coloniaService.findById(cliente.getColonia().getIdColonia())
                .orElseThrow(() -> new RuntimeException("Colonia no encontrada"));

        cliente.setFechaAlta(LocalDateTime.now());

        Cliente guardado = clienteService.save(cliente);
        return ResponseEntity.ok(new ClienteDTO(guardado));
    }

    @PutMapping("cliente/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,@RequestBody Cliente cliente){

        Optional<Cliente> findCliente = clienteService.findById(id);
        if (findCliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Colonia colonia = coloniaService.findById(cliente.getColonia().getIdColonia())
                .orElseThrow(() -> new RuntimeException("Colonia no encontrada"));
        cliente.setColonia(colonia);
        System.out.println(id.toString());
        cliente.setIdCliente(id);
        System.out.println(cliente);
        Cliente guardado = clienteService.save(cliente);
        return ResponseEntity.ok(new ClienteDTO(guardado));
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<ClienteDTO> showById(@PathVariable Integer id){
        return clienteService.findById(id)
                .map(cliente -> ResponseEntity.ok(new ClienteDTO(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("cliente/telefono/{telefono}")
    public ResponseEntity<List<ClienteDTO>> showByTelefono(@PathVariable String telefono){
        List<Cliente> clientes = clienteService.findByTelefono(telefono);
        List<ClienteDTO> dtos = clientes.stream().map(ClienteDTO::new).toList();
        return ResponseEntity.ok(dtos);
    }

}
