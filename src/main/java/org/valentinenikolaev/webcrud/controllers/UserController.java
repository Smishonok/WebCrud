package org.valentinenikolaev.webcrud.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.valentinenikolaev.webcrud.exceptions.WebCrudException;
import org.valentinenikolaev.webcrud.models.User;
import org.valentinenikolaev.webcrud.repository.UserRepository;
import org.valentinenikolaev.webcrud.utils.beans.RepositoryBeans;
import org.valentinenikolaev.webcrud.utils.jsonconverters.UserConverter;
import org.valentinenikolaev.webcrud.utils.jsonconverters.UsersListConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController extends HttpServlet {

    private Logger log = LogManager.getLogger(UserController.class);
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RepositoryBeans.class);
        userRepository = context.getBean(UserRepository.class);
        super.init();
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    private void registerUserFromHtmlForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        LocalDate birthday = LocalDate.parse(req.getParameter("birthday"), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String login = req.getParameter("login");




    }

    private String getAllUsersList() {
        List<User> users = userRepository.getAll();
        Type type = new TypeToken<List<User>>() {
        }.getType();
        Gson jsonConverter = new GsonBuilder().registerTypeAdapter(type, new UsersListConverter()).create();
        return jsonConverter.toJson(users);
    }

    private String getUserInfo(String userId) {
        Long id;
        try {
            id = Long.parseLong(userId);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Illegal format of user id. User id should be the number.");
        }

        Optional<User> userOptional = userRepository.get(id);
        if (userOptional.isPresent()) {
            Gson jsonConverter = new GsonBuilder().registerTypeAdapter(User.class, new UserConverter()).create();
            return jsonConverter.toJson(userOptional.get());
        } else {
            throw new WebCrudException("User with requested id is not contains in database!");
        }
    }
}
