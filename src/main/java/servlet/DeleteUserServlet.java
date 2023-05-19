package servlet;


import repository.CustomRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long userId = Long.parseLong(request.getParameter("userId"));

        CustomRepository.deleteUser(userId);

        response.sendRedirect(request.getContextPath() + "/admin");
    }
}
