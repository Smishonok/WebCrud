package org.valentinenikolaev.webcrud.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T,L> {
    Optional<T> add(T entity);

    Optional<T> get(L entityID);

    boolean remove(T entity);

    List<T> getAll();
}
