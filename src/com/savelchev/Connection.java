package com.savelchev;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connection {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String DB_Driver = "org.postgresql.Driver";
    java.sql.Connection connection = null;
    Statement statement = null;
    String sql = null;
    int userId;
    String userName;
    String email;

    public void connection() {
        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Соединение с СУБД выполнено.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            ConsoleHelper.writeMessage("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace();
            ConsoleHelper.writeMessage("Ошибка SQL!");
        }
    }

    public void showTable() throws SQLException {
        statement = connection.createStatement();
        sql = "SELECT * FROM information_schema.users";
        ResultSet resultSet = statement.executeQuery(sql);
        ConsoleHelper.writeMessage("Table users");
        while (resultSet.next()) {
            userId = resultSet.getInt("user_id");
            userName = resultSet.getString("user_name");
            email = resultSet.getString("email");
            ConsoleHelper.writeMessage("User id:" + userId);
            ConsoleHelper.writeMessage("User name:" + userName);
            ConsoleHelper.writeMessage("email:" + email);
            ConsoleHelper.writeMessage("=========================");
        }
    }

    public void selectOneUserByName(String userName) throws SQLException {
        statement = connection.createStatement();
        sql = String.format("SELECT * FROM information_schema.users WHERE user_name = '%s'", userName);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            userId = resultSet.getInt("user_id");
            userName = resultSet.getString("user_name");
            email = resultSet.getString("email");
            ConsoleHelper.writeMessage("User id:" + userId);
            ConsoleHelper.writeMessage("User name:" + userName);
            ConsoleHelper.writeMessage("email:" + email);
        }

    }

    public void addNewUser(String user_name, String email) throws SQLException {
        statement = connection.createStatement();
        sql = String.format("INSERT INTO information_schema.users(user_name, email) VALUES ('%s', '%s')", user_name, email);
        statement.executeUpdate(sql);
    }

    public void deleteUser(int id) throws SQLException {
        statement = connection.createStatement();
        sql = String.format("DELETE FROM information_schema.users WHERE user_id = %d", id);
        statement.executeUpdate(sql);
    }

    public void updateUser(int id, String name, String email) throws SQLException {
        statement = connection.createStatement();
        sql = String.format("UPDATE information_schema.users SET user_name = '%s', email = '%s' WHERE user_id = %d", name, email, id);
        statement.executeUpdate(sql);
    }

    public void closeConnection() throws SQLException {
        connection.close();
        ConsoleHelper.writeMessage("Отключение от СУБД выполнено.");
    }
}
