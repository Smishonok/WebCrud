package org.valentinenikolaev.webcrud.controllers.filters.checkers;

import org.valentinenikolaev.webcrud.controllers.filters.RequestChecker;

import javax.servlet.ServletRequest;

public class DefaultChecker extends RequestChecker {
    @Override
    public boolean check(ServletRequest request) {
        return checkNext(request);
    }
}
