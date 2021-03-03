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
public class UserUrlHandler extends AbstractURLHandler {

    private final String USER_REQUEST_URL = ".*/users/\\d+";

    @Override
    public void checkAndHandle(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        Pattern pattern = Pattern.compile(USER_REQUEST_URL);
        String requestUrl = ((HttpServletRequest) request).getRequestURI();
        Matcher matcher = pattern.matcher(requestUrl);
        if (matcher.matches()) {
            Pattern idFormat = Pattern.compile("\\d+");
            matcher.reset().usePattern(idFormat).find();
            String userId = requestUrl.substring(matcher.start(), matcher.end());
            request.getParameterMap().put("user_id", new String[]{userId});
            request.getRequestDispatcher("/users").forward(request, response);
        } else {
            sendToNextHandler(request, response);
        }
    }
}
