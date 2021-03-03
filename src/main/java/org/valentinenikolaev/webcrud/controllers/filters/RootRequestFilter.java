package org.valentinenikolaev.webcrud.controllers.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.valentinenikolaev.webcrud.controllers.filters.urlhandlers.AbstractURLHandler;
import org.valentinenikolaev.webcrud.controllers.filters.urlhandlers.FileUrlHandler;
import org.valentinenikolaev.webcrud.controllers.filters.urlhandlers.FilesUrlHandler;
import org.valentinenikolaev.webcrud.controllers.filters.urlhandlers.UserUrlHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
public class RootRequestFilter extends AbstractFilter {

    private FileUrlHandler fileUrlHandler;
    private UserUrlHandler userUrlHandler;
    private FilesUrlHandler filesUrlHandler;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        AbstractURLHandler urlHandler = userUrlHandler;
        //urlHandler.addNext(userUrlHandler).addNext(filesUrlHandler);

        urlHandler.checkAndHandle(request, response);

//        chain.doFilter(request, response);
    }

    @Autowired
    public void setFileUrlHandler(FileUrlHandler fileUrlHandler) {
        this.fileUrlHandler = fileUrlHandler;
    }

    @Autowired
    public void setUserUrlHandler(UserUrlHandler userUrlHandler) {
        this.userUrlHandler = userUrlHandler;
    }

    @Autowired
    public void setFilesUrlHandler(FilesUrlHandler filesUrlHandler) {
        this.filesUrlHandler = filesUrlHandler;
    }
}
