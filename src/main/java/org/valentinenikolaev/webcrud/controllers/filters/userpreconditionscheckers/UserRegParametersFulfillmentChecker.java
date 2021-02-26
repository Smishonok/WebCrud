package org.valentinenikolaev.webcrud.controllers.filters.userpreconditionscheckers;

import org.valentinenikolaev.webcrud.controllers.filters.CheckResult;
import org.valentinenikolaev.webcrud.controllers.filters.ConditionChecker;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserRegParametersFulfillmentChecker extends ConditionChecker {
    @Override
    public CheckResult checkCondition(ServletRequest request) {
        Set<String> fulfilledParameters = request.getParameterMap().keySet();
        List<String> userRegParameters = getRegParameters();
        boolean isRegParamsFulFilled = fulfilledParameters.containsAll(userRegParameters);
        if (isRegParamsFulFilled) {
            return checkNext(request);
        } else {
            StringBuilder parametersNotFulfilled= new StringBuilder("Next fields not fulfilled:\n");
            userRegParameters.stream()
                             .filter(p -> !fulfilledParameters.contains(p))
                             .forEach(f -> parametersNotFulfilled.append(f).append(",").append("\n"));
            return new CheckResult(parametersNotFulfilled.toString());
        }
    }

    private List<String> getRegParameters() {
        List<String> regParams = new ArrayList<>();
        regParams.add("firstName");
        regParams.add("lastName");
        regParams.add("birthday");
        regParams.add("login");
        regParams.add("password");
        return regParams;
    }
}
