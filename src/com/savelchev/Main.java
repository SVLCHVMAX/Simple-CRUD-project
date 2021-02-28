package com.savelchev;

import java.io.IOException;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        // write your code here
        int userId;
        String userName;
        String email;
        int i;

        ConsoleHelper.writeMessage("Выберите действие: \n" +
                "1 - Показать таблицу\n" +
                "2 - Удалить пользователя из таблицы по id\n" +
                "3 - Вставить нового пользователя в таблицу\n" +
                "4 - Выбрать определенного пользователя из таблицы по имени\n" +
                "5 - Изменить данные пользователя в таблице\n" +
                "6 - Выход");
        ConsoleHelper.writeMessage("Введите цифру:");
        i = ConsoleHelper.readInt();
        Connection connection = new Connection();
        connection.connection();

        while (i != 6) {
            if (i == 1) {
                connection.showTable();
                ConsoleHelper.writeMessage("Введите цифру:");
                i = ConsoleHelper.readInt();
            }
            if (i == 2) {
                ConsoleHelper.writeMessage("Введите id пользователя, которого нужно удалить:");
                userId = ConsoleHelper.readInt();
                if (userId!=0) {
                    connection.deleteUser(userId);
                    ConsoleHelper.writeMessage(String.format("Пользователь с user_id = %d удален", userId));
                    ConsoleHelper.writeMessage("Введите цифру:");
                    i = ConsoleHelper.readInt();
                }
                else {
                    ConsoleHelper.writeMessage("Такого id не существует.");
                    ConsoleHelper.writeMessage("Введите цифру:");
                    i = ConsoleHelper.readInt();
                }
            }
            if (i == 3) {
                ConsoleHelper.writeMessage("Введите имя нового пользователя:");
                userName = ConsoleHelper.readString();
                ConsoleHelper.writeMessage("Введите email:");
                email = ConsoleHelper.readString();
                connection.addNewUser(userName, email);
                ConsoleHelper.writeMessage("Пользователь добавлен");
                ConsoleHelper.writeMessage("Введите цифру:");
                i = ConsoleHelper.readInt();
            }
            if (i == 4) {
                ConsoleHelper.writeMessage("Введите имя пользователя:");
                userName = ConsoleHelper.readString();
                connection.selectOneUserByName(userName);
                ConsoleHelper.writeMessage("Введите цифру:");
                i = ConsoleHelper.readInt();
            }
            if (i == 5) {
                ConsoleHelper.writeMessage("Введите id пользователя, данные которого нужно изменить:");
                userId = ConsoleHelper.readInt();
                ConsoleHelper.writeMessage("Введите новое имя пользователя:");
                userName = ConsoleHelper.readString();
                ConsoleHelper.writeMessage("Введите новый email пользователя:");
                email = ConsoleHelper.readString();
                connection.updateUser(userId, userName, email);
                ConsoleHelper.writeMessage("Данные пользователя изменены");
                ConsoleHelper.writeMessage("Введите цифру:");
                i = ConsoleHelper.readInt();
            }
            if (i > 6 || i < 1) {
                ConsoleHelper.writeMessage("Введите другую цифру:");
                i = ConsoleHelper.readInt();
            }
        }
        connection.closeConnection();
    }
}
