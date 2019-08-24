package pl.wojtek120.school.dao;

import pl.wojtek120.school.models.UserGroup;
import pl.wojtek120.school.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserGroupDao {
    private static final String CREATE_USERGROUP_QUERY = "INSERT INTO user_group (name) VALUES (?)";
    private static final String READ_USERGROUP_QUERY = "SELECT * FROM user_group WHERE id = ?";
    private static final String UPDATE_USERGROUP_QUERY = "UPDATE user_group SET name=? WHERE id=?";
    private static final String DELETE_USERGROUP_QUERY = "DELETE FROM user_group WHERE id=?";
    private static final String FIND_ALL_USERGROUPS_QUERY = "SELECT * FROM user_group";

    /**
     * Function to create new user_group record
     *
     * @param userGroup solution data model
     * @return created solution model
     */
    public UserGroup create(UserGroup userGroup) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_USERGROUP_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, userGroup.getName());
            statement.executeUpdate();

            try(ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    int generatedKey = generatedKeys.getInt(1);
                    userGroup.setId(generatedKey);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userGroup;
    }

    /**
     * Function to read record by chosen id
     *
     * @param id id of record to read
     * @return object of model that was read
     */
    public UserGroup read(int id) {

        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(READ_USERGROUP_QUERY);
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    UserGroup userGroup = new UserGroup();
                    userGroup.setId(resultSet.getInt("id"));
                    userGroup.setName(resultSet.getString("name"));

                    return userGroup;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Function to update user_group record
     *
     * @param userGroup object to be updated
     */
    public void update(UserGroup userGroup) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USERGROUP_QUERY);
            statement.setString(1, userGroup.getName());
            statement.setInt(2, userGroup.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to delete user_group record
     *
     * @param id id of record to be deleted
     */
    public void delete(int id) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_USERGROUP_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function to return list of all user_group records from database
     *
     * @return list of all records
     */
    public List<UserGroup> findAll() {
        try (Connection connection = DbUtil.getConnection()) {
           List<UserGroup> userGroups = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERGROUPS_QUERY);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UserGroup userGroup = new UserGroup();
                    userGroup.setId(resultSet.getInt("id"));
                    userGroup.setName(resultSet.getString("name"));
                    userGroups.add(userGroup);
                }
                return userGroups;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
