package main.controller;

import main.UserDB.UserDB;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserDB db;

    @Override
    public void init(ServletConfig config) throws ServletException {
        db = UserDB.getInstance();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        final HttpSession session = req.getSession(true);
        session.setAttribute("login", login);

        PrintWriter writer = resp.getWriter();

      //  String colorText = "<div style=\"color:Red;\"text-align: left;\">";
        if (login.isEmpty()) {
            writer.println(
                    "Login is empty");
            req.getRequestDispatcher("/").include(req, resp);
        } else if (password.isEmpty()) {
            writer.print(
                    "Password is empty");
            req.getRequestDispatcher("/").include(req, resp);
        } else if (db.getUserByType(login, password).isEmpty()) {
            writer.print(
                    "Login/Password incorrect. Try again.");
        } else if (db.getUserByType(login, password).isPresent()) {
            req.setAttribute("users", db.getMemoryDb());
            resp.sendRedirect("/users");
        }
        writer.close();
    }
}
