package servlet;

import model.User;
import repository.CustomRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/createUser")
public class CreateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/pages/createUser.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User userAccount = new User(email, password, role);
        CustomRepository.addUser(userAccount);

        response.sendRedirect(request.getContextPath() + "/admin");
    }

}
