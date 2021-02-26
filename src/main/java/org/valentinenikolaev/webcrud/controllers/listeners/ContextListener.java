package org.valentinenikolaev.webcrud.controllers.listeners;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.valentinenikolaev.webcrud.utils.beans.RepositoryBeans;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {

    private static AnnotationConfigApplicationContext ctx;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("webContext",getContext());
    }

    private AnnotationConfigApplicationContext getContext() {
        if (ctx == null) {
            synchronized (ContextListener.class) {
                if (ctx == null) {
                    ctx = new AnnotationConfigApplicationContext(RepositoryBeans.class);
                }
            }
        }
        return ctx;
    }
}
