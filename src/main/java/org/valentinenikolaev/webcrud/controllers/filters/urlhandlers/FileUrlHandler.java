package org.valentinenikolaev.webcrud.controllers.filters.urlhandlers;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FileUrlHandler extends AbstractURLHandler{

    private final String FILE_REQUEST_URL = ".*/users/\\d+/files/\\d+";

    @Override
    public void check(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        Pattern pattern = Pattern.compile(FILE_REQUEST_URL);
        String requestUrl = ((HttpServletRequest) request).getRequestURI();
        Matcher matcher = pattern.matcher(requestUrl);
        if (matcher.find()) {
            Scanner scanner = new Scanner(requestUrl);
            String userId = scanner.next("\\d+");
            String fileId = scanner.next("\\d+");
            request.getParameterMap().put("user_id", new String[]{userId});
            request.getParameterMap().put("file_id", new String[]{fileId});
            request.getRequestDispatcher("/files").forward(request, response);
        } else {
            checkNext(request, response);
        }
    }
}
