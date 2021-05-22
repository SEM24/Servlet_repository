package main.controller;

import main.UserDB.UserDB;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private UserDB db;

    @Override
    public void init(ServletConfig config) throws ServletException {
        db = UserDB.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = (String) req.getSession().getAttribute("login");

        Optional<Role> userRole = db.getRole(login);

        if (userRole.isPresent() && userRole.get().equals(Role.ROLE_ADMIN)) {
            req.getSession().setAttribute("user_role", Role.ROLE_ADMIN);
        }
        if (userRole.isPresent() && userRole.get().equals(Role.ROLE_SUPPORT)) {
            req.getSession().setAttribute("user_role", Role.ROLE_SUPPORT);
        }
        req.setAttribute("access_admin", Role.ROLE_ADMIN);
        req.setAttribute("access_support", Role.ROLE_SUPPORT);

        req.setAttribute("users", db.getMemoryDb());
        req.getRequestDispatcher("users.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();

        String login = (String) req.getSession().getAttribute("login");

        Optional<Role> userRole = db.getRole(login);

        String deleteUser = req.getParameter("delete-user");
        Optional<Role> deleteUserRole = db.getRole(deleteUser);

        if (userRole.isPresent() && userRole.get().equals(Role.ROLE_ADMIN)) {
            req.setAttribute("access_admin", Role.ROLE_ADMIN);
            req.setAttribute("users", db.getMemoryDb());
            if (deleteUserRole.isPresent() && deleteUserRole.get().equals(Role.ROLE_SUPPORT) ||
                    deleteUserRole.isPresent() && deleteUserRole.get().equals(Role.ROLE_USER)) {
                db.remove(deleteUser);
            } else {
                writer.print("You can't delete users with this role, change the role");
            }
            req.getRequestDispatcher("users.jsp").include(req, resp);
        } else if (userRole.isPresent() && userRole.get().equals(Role.ROLE_SUPPORT)) {
            req.setAttribute("access_support", Role.ROLE_SUPPORT);
            req.setAttribute("users", db.getMemoryDb());
            if (deleteUserRole.isPresent() && deleteUserRole.get().equals(Role.ROLE_USER)) {
                db.remove(deleteUser);
            } else {
                writer.print("You can't delete users with this role, change the role");
            }
            req.getRequestDispatcher("users.jsp").include(req, resp);
        }
    }
}
