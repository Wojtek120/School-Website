package pl.wojtek120.school.dao;

import pl.wojtek120.school.models.User;
import pl.wojtek120.school.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDao {
    private static final String CREATE_USER_QUERY = "INSERT INTO programming_school.users (email, username, password, user_group_id) VALUES (?, ?, ?, ?)";
    private static final String READ_USER_QUERY = "SELECT * FROM programming_school.users WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE programming_school.users SET email=?, username=?, password=?, user_group_id=? WHERE id=?";
    private static final String DELETE_USER_QUERY = "DELETE FROM programming_school.users WHERE id=?";
    private static final String FIND_ALL_USERS_QUERY = "SELECT * FROM programming_school.users";
    private static final String FIND_ALL_BY_GROUP_QUERY = "SELECT * FROM programming_school.users WHERE group_id=?";

    public User create(User user) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getUserGroupId());

            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    int generatedKey = generatedKeys.getInt(1);
                    user.setId(generatedKey);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User read(int id) {

        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    return putDataFromResultSetIntoUser(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(User user) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getUserGroupId());
            statement.setInt(5, user.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAll() {
        return getUsers(-1, FIND_ALL_USERS_QUERY);
    }

    public List<User> findAllByGroupId(int groupId) {
        return getUsers(groupId, FIND_ALL_BY_GROUP_QUERY);
    }

    private List<User> getUsers(int id, String query) {
        try (Connection conn = DbUtil.getConnection()) {
            List<User> users = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(query);

            if (id != -1) {
                statement.setInt(1, id);
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = putDataFromResultSetIntoUser(resultSet);
                    users.add(user);
                }
                return users;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User putDataFromResultSetIntoUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setUserGroupId(resultSet.getInt("user_group_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
