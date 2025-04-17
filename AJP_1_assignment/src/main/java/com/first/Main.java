package com.first;

import java.util.List;
import java.util.Scanner;




public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n======= Turf Management System =======");
            System.out.println("1. Add User");
            System.out.println("2. View All Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

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
                    System.out.print("Enter User Type (admin/customer/staff): ");
                    String userType = scanner.nextLine();

                    User newUser = new User(0, firstName, lastName, email, phone, userType);
                    userDAO.addUser(newUser);
                    break;

                case 2:
                    // View All Users
                    List<User> users = userDAO.getAllUsers();
                    if (users.isEmpty()) {
                        System.out.println("No users found.");
                    } else {
                        System.out.println("\nUser List:");
                        for (User user : users) {
                            System.out.println("ID: " + user.getUserId() + ", Name: " + user.getFirstName() + " " + user.getLastName() +
                                    ", Email: " + user.getEmail() + ", Phone: " + user.getPhone() + ", Type: " + user.getUserType());
                        }
                    }
                    break;

                case 3:
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
                    System.out.print("Enter New User Type (admin/customer/staff): ");
                    String newUserType = scanner.nextLine();

                    User updateUser = new User(updateId, newFirstName, newLastName, newEmail, newPhone, newUserType);
                    userDAO.updateUser(updateUser);
                    break;

                case 4:
                    // Delete User
                    System.out.print("Enter User ID to delete: ");
                    int deleteId = scanner.nextInt();
                    userDAO.deleteUser(deleteId);
                    break;

                case 5:
                    // Exit
                    System.out.println("Exiting the application...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }
}

