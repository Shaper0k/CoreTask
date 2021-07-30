package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();

        userService.saveUser("Petr", "Ivanov", (byte) 16);

        userService.saveUser("George","Kuzmin", (byte) 31);

        userService.saveUser("Alex","Petrov", (byte) 17);

        userService.saveUser("Artem","Savin", (byte) 54);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}