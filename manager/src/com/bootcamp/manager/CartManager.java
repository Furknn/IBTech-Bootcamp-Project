package com.bootcamp.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bootcamp.entity.Cart;

public class CartManager extends BaseManager<Cart> {

    public Cart create(Cart t) {
        try {

            String[] returnId = { "id" };
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO cart (totalamount,customername) VALUES (?,?)",
                    returnId);
            statement.setDouble(1, t.getTotalAmount());
            statement.setString(2, t.getCustomerName());
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

    public Cart update(Cart t) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("UPDATE cart SET totalamount=?,customername=? WHERE id=?");
            statement.setDouble(1, t.getTotalAmount());
            statement.setString(2, t.getCustomerName());
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

    public boolean delete(Cart t) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM Cart WHERE id=?");
            statement.setLong(1, t.getId());
            int affected = statement.executeUpdate();
            disconnect();
            return affected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cart getById(long id) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM Cart WHERE id=?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            Cart cart = parse(result);
            disconnect();
            return cart;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Cart> getAll() {
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM Cart");
            ResultSet result = statement.executeQuery();
            List<Cart> carts = parseList(result);
            disconnect();
            return carts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Cart parse(ResultSet resultSet) {
        try {
            return resultSet.next()
                    ? new Cart(resultSet.getLong("id"), resultSet.getDouble("totalAmount"),
                            resultSet.getString("customerName"))
                    : null;
        } catch (SQLException e) {

            e.printStackTrace();
            return null;
        }
    }

}
