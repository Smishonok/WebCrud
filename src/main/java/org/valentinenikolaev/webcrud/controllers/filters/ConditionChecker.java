package org.valentinenikolaev.webcrud.controllers.filters;

import javax.servlet.ServletRequest;

public abstract class ConditionChecker {

    private ConditionChecker nextChecker;

    public ConditionChecker addNext(ConditionChecker checker) {
        nextChecker = checker;
        return checker;
    }

    public abstract CheckResult checkCondition(ServletRequest request);

    protected CheckResult checkNext(ServletRequest request) {
        if (nextChecker != null) {
            return nextChecker.checkCondition(request);
        } else {
            return new CheckResult(true);
        }
    }
}
