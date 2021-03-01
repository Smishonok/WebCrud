package org.valentinenikolaev.webcrud.controllers.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.valentinenikolaev.webcrud.controllers.filters.checkers.DefaultChecker;
import org.valentinenikolaev.webcrud.controllers.filters.checkers.IdFormatChecker;
import org.valentinenikolaev.webcrud.controllers.filters.checkers.DateFormatChecker;
import org.valentinenikolaev.webcrud.controllers.filters.checkers.userCheckers.LoginExistenceChecker;
import org.valentinenikolaev.webcrud.controllers.filters.checkers.userCheckers.RegParametersFulfillmentChecker;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class UserParamValidator extends AbstractFilter {

    private RegParametersFulfillmentChecker regParametersFulfillmentChecker;
    private LoginExistenceChecker           loginExistenceChecker;
    private DateFormatChecker               dateFormatChecker;
    private IdFormatChecker                 idFormatChecker;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;

        if (httpReq.getMethod().equals(GET_METHOD) && ! httpReq.getParameterMap().containsKey(
                "user_id")) {
            chain.doFilter(request, response);
            return;
        }

        RequestChecker checker = new DefaultChecker();
        switch (httpReq.getMethod()) {
            case GET_METHOD:
            case DELETE_METHOD:
                checker.addNext(idFormatChecker);
                break;
            case PUT_METHOD:
                checker.addNext(regParametersFulfillmentChecker).addNext(idFormatChecker).addNext(
                        dateFormatChecker);
                break;
            case POST_METHOD:
                checker.addNext(regParametersFulfillmentChecker)
                       .addNext(loginExistenceChecker)
                       .addNext(idFormatChecker)
                       .addNext(dateFormatChecker);
                break;
            default:
                chain.doFilter(request, response);
                return;
        }
        checker.check(request);
        chain.doFilter(request, response);
    }

    @Autowired (required = true)
    public void setRegParametersFulfillmentChecker(
            RegParametersFulfillmentChecker regParametersFulfillmentChecker) {
        this.regParametersFulfillmentChecker = regParametersFulfillmentChecker;
    }

    @Autowired (required = true)
    public void setDateFormatChecker(DateFormatChecker dateFormatChecker) {
        this.dateFormatChecker = dateFormatChecker;
    }

    @Autowired (required = true)
    public void setIdFormatChecker(IdFormatChecker idFormatChecker) {
        this.idFormatChecker = idFormatChecker;
        this.idFormatChecker.setIdType(IdFormatChecker.ID_TYPE.user_id);
    }

    @Autowired
    public void setLoginExistenceChecker(LoginExistenceChecker loginExistenceChecker) {
        this.loginExistenceChecker = loginExistenceChecker;
    }
}
