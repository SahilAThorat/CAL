package com.sk;

import com.sk.dao.UserDao;
import com.sk.model.User;
import com.sk.model.UserType;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n******** Turf Management System ********");
            System.out.println("Select Operation: ");
            System.out.println("1 - Add User");
            System.out.println("2 - View All Users");
            System.out.println("3 - View User by ID");
            System.out.println("4 - Update User");
            System.out.println("5 - Delete User");
            System.out.println("6 - Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add User
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();

                    System.out.print("Enter User Type (ADMIN, CUSTOMER, STAFF): ");
                    String userTypeStr = scanner.nextLine().toUpperCase();
                    UserType userType = UserType.valueOf(userTypeStr);

                    User newUser = new User(firstName, lastName, email, phone, userType);
                    userDao.addUser(newUser);
                    System.out.println("✅ User added successfully!");
                    break;

                case 2:
                    // View All Users
                    List<User> users = userDao.getAllUsers();
                    if (users.isEmpty()) {
                        System.out.println("⚠ No users found!");
                    } else {
                        System.out.println("\n📋 User List:");
                        users.forEach(user -> System.out.println(user.getUserId() + " - " + user.getFirstName() + " " + user.getLastName()));
                    }
                    break;

                case 3:
                    // View User by ID
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    User user = userDao.getUserById(userId);
                    if (user != null) {
                        System.out.println("\n👤 User Details:");
                        System.out.println("ID: " + user.getUserId());
                        System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
                        System.out.println("Email: " + user.getEmail());
                        System.out.println("Phone: " + user.getPhone());
                        System.out.println("User Type: " + user.getUserType());
                        System.out.println("Created At: " + user.getCreatedAt());
                    } else {
                        System.out.println("⚠ User not found!");
                    }
                    break;

                case 4:
                    // Update User
                    System.out.print("Enter User ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter New First Name: ");
                    String newFirstName = scanner.nextLine();

                    System.out.print("Enter New Last Name: ");
                    String newLastName = scanner.nextLine();

                    System.out.print("Enter New Email: ");
                    String newEmail = scanner.nextLine();

                    System.out.print("Enter New Phone: ");
                    String newPhone = scanner.nextLine();

                    userDao.updateUser(updateId, newFirstName, newLastName, newEmail, newPhone);
                    System.out.println("✅ User updated successfully!");
                    break;

                case 5:
                    // Delete User
                    System.out.print("Enter User ID to delete: ");
                    int deleteId = scanner.nextInt();
                    userDao.deleteUser(deleteId);
                    System.out.println("🗑 User deleted successfully!");
                    break;

                case 6:
                    // Exit
                    System.out.println("🚪 Exiting the application...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("⚠ Invalid choice! Please try again.");
            }
        }
    }
}
