package com.oneteam.empsystem.repo.repos;

import java.io.Serializable;
import java.util.List;

public interface GenericRepo<T, PK extends Serializable>{

    T findById(PK id);
    List<T> findAll();
    void save(T entity);
    void update(T entity);
    void remove(T entity);
}
