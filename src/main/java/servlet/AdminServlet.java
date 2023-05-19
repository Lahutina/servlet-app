package servlet;

import repository.CustomRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      request.setAttribute("userList", CustomRepository.getAll());
      request.getRequestDispatcher("/WEB-INF/pages/admin.jsp").forward(request, response);
   }
}