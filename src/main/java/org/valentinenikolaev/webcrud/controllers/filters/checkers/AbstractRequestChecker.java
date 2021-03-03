package org.valentinenikolaev.webcrud.controllers.filters.checkers;

import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;

@Component
public abstract class AbstractRequestChecker {
    private AbstractRequestChecker nextChecker;

    public AbstractRequestChecker addNext(AbstractRequestChecker checker) {
        nextChecker = checker;
        return checker;
    }

    public abstract boolean check(ServletRequest entity);

    protected boolean checkNext(ServletRequest entity) {
        if (nextChecker != null) {
            return nextChecker.check(entity);
        } else {
            return true;
        }
    }
}
