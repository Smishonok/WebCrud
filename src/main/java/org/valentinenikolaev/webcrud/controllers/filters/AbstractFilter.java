package org.valentinenikolaev.webcrud.controllers.filters;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public abstract class AbstractFilter implements Filter {

    protected final String POST_METHOD   = "POST";
    protected final String GET_METHOD    = "GET";
    protected final String DELETE_METHOD = "DELETE";
    protected final String PUT_METHOD    = "PUT";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        AnnotationConfigApplicationContext webContext = (AnnotationConfigApplicationContext) filterConfig.getServletContext()
                                                                                                         .getAttribute(
                                                                                                                 "webContext");
        webContext.getAutowireCapableBeanFactory()
                  .autowireBean(this);
    }

    @Override
    public abstract void doFilter(ServletRequest request, ServletResponse response,
                                  FilterChain chain) throws IOException, ServletException;
}
