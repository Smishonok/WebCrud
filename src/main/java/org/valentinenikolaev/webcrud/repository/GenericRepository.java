package org.valentinenikolaev.webcrud.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T,L> {
    Optional<T> add(T entity);

    Optional<T> get(L id);

    boolean remove(L id);

    List<T> getAll();
}
