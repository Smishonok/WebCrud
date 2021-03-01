package org.valentinenikolaev.webcrud.utils;

import org.springframework.stereotype.Component;

@Component
public abstract class GenericChecker<T> {

    private GenericChecker nextChecker;

    public GenericChecker addNext(GenericChecker checker) {
        nextChecker = checker;
        return checker;
    }

    public abstract boolean check(T entity);

    protected boolean checkNext(T entity) {
        if (nextChecker != null) {
            return nextChecker.check(entity);
        } else {
            return true;
        }
    }
}
