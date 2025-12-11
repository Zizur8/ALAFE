package com.vs.alafe.service;

import com.vs.alafe.model.dto.ClienteDTO;
import com.vs.alafe.model.entities.Cliente;
import com.vs.alafe.model.entities.Propietario;
import com.vs.alafe.repository.ClienteRepository;
import com.vs.alafe.repository.PropietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PropietarioRepository propietarioRepository;

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

//        Optional<Propietario> propietario = propietarioRepository.findById(1);
//        Cliente cliente = new Cliente();
//        cliente.setPropietario(propietario.get());
//        cliente.setTelefono(clienteDTO.getTelefono());
//        cliente.setNombre(clienteDTO.getNombre();
//        cliente.setApellidoPaterno(clienteDTO.getApellidoPaterno());
//        cliente.setApellidoMaterno(clienteDTO.getApellidoMaterno());
//        cliente.setColonia();
//        cliente.setNumeroExterior(clienteDTO.getNumeroExterior());
//        cliente.getCalle(clienteDTO.getCalle());
//        if (clienteDTO.)
//        cliente.setFechaAlta();

        Cliente cliente = clienteRepository.findById(clienteDTO.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        return cliente;


    }
}
