package com.example.servlets;

import com.example.dao.BuyerDAO;
import com.example.model.Buyer;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddBuyerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        double balance = Double.parseDouble(request.getParameter("balance"));

        Buyer buyer = new Buyer();
        buyer.setName(name);
        buyer.setSurname(surname);
        buyer.setEmail(email);
        buyer.setBalance(balance);

        BuyerDAO buyerDAO = new BuyerDAO();
        request.setAttribute("addMessage", "Kupac uspješno unesen.");
        request.getRequestDispatcher("buyers.jsp").forward(request, response);
        try {
            buyerDAO.addBuyer(buyer);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("buyers.jsp");
    }

}
