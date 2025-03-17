package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Alice", "Brown", (byte) 25);
        userService.saveUser("Bob", "Smith", (byte) 30);
        userService.saveUser("Charlie", "Johnson", (byte) 35);
        userService.saveUser("Diana", "White", (byte) 40);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
