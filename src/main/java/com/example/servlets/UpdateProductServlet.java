package com.example.servlets;

import com.example.dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

public class UpdateProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        String newName = request.getParameter("newName");
        double newPrice = Double.parseDouble(request.getParameter("newPrice"));
        int newQuantity = Integer.parseInt(request.getParameter("newQuantity"));

        ProductDAO productDAO = new ProductDAO();
        request.setAttribute("updateMessage", "Proizvod uspješno ažuriran");
        request.getRequestDispatcher("products.jsp").forward(request, response);
        try {
            productDAO.updateProduct(productId, newName, newPrice, newQuantity);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("products.jsp");
    }

}
