package org.valentinenikolaev.webcrud.exceptions;

public class WebCrudException extends RuntimeException{

    public WebCrudException(String message) {
        super(message);
    }

    public WebCrudException(String message, Throwable cause) {
        super(message, cause);
    }
}
