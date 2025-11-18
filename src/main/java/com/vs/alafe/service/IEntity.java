package com.vs.alafe.service;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IEntity<T> {
    T save(T object);
    T findById(Integer id);
    void delete(T object);
    List<T> findAll();
}
