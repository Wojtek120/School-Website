package pl.wojtek120.school.controller.exercise;

import pl.wojtek120.school.dao.ExerciseDao;
import pl.wojtek120.school.dao.UserDao;
import pl.wojtek120.school.dao.UserGroupDao;
import pl.wojtek120.school.models.Exercise;
import pl.wojtek120.school.models.User;
import pl.wojtek120.school.models.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/admin/exercise/add")
public class AddExercise extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ExerciseDao exerciseDao = new ExerciseDao();

        String exerciseTitle = request.getParameter("exerciseTitle");
        String exerciseDescription = request.getParameter("exerciseDescription");

        Exercise exercise = new Exercise(exerciseTitle, exerciseDescription);

        exerciseDao.create(exercise);

        response.sendRedirect("/admin/exercise");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/web/exercise/addExercise.jsp").forward(request, response);
    }
}
