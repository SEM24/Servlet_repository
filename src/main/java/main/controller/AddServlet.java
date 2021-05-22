package main.controller;

import main.UserDB.UserDB;
import main.entity.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
@WebServlet("/addForm")
public class AddServlet extends HttpServlet {
    private UserDB db;

    @Override
    public void init(ServletConfig config) throws ServletException {
        db = UserDB.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();
        String userLogin = (String) req.getSession().getAttribute("login");
        Optional<Role> userRole = db.getRole(userLogin);

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String roleAdd = req.getParameter("role");
        Role role = Role.valueOf(roleAdd);
        User user = new User(login, password, role);

        if (userRole.isPresent() && userRole.get().equals(Role.ROLE_ADMIN)) {
            if (role.equals(Role.ROLE_SUPPORT) || role.equals(Role.ROLE_USER)) {
                db.add(user);
                resp.sendRedirect("/users");
            }
            writer.print(
                    "You can't add user with your role, change the role");
            req.setAttribute("access_admin", Role.ROLE_ADMIN);
            req.setAttribute("users", db.getMemoryDb());
            req.getRequestDispatcher("users.jsp").include(req, resp);
        } else if (userRole.isPresent() && userRole.get().equals(Role.ROLE_SUPPORT)) {
            if (role.equals(Role.ROLE_USER)) {
                db.add(user);
                resp.sendRedirect("/users");
            }
            writer.print(
                    "You can't add user with your role, change the role");
            req.setAttribute("access_admin", Role.ROLE_SUPPORT);
            req.setAttribute("users", db.getMemoryDb());
            req.getRequestDispatcher("users.jsp").include(req, resp);
        }
    }
}
