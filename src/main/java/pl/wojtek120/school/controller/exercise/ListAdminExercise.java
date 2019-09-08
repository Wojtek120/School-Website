package pl.wojtek120.school.controller.exercise;

import pl.wojtek120.school.dao.ExerciseDao;
import pl.wojtek120.school.dao.UserGroupDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/exercise")
public class ListAdminExercise extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExerciseDao exerciseDao = new ExerciseDao();
        request.setAttribute("exercises", exerciseDao.findAll());

        getServletContext().getRequestDispatcher("/web/exercise/listAdminExercise.jsp").forward(request, response);
    }
}
