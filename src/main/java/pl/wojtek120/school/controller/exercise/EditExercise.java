package pl.wojtek120.school.controller.exercise;

import pl.wojtek120.school.dao.ExerciseDao;
import pl.wojtek120.school.dao.UserDao;
import pl.wojtek120.school.dao.UserGroupDao;
import pl.wojtek120.school.models.Exercise;
import pl.wojtek120.school.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/exercise/edit")
public class EditExercise extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExerciseDao exerciseDao = new ExerciseDao();

        int id = Integer.parseInt(request.getParameter("id"));
        Exercise exercise = exerciseDao.read(id);

        exercise.setTitle(request.getParameter("exerciseTitle"));
        exercise.setDescription(request.getParameter("exerciseDescription"));

        exerciseDao.update(exercise);

        response.sendRedirect("/admin/exercise");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExerciseDao exerciseDao = new ExerciseDao();

        int id = Integer.parseInt(request.getParameter("id"));

        request.setAttribute("exercise", exerciseDao.read(id));

        getServletContext().getRequestDispatcher("/web/exercise/editExercise.jsp").forward(request, response);
    }
}
