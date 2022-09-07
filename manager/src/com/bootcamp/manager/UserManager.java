package com.bootcamp.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bootcamp.entity.User;

public class UserManager extends BaseManager<User> {

    public User create(User t) throws SQLException {
        String[] returnId = { "id" };
        PreparedStatement statement = getConnection().prepareStatement("INSERT INTO \"user\" (username,password) VALUES (?,?)",
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

    }

    public User update(User t) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("UPDATE \"user\" SET username=?,password=? WHERE id=?");
        statement.setString(1, t.getUsername());
        statement.setString(2, t.getPassword());
        statement.setLong(3, t.getId());
        int affected = statement.executeUpdate();
        if (affected <= 0) {
            t = null;
        }
        disconnect();
        return t;
    }

    public boolean delete(User t) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("DELETE FROM \"user\" WHERE id=?");
        statement.setLong(1, t.getId());
        int affected = statement.executeUpdate();
        disconnect();
        return affected > 0;
    }

    public User getById(long id) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM \"user\" WHERE id=?");
        statement.setLong(1, id);
        ResultSet result = statement.executeQuery();
        User user = parse(result);
        disconnect();
        return user;
    }

    public List<User> getAll() throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM \"user\"");
        ResultSet result = statement.executeQuery();
        List<User> users = parseList(result);
        disconnect();
        return users;
    }

    @Override
    public User parse(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}
