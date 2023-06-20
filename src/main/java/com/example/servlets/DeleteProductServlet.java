package com.example.servlets;

import com.example.dao.ProductDAO;

import com.example.exceptions.ProductNotFoundException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));

        ProductDAO productDAO = new ProductDAO();
        request.setAttribute("successMessage", "Proizvod uspješno obrisan");
        request.getRequestDispatcher("products.jsp").forward(request, response);
        try {
            productDAO.deleteProduct(productId);
        } catch (ProductNotFoundException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("products.jsp").forward(request, response);
            return;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("products.jsp");
    }

}
