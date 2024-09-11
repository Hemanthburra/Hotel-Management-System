package com.HotelManagementSystem.controller;

import java.util.List;

import com.HotelManagementSystem.dao.UserDAO;
import com.HotelManagementSystem.models.User;

public class UserController {
    private UserDAO userDAO;

    public UserController() throws ClassNotFoundException {
        userDAO = new UserDAO();
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void saveUser(User user) {
        userDAO.addUser(user);
    }

    public User getUserByName(String userName) {
        return userDAO.getUserByName(userName);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(String userName) {
        userDAO.deleteUser(userName);
    }
}