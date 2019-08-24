package pl.wojtek120.school.dao;

import pl.wojtek120.school.models.Exercise;
import pl.wojtek120.school.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDao {
    private static final String CREATE_EXERCISE_QUERY = "INSERT INTO exercise (title, description) VALUES (?, ?)";
    private static final String READ_EXERCISE_QUERY = "SELECT * FROM exercise WHERE id = ?";
    private static final String UPDATE_EXERCISE_QUERY = "UPDATE exercise SET title=?, description=? WHERE id=?";
    private static final String DELETE_EXERCISE_QUERY = "DELETE FROM exercise WHERE id=?";
    private static final String FIND_ALL_EXERCISES_QUERY = "SELECT * FROM exercise";
    private static final String FIND_ALL_EXERCISES_THAT_ARE_NOT_SOLVED_BY_USER_QUERY = "SELECT * FROM exercise WHERE NOT id IN (SELECT exercise_id FROM solution WHERE user_id = ?);";

    /**
     * Function to create new exercise record
     *
     * @param exercise exercise data model
     * @return created exercise model
     */
    public Exercise create(Exercise exercise) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_EXERCISE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    int generatedKey = generatedKeys.getInt(1);
                    exercise.setId(generatedKey);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exercise;
    }

    /**
     * Function to read exercise record by chosen id
     *
     * @param id id of record to read
     * @return object of model that was read
     */
    public Exercise read(int id) {

        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_EXERCISE_QUERY);
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Exercise exercise = new Exercise();
                    exercise.setId(resultSet.getInt("id"));
                    exercise.setTitle(resultSet.getString("title"));
                    exercise.setDescription(resultSet.getString("description"));
                    return exercise;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Function to update exercise record
     *
     * @param exercise object to be updated
     */
    public void update(Exercise exercise) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_EXERCISE_QUERY);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.setInt(3, exercise.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to delete exercise record
     *
     * @param id id of record to be deleted
     */
    public void delete(int id) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_EXERCISE_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to return list of all exercise records from database
     *
     * @return list of all records
     */
    public List<Exercise> findAll() {
        return findAllExecuteQuery(FIND_ALL_EXERCISES_QUERY, -1);
    }

    /**
     * Function to return list of all exercise records that are not solved
     * and belongs to specified in parameter user
     *
     * @param userId id of user wo which solution must belong
     * @return list of records
     */
    public List<Exercise> findAllThatAreNotSolved(int userId) {
        return findAllExecuteQuery(FIND_ALL_EXERCISES_THAT_ARE_NOT_SOLVED_BY_USER_QUERY, userId);
    }


    /**
     * Function to return list of data, by specified query
     *
     * @param id id to be set in query. If id <= 0, than skipped.
     * @return list of data
     */
    private List<Exercise> findAllExecuteQuery(String query, int id) {
        try (Connection connection = DbUtil.getConnection()) {
            List<Exercise> exercises = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(query);

            if (id > 0) {
                statement.setInt(1, id);
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = putDataFromResultSetIntoSolution(resultSet);
                exercises.add(exercise);
            }
            return exercises;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Function to put data from result set to model
     *
     * @param resultSet result set
     * @return model with data
     */
    private Exercise putDataFromResultSetIntoSolution(ResultSet resultSet) {
        Exercise exercise = new Exercise();
        try {
            exercise.setId(resultSet.getInt("id"));
            exercise.setTitle(resultSet.getString("title"));
            exercise.setDescription(resultSet.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exercise;
    }
}
