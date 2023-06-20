package com.example.servlets;

import com.example.dao.BuyerDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateBuyerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int buyerId = Integer.parseInt(request.getParameter("buyerId"));
        String newName = request.getParameter("newName");
        String newSUrname = request.getParameter("newSurname");
        String newEmail = request.getParameter("newEmail");
        String newBalance = request.getParameter("newBalance");

        BuyerDAO buyerDAO = new BuyerDAO();
        try {
            buyerDAO.updateBuyer(buyerId, newName, newSUrname, newEmail, Double.parseDouble(newBalance));
            request.setAttribute("updateMessage", "Kupac uspješno ažuriran");
            request.getRequestDispatcher("buyers.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("buyers.jsp");
    }

}
