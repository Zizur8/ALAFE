package com.vs.alafe.service;

import com.vs.alafe.model.dto.ClienteDTO;
import com.vs.alafe.model.entities.Cliente;
import com.vs.alafe.model.entities.Colonia;
import com.vs.alafe.model.entities.Propietario;
import com.vs.alafe.repository.ClienteRepository;
import com.vs.alafe.repository.PropietarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PropietarioService propietarioService;
    @Autowired
    private ColoniaService coloniaService;

    @Transactional
    public Cliente save(Cliente object) {
        return clienteRepository.save(object);
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public void delete(Cliente object) {
        clienteRepository.delete(object);
    }

    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Cliente> findByTelefono(String telefono) {
        return clienteRepository.findByTelefonoContaining(telefono);
    }

    public boolean existsByIdCliente(Integer idCliente) {
        if (idCliente == null) {
            return false;
        }
        return clienteRepository.existsByIdCliente(idCliente);
    }

    public Cliente toEntity(ClienteDTO clienteDTO){

        boolean clienteExiste = false;
        Integer idCliente = clienteDTO.getIdCliente();
        Propietario propietario = propietarioService.findById(1)
                .orElseThrow(() -> new EntityNotFoundException("Propietario no encontrado."));
        System.out.println(propietario.toString());
        Colonia colonia = coloniaService.findById(clienteDTO.getIdColonia())
                .orElseThrow(() -> new EntityNotFoundException("Colonia seleccionada no existe."));
        if (idCliente != null) {
            clienteExiste = clienteRepository.existsByIdCliente(clienteDTO.getIdCliente());
            idCliente = clienteDTO.getIdCliente();
        }

        Cliente cliente = new Cliente();
        cliente.setIdCliente(idCliente);
        cliente.setPropietario(propietario);
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setCorreo(clienteDTO.getCorreo());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellidoPaterno(clienteDTO.getApellidoPaterno());
        cliente.setApellidoMaterno(clienteDTO.getApellidoMaterno());
        cliente.setColonia(colonia);
        cliente.setNumeroExterior(clienteDTO.getNumeroExterior());
        cliente.setCalle(clienteDTO.getCalle());

        if (!clienteExiste) {
            cliente.setFechaAlta(LocalDateTime.now());
        }

        return cliente;


    }
}
