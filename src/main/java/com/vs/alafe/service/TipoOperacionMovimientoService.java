package com.vs.alafe.service;

import com.vs.alafe.model.entities.TipoMoneda;
import com.vs.alafe.model.entities.TipoOperacionMovimiento;
import com.vs.alafe.repository.TipoMonedaRepository;
import com.vs.alafe.repository.TipoOperacionMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TipoOperacionMovimientoService {

    @Autowired
    TipoOperacionMovimientoRepository tipoOperacionMovimientoRepository;

    @Transactional(readOnly = true)
    public Optional<TipoOperacionMovimiento> findById(Integer id) {
        return tipoOperacionMovimientoRepository.findById(id);
    }
}
