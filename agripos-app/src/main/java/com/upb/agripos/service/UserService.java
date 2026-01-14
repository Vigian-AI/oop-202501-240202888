package com.upb.agripos.service;

import com.upb.agripos.dao.UserDAO;
import com.upb.agripos.model.User;

public class UserService {
    private final UserDAO userDAO;
    private User currentUser;

    public UserService() {
        this.userDAO = UserDAO.getInstance();
    }

    public User login(String username, String password) {
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            return null;
        }

        User user = userDAO.authenticate(username, password);
        if (user != null) {
            this.currentUser = user;
            System.out.println("✓ Login sukses: " + user.getFullName() + " (" + user.getRole() + ")");
        } else {
            System.out.println("✗ Login gagal: Username atau password salah");
        }
        return user;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        if (currentUser != null) {
            System.out.println("✓ Logout: " + currentUser.getFullName());
            currentUser = null;
        }
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
