package com.example.payment.Repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<ID, ENTITY> {
    List<ENTITY> findAll();
    Optional<ENTITY> findById(ID id);
    Optional<ENTITY> create(ENTITY entity);
    Optional<ENTITY> update(ENTITY entity);
    void delete(ID id);


}
