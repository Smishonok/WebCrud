package org.valentinenikolaev.webcrud.controllers.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.valentinenikolaev.webcrud.controllers.filters.userpreconditionscheckers.UserLoginChecker;
import org.valentinenikolaev.webcrud.controllers.filters.userpreconditionscheckers.UserRegParametersFulfillmentChecker;
import org.valentinenikolaev.webcrud.exceptions.WebCrudException;
import org.valentinenikolaev.webcrud.repository.UserRepository;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserRegParamValidator extends AbstractFilter {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;

        if (!httpReq.getMethod().equals("POST")) {
            chain.doFilter(request, response);
            return;
        }

        ConditionChecker checker = new UserRegParametersFulfillmentChecker();
        checker.addNext(new UserLoginChecker(userRepository));

        CheckResult result = checker.checkCondition(request);
        if (result.isAllConditionsValid()) {
            chain.doFilter(request, response);
        } else {
            throw new WebCrudException(result.getErrorMessage());
        }
    }
}
