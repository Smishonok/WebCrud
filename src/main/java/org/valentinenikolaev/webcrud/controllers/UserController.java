package org.valentinenikolaev.webcrud.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        if (!req.getParameterMap().containsKey("action")) {
            sendUsersList(req, resp);
            return;
        }

        String action = req.getParameter("action");

        switch (action) {
            case "add":
                addUser(req, resp);
                break;
            case "remove":
                removeUser(req, resp);
                break;
            case "change":
                changeUser(req, resp);
                break;
            default:
                sendBadRequestRespons(req, resp);
        }
    }

    private void sendUsersList(HttpServletRequest req, HttpServletResponse resp) {



    }

    private void sendBadRequestRespons(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void changeUser(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void removeUser(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        doGet(req,resp);
    }
}
