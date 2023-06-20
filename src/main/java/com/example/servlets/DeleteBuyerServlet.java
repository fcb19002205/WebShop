package com.example.servlets;

import com.example.dao.BuyerDAO;

import com.example.exceptions.BuyerNotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBuyerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int buyerId = Integer.parseInt(request.getParameter("buyerId"));

        BuyerDAO buyerDAO = new BuyerDAO();
        try {
            buyerDAO.deleteBuyer(buyerId);
            request.setAttribute("successMessage", "Kupac uspješno obrisan");
            request.getRequestDispatcher("buyers.jsp").forward(request, response);
        } catch (BuyerNotFoundException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("buyers.jsp").forward(request, response);
            return;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("buyers.jsp");
    }
}
