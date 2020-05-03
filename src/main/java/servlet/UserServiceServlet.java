package servlet;


import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebServlet(name = "UserServiceServlet", urlPatterns= "/login")
public class UserServiceServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getServletContext()
                .getRequestDispatcher("/login.jsp");
        dispatcher.forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest) req).getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = UserService.getInstance().getUserByLoginAndPassword(login, password);
        session.setAttribute("session", user);
        resp.sendRedirect("/untitled1_master_war/user");
     }
}
