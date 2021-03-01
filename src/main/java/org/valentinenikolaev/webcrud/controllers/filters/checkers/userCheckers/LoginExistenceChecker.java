package org.valentinenikolaev.webcrud.controllers.filters.checkers.userCheckers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.valentinenikolaev.webcrud.controllers.filters.RequestChecker;
import org.valentinenikolaev.webcrud.exceptions.WebCrudException;
import org.valentinenikolaev.webcrud.repository.UserRepository;

import javax.servlet.ServletRequest;

@Component
public class LoginExistenceChecker extends RequestChecker {

    private final UserRepository userRepository;

    public LoginExistenceChecker(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean check(ServletRequest request) {
        String login = request.getParameter("login");

        boolean isUserWithLoginExists = userRepository.isExistUserWithLogin(login);

        if (isUserWithLoginExists) {
            throw new WebCrudException("User with login: " + login + " exists. Change user login.");
        }
        return checkNext(request);
    }
}
