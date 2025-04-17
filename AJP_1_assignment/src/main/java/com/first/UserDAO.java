package com.first;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UserDAO { 
    private Connection conn;

    public UserDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // CREATE: Insert a new user
    public void addUser(User user) {
        String sql = "INSERT INTO Users (first_name, last_name, email, phone, user_type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getUserType());
            stmt.executeUpdate();
            System.out.println("User added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ: Get all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User(rs.getInt("user_id"), rs.getString("first_name"), 
                                     rs.getString("last_name"), rs.getString("email"), 
                                     rs.getString("phone"), rs.getString("user_type"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // UPDATE: Update user details
    public void updateUser(User user) {
        String sql = "UPDATE Users SET first_name=?, last_name=?, email=?, phone=?, user_type=? WHERE user_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, user.getUserType());
            stmt.setInt(6, user.getUserId());
            stmt.executeUpdate();
            System.out.println("User updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE: Delete a user
    public void deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE user_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.executeUpdate();
            System.out.println("User deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

