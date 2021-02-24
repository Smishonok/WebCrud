package org.valentinenikolaev.webcrud.controllers.errorhandlers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class ErrorHandler extends HttpServlet {


    protected void printError(HttpServletResponse response, String message, int statusCode) throws ServletException, IOException {
        String docType = "<!DOCTYPE html>";
        String title = "<title>Web CRUD error page</title>";

        PrintWriter writer = response.getWriter();
        writer.println(docType + "<html>" + "<head>" + title + "</head><body>");
        writer.println("<h1>ERROR</h1>");
        writer.println("<h2>" + message + "</h2>");
        writer.println("<h2>Status code: "+statusCode+"</h2>");
        writer.println("</body></html>");
    }
}
