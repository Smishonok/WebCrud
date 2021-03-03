package org.valentinenikolaev.webcrud.controllers.filters.urlhandlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


class UserUrlHandlerTest {

    @Test
    @DisplayName("When request url has user id using format /user/id then return id as request param")
    public void check_testReturnedRequest() throws ServletException, IOException {
        //Given
        HttpServletRequest requestStub = Mockito.mock(HttpServletRequest.class);
        ServletResponse responseStub = Mockito.mock(ServletResponse.class);
        String requestUri = "webApp/users/456";
        Map<String, String[]> parameterMap = new HashMap<>();

        Mockito.when(requestStub.getRequestURI()).thenReturn(requestUri);
        Mockito.when(requestStub.getParameterMap()).thenReturn(parameterMap);

        //When
        UserUrlHandler urlHandler = new UserUrlHandler();
        urlHandler.check(requestStub, responseStub);

        //Then
        Assertions.assertAll(
                () -> assertThat(parameterMap.containsKey("user_id")).isTrue(),
                () -> assertThat(parameterMap.get("user_id")).contains("456")
        );
    }


}