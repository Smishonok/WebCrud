package org.valentinenikolaev.webcrud.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.valentinenikolaev.webcrud.exceptions.WebCrudException;
import org.valentinenikolaev.webcrud.models.Account;
import org.valentinenikolaev.webcrud.models.AccountStatus;
import org.valentinenikolaev.webcrud.models.User;
import org.valentinenikolaev.webcrud.repository.UserRepository;
import org.valentinenikolaev.webcrud.utils.PasswordHandler;
import org.valentinenikolaev.webcrud.utils.jsonconverters.UserConverter;
import org.valentinenikolaev.webcrud.utils.jsonconverters.UsersListConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
public class UserController extends AbstractController {

    private Logger log = LogManager.getLogger(UserController.class);

    private final UserRepository  userRepository;
    private final PasswordHandler passwordHandler;

    @Autowired
    public UserController(UserRepository userRepository, PasswordHandler passwordHandler) {
        this.userRepository  = userRepository;
        this.passwordHandler = passwordHandler;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String dataForResponse;
        if (req.getParameterMap().containsKey("user_id")) {
            dataForResponse = getUserInfo(req.getParameter("user_id"));
        } else {
            dataForResponse = getAllUsersList();
        }
        resp.setContentType("application/json");
        resp.getWriter().println(dataForResponse);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = getUserFromRequest(req);
        boolean isUserRegistered = userRepository.add(user).isPresent();

        resp.setContentType("text/html");

        String message;
        if (isUserRegistered) {
            message = "<h1>User added</h1>";
        } else {
            message = "<h1>Unknown error. User not added</h1>";
        }

        resp.getWriter().println(setResponseMessage(message));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = getUserFromRequest(req);
        boolean isUserChanged = userRepository.change(user).isPresent();
        String message;
        if (isUserChanged) {
            message = "<h1>User changed</h1>";
        } else {
            message = "<h1>Unknown error. User not changed</h1>";
        }

        resp.getWriter().println(setResponseMessage(message));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        boolean isRemoved = false;
        if (req.getParameterMap().containsKey("user_id")) {
            Long userId = Long.parseLong(req.getParameter("user_id"));
            isRemoved = userRepository.remove(userId);
        }

        if (isRemoved) {
            resp.getWriter().println(setResponseMessage("User removed"));
        } else {
            resp.getWriter().println(setResponseMessage("User non removed."));
        }
    }

    private User getUserFromRequest(HttpServletRequest req) throws IOException {
        User user = new User();
        user.setFirstName(req.getParameter("firstName"))
            .setLastName(req.getParameter("lastName"))
            .setBirthday(LocalDate.parse(req.getParameter("birthday"),
                                         DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        Account account = new Account();
        account.setLogin(req.getParameter("login")).setPasswordToken(
                passwordHandler.encode(req.getParameter("password"))).setStatus(
                AccountStatus.ACTIVE);

        account.setUser(user);
        user.setAccount(account);
        return user;
    }

    private String getAllUsersList() {
        List<User> users = userRepository.getAll();
        Type       type  = new TypeToken<List<User>>() {}.getType();
        Gson jsonConverter = new GsonBuilder().registerTypeAdapter(type, new UsersListConverter())
                                              .create();
        return jsonConverter.toJson(users);
    }

    private String getUserInfo(String userId) {
        Long id = Long.parseLong(userId);

        Optional<User> userOptional = userRepository.get(id);
        if (userOptional.isPresent()) {
            Gson jsonConverter = new GsonBuilder().registerTypeAdapter(User.class,
                                                                       new UserConverter())
                                                  .create();
            return jsonConverter.toJson(userOptional.get());
        } else {
            throw new WebCrudException("User with requested id is not contains in database!");
        }
    }

    private String setResponseMessage(String message) {
        String header = "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/css\" charset=\"UTF-8\">\n" +
                "    <title>Users</title>\n" +
                "    <meta name=\"description\" lang=\"ru\" content=\"Приложение по хранению файлов на основе Java сервлетов.\">\n" +
                "    <link rel=\"stylesheet\" href=\"CSS/Index.css\">\n" + "</head><body>";
        String footer = "</body></html>";

        return header + message + footer;
    }

}
