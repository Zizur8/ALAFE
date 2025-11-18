package com.vs.alafe.service.impl;

import com.vs.alafe.model.dao.ColoniaDao;
import com.vs.alafe.model.entity.Colonia;
import com.vs.alafe.service.IColonia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ColoniaImpl implements IColonia<Colonia> {

    @Autowired
    private ColoniaDao coloniaDao;

    @Transactional
    @Override
    public Colonia save(Colonia object) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Colonia findById(Integer id) {
        return coloniaDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public void delete(Colonia object) {
    }

    @Transactional(readOnly = true)
    @Override
    public List<Colonia> findAll() {
        return coloniaDao.findAll();
    }
}
