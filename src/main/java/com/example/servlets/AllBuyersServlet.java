package com.example.servlets;

import com.example.dao.BuyerDAO;
import com.example.model.Buyer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AllBuyersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    BuyerDAO buyerDAO = new BuyerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Buyer> buyers = null;
        try {
            buyers = buyerDAO.getAllBuyers();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("buyers", buyers);
        request.getRequestDispatcher("displayBuyers.jsp").forward(request, response);
    }

}
