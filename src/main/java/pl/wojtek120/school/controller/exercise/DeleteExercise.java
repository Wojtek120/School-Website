package pl.wojtek120.school.controller.exercise;

import pl.wojtek120.school.dao.ExerciseDao;
import pl.wojtek120.school.dao.UserDao;
import pl.wojtek120.school.models.Exercise;
import pl.wojtek120.school.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/exercise/delete")
public class DeleteExercise extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExerciseDao exerciseDao = new ExerciseDao();
        int exerciseId = Integer.parseInt(request.getParameter("id"));

        exerciseDao.delete(exerciseId);

        response.sendRedirect("/admin/exercise");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ExerciseDao exerciseDao = new ExerciseDao();
        Exercise exercise = exerciseDao.read(id);

        request.setAttribute("name", exercise.getTitle());
        request.setAttribute("id", id);
        request.setAttribute("delete", "admin/exercise");

        getServletContext().getRequestDispatcher("/web/general/deleteConfirmation.jsp").forward(request, response);
    }
}
