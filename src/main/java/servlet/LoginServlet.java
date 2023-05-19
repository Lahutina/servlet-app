package servlet;

import model.User;
import repository.CustomRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User userAccount = CustomRepository.findUser(email, password);

        if (userAccount == null) {
            String errorMessage = "Invalid email or Password";

            request.setAttribute("errorString", errorMessage);

            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);

            return;
        }

        request.getSession().setAttribute("loginedUser", userAccount);

        response.sendRedirect(request.getContextPath() + "/userInfo");
    }
}
