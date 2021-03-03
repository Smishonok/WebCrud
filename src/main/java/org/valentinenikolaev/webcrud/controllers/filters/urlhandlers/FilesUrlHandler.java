package org.valentinenikolaev.webcrud.controllers.filters.urlhandlers;

import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FilesUrlHandler extends AbstractURLHandler {
    private final String FILES_REQUEST_URL = ".*/users/\\d+/files";

    @Override
    public void checkAndHandle(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        Pattern pattern = Pattern.compile(FILES_REQUEST_URL);
        String requestUrl = ((HttpServletRequest) request).getRequestURI();
        Matcher matcher = pattern.matcher(requestUrl);
        if (matcher.matches()) {
            matcher.reset().usePattern(Pattern.compile("\\d+"));
            matcher.find();
            String userId = matcher.group(0);
            request.getParameterMap().put("user_id", new String[]{userId});
            request.getRequestDispatcher("/files").forward(request, response);
        } else {
            sendToNextHandler(request, response);
        }

    }
}
