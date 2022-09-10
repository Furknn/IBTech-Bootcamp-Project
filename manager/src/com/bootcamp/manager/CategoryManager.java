package com.bootcamp.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bootcamp.entity.Category;

public class CategoryManager extends BaseManager<Category> {

    public Category create(Category t) {

        try {
            String[] returnId = { "id" };
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO category (name) VALUES (?)", returnId);
            statement.setString(1, t.getName());
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

    public Category update(Category t) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("UPDATE category SET name=? WHERE id=?");
            statement.setString(1, t.getName());
            statement.setLong(2, t.getId());
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

    public boolean delete(Category t) {

        try {
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM Category WHERE Id=?");
            statement.setLong(1, t.getId());
            int affected = statement.executeUpdate();
            disconnect();
            return affected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Category getById(long id) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM Category WHERE Id=?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            Category category = parse(result);
            disconnect();
            return category;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Category> getAll() {

        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM Category");
            ResultSet result = statement.executeQuery();
            List<Category> categories = parseList(result);
            disconnect();
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Category parse(ResultSet resultSet) {
        try {
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
            }
            return new Category(resultSet.getLong("Id"), resultSet.getString("Name"));
        } catch (SQLException e) {

            e.printStackTrace();
            return null;
        }
    }
}
