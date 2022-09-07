package com.bootcamp.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseManager<T> {
    private String url;
    private String username;
    private String password;
    protected Connection connection;

    public BaseManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public BaseManager() {
        this("jdbc:postgresql://localhost:5432/bootcampproject", "sa", "123321xp");
    }

    public abstract T parse(ResultSet resultSet) throws SQLException;

    public List<T> parseList(ResultSet resultSet) throws SQLException {
        List<T> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(parse(resultSet));
        }
        return list;
    }

    public void connect() throws SQLException {
    	try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        connection = DriverManager.getConnection(url, username, password);
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connect();
        }
        return connection;
    }

}
