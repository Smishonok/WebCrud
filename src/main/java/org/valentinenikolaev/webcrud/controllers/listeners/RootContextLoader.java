package org.valentinenikolaev.webcrud.controllers.listeners;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.valentinenikolaev.webcrud.utils.beans.ControllersBeans;
import org.valentinenikolaev.webcrud.utils.beans.RepositoryBeans;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class RootContextLoader implements ServletContextListener {

    private static AnnotationConfigApplicationContext rootContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("rootContext",getContext());
    }

    private AnnotationConfigApplicationContext getContext() {
        if (rootContext == null) {
            synchronized (RootContextLoader.class) {
                if (rootContext == null) {
                    rootContext = new AnnotationConfigApplicationContext(RepositoryBeans.class);
                    rootContext.register(ControllersBeans.class);
                    rootContext.refresh();
                }
            }
        }
        return rootContext;
    }
}
