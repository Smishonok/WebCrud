package org.valentinenikolaev.webcrud.controllers.filters.userpreconditionscheckers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.valentinenikolaev.webcrud.controllers.filters.CheckResult;
import org.valentinenikolaev.webcrud.controllers.filters.ConditionChecker;
import org.valentinenikolaev.webcrud.repository.UserRepository;

import javax.servlet.ServletRequest;

@Component
public class UserLoginChecker extends ConditionChecker {

    private UserRepository userRepository;

    public UserLoginChecker(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CheckResult checkCondition(ServletRequest request) {
        String login = request.getParameter("login");

        boolean isUserWithLoginExists = userRepository.isExistUserWithLogin(login);

        if (isUserWithLoginExists) {
            return new CheckResult("User with login: " + login + " exists. Change user login.");
        }
        return checkNext(request);
    }
}
