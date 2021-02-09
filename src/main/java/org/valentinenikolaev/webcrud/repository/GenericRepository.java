package org.valentinenikolaev.webcrud.repository;

public interface GenericRepository<T,L> {
    T add(T entity);

    T get(L entityID);

    boolean remove(T entity);
}
