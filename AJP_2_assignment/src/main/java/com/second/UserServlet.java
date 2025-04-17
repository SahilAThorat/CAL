package com.second;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L; 
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String userType = request.getParameter("userType");

            User user = new User(0, firstName, lastName, email, phone, userType);
            userDAO.addUser(user);
            response.getWriter().println("User added successfully!");
        } 
        else if ("update".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String userType = request.getParameter("userType");

            User user = new User(userId, firstName, lastName, email, phone, userType);
            userDAO.updateUser(user);
            response.getWriter().println("User updated successfully!");
        } 
        else if ("delete".equals(action)) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            userDAO.deleteUser(userId);
            response.getWriter().println("User deleted successfully!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDAO.getAllUsers();
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        out.println("<h1>User List</h1>");
        for (User user : users) {
            out.println("<p>" + user.getFirstName() + " " + user.getLastName() + " (" + user.getEmail() + ")</p>");
        }
    }
}
