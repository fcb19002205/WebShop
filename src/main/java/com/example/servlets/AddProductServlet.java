package com.example.servlets;

import com.example.dao.ProductDAO;
import com.example.model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);

        ProductDAO productDAO = new ProductDAO();
        request.setAttribute("addMessage", "Proizvod uspješno unesen.");
        request.getRequestDispatcher("products.jsp").forward(request, response);
        try {
            productDAO.addProduct(product);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("products.jsp");
    }

}
