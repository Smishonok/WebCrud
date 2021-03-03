package org.valentinenikolaev.webcrud.controllers.filters.urlhandlers;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public abstract class AbstractURLHandler {
    private AbstractURLHandler nextHandler;

    public AbstractURLHandler addNext(AbstractURLHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }

    public abstract void checkAndHandle(ServletRequest request, ServletResponse response) throws ServletException, IOException;

    public void sendToNextHandler(ServletRequest request, ServletResponse response) {
        if (nextHandler != null) {
            nextHandler.sendToNextHandler(request,response);
        }
    }
}
