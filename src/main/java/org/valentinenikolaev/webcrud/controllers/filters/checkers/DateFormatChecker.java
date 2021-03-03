package org.valentinenikolaev.webcrud.controllers.filters.checkers;

import org.springframework.stereotype.Component;
import org.valentinenikolaev.webcrud.exceptions.WebCrudException;

import javax.servlet.ServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class DateFormatChecker extends AbstractRequestChecker {
    @Override
    public boolean check(ServletRequest request) {
        try {
            LocalDate.parse(request.getParameter("birthday"),
                            DateTimeFormatter.ofPattern("dd.MM" + ".yyyy"));
        } catch (DateTimeParseException e) {
            throw new WebCrudException("Wrong birthday format. Date format is: dd.mm.yyyy");
        }
        return checkNext(request);
    }
}
