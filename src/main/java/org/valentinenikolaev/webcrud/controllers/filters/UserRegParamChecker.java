package org.valentinenikolaev.webcrud.controllers.filters;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRegParamChecker implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Set<String> userParam = request.getParameterMap().keySet();

        List<String> regParameters = getRegParameters();
        boolean isRegParamsFullField = userParam.containsAll(regParameters);

        if (isRegParamsFullField) {
            chain.doFilter(request, response);
        } else {
            StringBuilder notFulfilled= new StringBuilder("Next fields not fulfilled:\n");
            regParameters.stream()
                         .filter(userParam::contains)
                         .forEach(f-> notFulfilled.append(f).append("\n"));
            response.getWriter().println(notFulfilled);
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
