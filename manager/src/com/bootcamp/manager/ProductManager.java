package com.bootcamp.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bootcamp.entity.Product;

public class ProductManager extends BaseManager<Product> {

    public Product create(Product t) {
        try {
            String[] returnId = { "id" };
            PreparedStatement statement = getConnection()
                    .prepareStatement("INSERT INTO product (name, price, categoryid) VALUES (?, ?, ?)", returnId);
            statement.setString(1, t.getName());
            statement.setDouble(2, t.getPrice());
            statement.setLong(3, t.getCategoryId());
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

    public Product update(Product t) {
        try {
            PreparedStatement statement = getConnection()
                    .prepareStatement("UPDATE product SET name=?, price=?, categoryid=? WHERE id=?");
            statement.setString(1, t.getName());
            statement.setDouble(2, t.getPrice());
            statement.setLong(3, t.getCategoryId());
            statement.setLong(4, t.getId());
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

    public boolean delete(Product t) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM product WHERE Id=?");
            statement.setLong(1, t.getId());
            int affected = statement.executeUpdate();
            disconnect();
            return affected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Product getById(long id) {

        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM product WHERE id=?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            Product product = null;
            if (result.next()) {
                product = new Product();
                product.setId(result.getLong("id"));
                product.setName(result.getString("name"));
                product.setPrice(result.getDouble("price"));
                product.setCategoryId(result.getLong("categoryid"));
            }
            disconnect();
            return product;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Product> getAll() {

        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM product");
            ResultSet result = statement.executeQuery();
            List<Product> products = new ArrayList<Product>();
            while (result.next()) {
                Product product = new Product();
                product.setId(result.getLong("id"));
                product.setName(result.getString("name"));
                product.setPrice(result.getDouble("price"));
                product.setCategoryId(result.getLong("categoryid"));
                products.add(product);
            }
            disconnect();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Product parse(ResultSet resultSet) {
        try {
            return new Product(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getDouble("price"),
                    resultSet.getLong("categoryId"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
