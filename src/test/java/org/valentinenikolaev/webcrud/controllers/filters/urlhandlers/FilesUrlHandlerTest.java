package org.valentinenikolaev.webcrud.controllers.filters.urlhandlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FilesUrlHandlerTest {

    @Test
    @DisplayName("When came request with url like */users/id/files then set user_id in requestParameter equals id from url")
    public void handleUrlRequestWithUserId() throws ServletException, IOException {
        //given
        HttpServletRequest requestStub = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse responseStub = Mockito.mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcherStub = Mockito.mock(RequestDispatcher.class);

        Map<String, String[]> requestPramMap = new HashMap<>();
        Mockito.when(requestStub.getRequestURI()).thenReturn("app/users/456/files");
        Mockito.when(requestStub.getParameterMap()).thenReturn(requestPramMap);
        Mockito.when(requestStub.getRequestDispatcher(Mockito.any())).thenReturn(requestDispatcherStub);

        FilesUrlHandler urlHandler = new FilesUrlHandler();

        //when
        urlHandler.checkAndHandle(requestStub, responseStub);

        //then
        Assertions.assertAll(
                () -> assertThat(requestPramMap.containsKey("user_id")).isTrue(),
                () -> assertThat(requestPramMap.values()
                                               .stream()
                                               .flatMap(Stream::of)
                                               .collect(Collectors.toList())).contains("456")
        );




    }


}