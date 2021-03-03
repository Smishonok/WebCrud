package org.valentinenikolaev.webcrud.controllers.filters.checkers;

import javax.servlet.ServletRequest;

public class DefaultChecker extends AbstractRequestChecker {
    @Override
    public boolean check(ServletRequest request) {
        return checkNext(request);
    }
}
