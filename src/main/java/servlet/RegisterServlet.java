package servlet;

import config.RoleService;
import model.User;
import repository.CustomRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/pages/register.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
 
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User userAccount = new User(email, password, RoleService.ROLE_USER);
        userAccount = CustomRepository.addUser(userAccount);

        request.getSession().setAttribute("loginedUser", userAccount);
        response.sendRedirect(request.getContextPath() + "/userInfo");
    }
 
}
