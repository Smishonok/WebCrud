package org.valentinenikolaev.webcrud.controllers;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class AbstractController extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        AnnotationConfigApplicationContext webContext = (AnnotationConfigApplicationContext) config.getServletContext()
                                                                                                   .getAttribute("webContext");
        webContext.getAutowireCapableBeanFactory().autowireBean(this);
    }
}
