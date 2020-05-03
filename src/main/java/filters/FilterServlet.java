package filters;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin", filterName = "FilterServlet")

public class FilterServlet implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();

        if (session == null) {
            ((HttpServletResponse) servletResponse).sendRedirect("login");
            return;
        }
        User user = (User) session.getAttribute("session");
        if (user == null) {
            RequestDispatcher dispatcher = servletRequest
                    .getRequestDispatcher("/login");
            dispatcher.forward(servletRequest, servletResponse);
            return;
        }
        String role = user.getRole();
        if (role.equals("admin")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (role.equals("user")) {
            RequestDispatcher dispatcher = servletRequest
                    .getRequestDispatcher("/user");
            dispatcher.forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
