package servlet;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminServlet", urlPatterns = "/admin")

public class AdminServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService.getInstance().createTable();
        List<User> users = UserService.getInstance().getAllUser();
        req.setAttribute("userList", users);


        RequestDispatcher dispatcher = req.getServletContext()
                .getRequestDispatcher("/admin.jsp");
        dispatcher.forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String role = UserService.getInstance().setRoleByLogin(login);
        UserService.getInstance().addUser(new User(login, email, password, role));
        doGet(req, resp);
    }
}
