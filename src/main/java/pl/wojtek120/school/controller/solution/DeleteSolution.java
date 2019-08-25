package pl.wojtek120.school.controller.solution;

import pl.wojtek120.school.dao.SolutionDao;
import pl.wojtek120.school.models.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/solution/delete")
public class DeleteSolution extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SolutionDao solutionDao = new SolutionDao();
        solutionDao.delete(Integer.parseInt(request.getParameter("id")));

        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        SolutionDao solutionDao = new SolutionDao();
        Solution solution = solutionDao.read(id);

        request.setAttribute("name", solution.getDescription());
        request.setAttribute("id", id);
        getServletContext().getRequestDispatcher("/web/general/deleteConfirmation.jsp").forward(request, response);
    }
}
