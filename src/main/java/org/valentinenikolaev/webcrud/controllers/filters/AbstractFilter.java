package org.valentinenikolaev.webcrud.controllers.filters;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.*;
import java.io.IOException;

public abstract class AbstractFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        AnnotationConfigApplicationContext webContext = (AnnotationConfigApplicationContext) filterConfig.getServletContext()
                                                                                                         .getAttribute("webContext");
        webContext.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public abstract void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException;
}
