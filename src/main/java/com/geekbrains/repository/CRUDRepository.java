package com.geekbrains.repository;

import java.util.List;

public interface CRUDRepository<K, V> {

    void save(K k);
    void deleteById(V id);
    void update(K k);
    K findById(V id);
    List<K> findAll();

}
