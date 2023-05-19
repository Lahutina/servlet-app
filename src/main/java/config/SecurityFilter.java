package config;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter("/*")
public class SecurityFilter implements Filter {

    public SecurityFilter() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String servletPath = request.getServletPath();
        if (servletPath.equals("/login")) {
            chain.doFilter(request, response);
            return;
        }

        User loginedUser = (User) request.getSession().getAttribute("loginedUser");
        if (loginedUser != null) {
            String email = loginedUser.getEmail();
            String role = loginedUser.getRole();
            request = new WrappedRequest(email, role, request);
        }

        if (!RoleService.urlsAccessibleToAll(request.getServletPath()) && !RoleService.hasPermission(request)) {
            request.getRequestDispatcher("/WEB-INF/pages/accessDenied.jsp").forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig fConfig) {

    }

}