package servlet;

import model.User;
import repository.CustomRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long userId = Long.parseLong(request.getParameter("userId"));

        User user = CustomRepository.findUser(userId);
        request.setAttribute("user", user);

        request.getRequestDispatcher("/WEB-INF/pages/editUser.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long userId = Long.parseLong(request.getParameter("userId"));
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User updatedUser = new User(userId, email, password, role);
        CustomRepository.update(updatedUser);

        response.sendRedirect(request.getContextPath() + "/admin");
    }
}
