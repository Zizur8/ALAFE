package com.vs.alafe.controller.entitys;

import com.vs.alafe.model.dto.ClienteDTO;
import com.vs.alafe.model.entity.Cliente;
import com.vs.alafe.service.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alafe/v1")
public class ClienteController {

    @Autowired
    private ICliente<Cliente> clienteService;

    @PostMapping("cliente")
    public Cliente create(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @PutMapping("cliente")
    public Cliente update(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }
    @DeleteMapping("cliente/{id}")
    public void delete(@PathVariable Integer id){
        Cliente clienteEliminar = clienteService.findById(id);
        clienteService.delete(clienteEliminar);
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<ClienteDTO> showById(@PathVariable Integer id){
        Cliente cliente = clienteService.findById(id);
        if (cliente == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ClienteDTO(cliente));
    }
}
