package pl.wojtek120.school.dao;

import pl.wojtek120.school.models.Solution;
import pl.wojtek120.school.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao {
    private static final String CREATE_SOLUTION_QUERY = "INSERT INTO solution (description, exercise_id, user_id) VALUES (?, ?, ?)";
    private static final String READ_SOLUTION_QUERY = "SELECT * FROM solution WHERE id = ?";
    private static final String UPDATE_SOLUTION_QUERY = "UPDATE solution SET description=?, exercise_id=?, user_id=? WHERE id=?";
    private static final String DELETE_SOLUTION_QUERY = "DELETE FROM solution WHERE id=?";
    private static final String FIND_ALL_SOLUTIONS_QUERY = "SELECT * FROM solution";
    private static final String FIND_ALL_SORTED_SOLUTION_BY_EXERCISE_QUERY = "SELECT * FROM solution WHERE exercise_id = ? ORDER BY created";
    private static final String FIND_ALL_BY_USER_ID_SOLUTION_QUERY = "SELECT * FROM solution WHERE user_id = ? ORDER BY created";
    private static final String FIND_RECENT_SOLUTIONS_QUERY = "SELECT * FROM programming_school.solution ORDER by updated DESC LIMIT ?";

    /**
     * Function to create new solution record
     *
     * @param solution solution data model
     * @return created solution model
     */
    public Solution create(Solution solution) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, solution.getDescription());
            statement.setInt(2, solution.getExerciseId());
            statement.setInt(3, solution.getUserId());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    int generatedKey = generatedKeys.getInt(1);
                    solution.setId(generatedKey);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return solution;
    }

    /**
     * Function to read record by chosen id
     *
     * @param id id of record to read
     * @return object of model that was read
     */
    public Solution read(int id) {

        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return putDataFromResultSetIntoSolution(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Function to update record
     *
     * @param solution object to be updated
     */
    public void update(Solution solution) {
        try (Connection connection = DbUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_SOLUTION_QUERY)) {
                statement.setString(1, solution.getDescription());
                statement.setInt(2, solution.getExerciseId());
                statement.setInt(3, solution.getUserId());
                statement.setInt(4, solution.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to delete record
     *
     * @param id id of record to be deleted
     */
    public void delete(int id) {
        try (Connection connection = DbUtil.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_SOLUTION_QUERY)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to return list of all records from database
     *
     * @return list of all records
     */
    public List<Solution> findAll() {
        return getSolutions(-1, -1, FIND_ALL_SOLUTIONS_QUERY);
    }

    /**
     * Function to return list of all records from database that have id of exercise specified as parameter
     *
     * @param exerciseId id of exercise which returned record must contain
     * @return list of records
     */
    private List<Solution> findAllByExerciseId(int exerciseId) {
        return getSolutions(exerciseId, -1, FIND_ALL_SORTED_SOLUTION_BY_EXERCISE_QUERY);
    }

    /**
     * Function to return list of all solution records from database that have user id specified as parameter
     *
     * @param userId id of user which returned record must contain
     * @return list of records
     */
    public List<Solution> findAllByUserId(int userId) {
        return getSolutions(userId, -1, FIND_ALL_BY_USER_ID_SOLUTION_QUERY);
    }

    /**
     * Function to return list of solution records sorted descending by update.
     *
     * @param limit - number of records to be returned
     * @return list od data
     */
    public List<Solution> findRecent(int limit){
        return getSolutions(-1, limit, FIND_RECENT_SOLUTIONS_QUERY);
    }

    /**
     * Function to return list of data, by specified query
     *
     * @param id    id to be set in query. If id <= 0, than skipped.
     * @param limit limit of data to show. If limit <= 0, than skipped.
     * @return list of data
     */
    private List<Solution> getSolutions(int id, int limit, String query) {
        try (Connection connection = DbUtil.getConnection()) {

            List<Solution> solutions = new ArrayList<>();
            try (PreparedStatement statement = connection.prepareStatement(query)) {

                if (id > 0) {
                    statement.setInt(1, id);
                }

                if (limit > 0) {
                    statement.setInt(1, limit);
                }

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Solution solution = putDataFromResultSetIntoSolution(resultSet);
                    solutions.add(solution);
                }
                return solutions;
            }
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
    private Solution putDataFromResultSetIntoSolution(ResultSet resultSet) {
        Solution solution = new Solution();
        try {
            solution.setId(resultSet.getInt("id"));
            solution.setCreated(resultSet.getTimestamp("created"));
            solution.setUpdated(resultSet.getTimestamp("updated"));
            solution.setDescription(resultSet.getString("description"));
            solution.setExerciseId(resultSet.getInt("exercise_id"));
            solution.setUserId(resultSet.getInt("user_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return solution;
    }
}
