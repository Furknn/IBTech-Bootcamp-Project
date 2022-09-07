package com.bootcamp.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bootcamp.entity.CartProduct;

public class CartProductManager extends BaseManager<CartProduct> {

    public CartProduct create(CartProduct t) {
        try {
            String[] returnId = { "id" };
            PreparedStatement statement = getConnection().prepareStatement(
                    "INSERT INTO cartproduct (cartid,productid,quantity,price) VALUES (?,?,?,?)", returnId);
            statement.setLong(1, t.getCartId());
            statement.setLong(2, t.getProductId());
            statement.setInt(3, t.getQuantity());
            statement.setDouble(4, t.getPrice());
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

    public CartProduct update(CartProduct t) {

        try {
            PreparedStatement statement = getConnection()
                    .prepareStatement("UPDATE cartproduct SET cartid=?,productid=?,quantity=?,price=? WHERE id=?");
            statement.setLong(1, t.getCartId());
            statement.setLong(2, t.getProductId());
            statement.setInt(3, t.getQuantity());
            statement.setDouble(4, t.getPrice());
            statement.setLong(5, t.getId());
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

    public boolean delete(CartProduct t) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("DELETE FROM CartProduct WHERE Id=?");
            statement.setLong(1, t.getId());
            int affected = statement.executeUpdate();
            disconnect();
            return affected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public CartProduct getById(long id) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM CartProduct WHERE Id=?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            CartProduct cartProduct = parse(result);
            disconnect();
            return cartProduct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<CartProduct> getAll() {
        try {

            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM CartProduct");
            ResultSet result = statement.executeQuery();
            List<CartProduct> cartProducts = parseList(result);
            disconnect();
            return cartProducts;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public CartProduct parse(ResultSet resultSet) {
        try {
            return resultSet.next()
                    ? new CartProduct(resultSet.getLong("Id"), resultSet.getLong("CartId"),
                            resultSet.getLong("ProductId"),
                            resultSet.getInt("Quantity"), resultSet.getDouble("Price"))
                    : null;
        } catch (SQLException e) {

            e.printStackTrace();
            return null;
        }
    }
}