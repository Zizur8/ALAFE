package com.vs.alafe.service;

import com.vs.alafe.model.entities.TipoMoneda;
import com.vs.alafe.repository.TipoMonedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class TipoMonedaService {

    @Autowired
    TipoMonedaRepository tipoMonedaRepository;

    @Transactional(readOnly = true)
    public Optional<TipoMoneda> findById(Integer id) {
        return tipoMonedaRepository.findById(id);
    }




}
