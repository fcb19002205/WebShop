package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SaleDAO {

    private static final long serialVersionUID = 1L;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/shop";
    private static final String USERNAME = "demo";
    private static final String PASSWORD = "secret";

    public void createSale(int buyerId, int productId, int quantity, double totalPrice) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            conn.setAutoCommit(false);

            try (PreparedStatement saleStmt = conn.prepareStatement("INSERT INTO sales (buyer_id, product_id, quantity, total_price) VALUES (?, ?, ?, ?)");
                 PreparedStatement buyerStmt = conn.prepareStatement("UPDATE buyers SET balance = balance - ? WHERE bid = ?");
                 PreparedStatement productStmt = conn.prepareStatement("UPDATE products SET quantity = quantity - ? WHERE pid = ?")) {

                saleStmt.setInt(1, buyerId);
                saleStmt.setInt(2, productId);
                saleStmt.setInt(3, quantity);
                saleStmt.setDouble(4, totalPrice);
                saleStmt.executeUpdate();

                buyerStmt.setDouble(1, totalPrice);
                buyerStmt.setInt(2, buyerId);
                buyerStmt.executeUpdate();

                productStmt.setInt(1, quantity);
                productStmt.setInt(2, productId);
                productStmt.executeUpdate();

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }

            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
