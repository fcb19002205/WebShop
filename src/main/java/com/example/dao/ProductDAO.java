package com.example.dao;

import com.example.exceptions.InsufficientQuantityException;
import com.example.exceptions.ProductNotFoundException;
import com.example.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private static final long serialVersionUID = 1L;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/shop";
    private static final String USERNAME = "demo";
    private static final String PASSWORD = "secret";

    public ProductDAO() {

    }

    public List<Product> getAllProducts() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        List<Product> products = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("pid"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                products.add(product);
            }

            conn.close();
            statement.close();
            resultSet.close();
            return products;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


        public void addProduct(Product product) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)")) {

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getQuantity());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productId) throws ClassNotFoundException, ProductNotFoundException {
        Class.forName(JDBC_DRIVER);
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("DELETE FROM products WHERE pid = ?")) {

            ps.setInt(1, productId);
            int rowAffected = ps.executeUpdate();

            if (rowAffected == 0) {
                throw new ProductNotFoundException("Proizvod s ID-om " + productId + " nije pronađen. Molimo pokušajte ponovno.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(int productId, String newName, double newPrice, int newQuantity) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("UPDATE products SET name = ?, price = ?, quantity = ? WHERE pid = ?")) {

            ps.setString(1, newName);
            ps.setDouble(2, newPrice);
            ps.setInt(3, newQuantity);
            ps.setInt(4, productId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product getProductById(int productId) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Product product = null;

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM products WHERE pid = ?")) {

            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("pid"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setQuantity(resultSet.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
}
