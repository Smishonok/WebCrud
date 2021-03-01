package org.valentinenikolaev.webcrud.controllers.filters.checkers;

import org.springframework.stereotype.Component;
import org.valentinenikolaev.webcrud.controllers.filters.RequestChecker;
import org.valentinenikolaev.webcrud.exceptions.WebCrudException;

import javax.servlet.ServletRequest;

@Component
public class IdFormatChecker extends RequestChecker {

    private Enum<ID_TYPE> idType;

    public enum ID_TYPE {
        user_id, account_id
    }

    @Override
    public boolean check(ServletRequest request) {
        String user_id = request.getParameter(idType.name());

        if (user_id.isEmpty() || user_id.isBlank()) {
            throw new WebCrudException("Id is required but empty.");
        }

        try {
            Long.parseLong(user_id);
        } catch (NumberFormatException e) {
            throw new WebCrudException("Illegal id format. Id should has digital format.");
        }

        return checkNext(request);
    }

    public void setIdType(Enum<ID_TYPE> idType) {
        this.idType = idType;
    }
}
