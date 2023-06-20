package com.example.servlets;

import com.example.dao.BuyerDAO;
import com.example.dao.ProductDAO;
import com.example.dao.SaleDAO;

import com.example.exceptions.BuyerNotFoundException;
import com.example.exceptions.InsufficientBalanceException;
import com.example.model.Buyer;
import com.example.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddSaleServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int buyerId = Integer.parseInt(request.getParameter("buyerId"));
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));


        ProductDAO productDAO = new ProductDAO();
        SaleDAO saleDAO = new SaleDAO();
        BuyerDAO buyerDAO = new BuyerDAO();

        Product product;
        try {
            product = productDAO.getProductById(productId);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Buyer buyer = buyerDAO.getBuyerById(buyerId);

        if (product != null && buyer != null) {
            if (product.getQuantity() >= quantity) {

                double totalPrice = product.getPrice() * quantity;

                if (buyer.getBalance() >= totalPrice) {
                    try {
                        saleDAO.createSale(buyerId, productId, quantity, totalPrice);
                        double newBalance = buyer.getBalance() - totalPrice;
                        buyerDAO.updateBuyerBalance(buyerId, newBalance);
                        request.setAttribute("totalPrice", totalPrice);
                        request.setAttribute("message", "Prodaja uspješno izvršena");
                    } catch (ClassNotFoundException | InsufficientBalanceException | BuyerNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    request.setAttribute("message", "Kupac nema dovoljno sredstava za kupovinu.");
                }
            } else {
                request.setAttribute("message", "Nedovoljan broj proizvoda.");
            }
        } else {
            request.setAttribute("message", "Nepoznat kupac ili proizvod.");
        }

        request.getRequestDispatcher("sales.jsp").forward(request, response);
    }
}
