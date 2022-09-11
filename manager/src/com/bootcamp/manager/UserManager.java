package com.bootcamp.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bootcamp.entity.User;

public class UserManager extends BaseManager<User> {
    private static UserManager instance = null;
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User create(User t) throws SQLException {
        try {
            String[] returnId = { "id" };
            PreparedStatement statement = getConnection().prepareStatement(
                    "INSERT INTO \"user\" (username,password) VALUES (?,?)",
                    returnId);
            statement.setString(1, t.getUsername());
            statement.setString(2, t.getPassword());
            int affected = statement.executeUpdate();
            if (affected > 0) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    t.setId(rs.getLong(1));
                }
            } else {
                t = null;
            }
            disconnect();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public User update(User t) throws SQLException {
        try {
            PreparedStatement statement = getConnection()
                    .prepareStatement("UPDATE \"user\" SET username=?,password=? WHERE id=?");
            statement.setString(1, t.getUsername());
            statement.setString(2, t.getPassword());
            statement.setLong(3, t.getId());
            int affected = statement.executeUpdate();
            if (affected <= 0) {
                t = null;
            }
            disconnect();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean delete(long id) throws SQLException {
        try {
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM \"user\" WHERE id=?");
            statement.setLong(1, id);
            int affected = statement.executeUpdate();
            disconnect();
            return affected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public User getById(long id) throws SQLException {
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM \"user\" WHERE id=?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            User user = parse(result);
            disconnect();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            ;
            return null;
        }

    }

    public List<User> getAll() throws SQLException {
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM \"user\"");
            ResultSet result = statement.executeQuery();
            List<User> users = parseList(result);
            disconnect();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public User parse(ResultSet resultSet) throws SQLException {
        try {
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
            }
            return new User(resultSet.getLong("id"), resultSet.getString("username"), resultSet.getString("password"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
