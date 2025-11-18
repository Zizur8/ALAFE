package com.vs.alafe.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityDao<T> extends JpaRepository<T, Integer> {

}
