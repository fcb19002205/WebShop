package com.example.dao;

import com.example.exceptions.BuyerNotFoundException;
import com.example.exceptions.InsufficientBalanceException;
import com.example.model.Buyer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuyerDAO {

    private static final long serialVersionUID = 1L;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/shop";
    private static final String USERNAME = "demo";
    private static final String PASSWORD = "secret";

    public List<Buyer> getAllBuyers() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        List<Buyer> buyers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM buyers")) {

            while (resultSet.next()) {

               int id = resultSet.getInt("bid");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
               double balance = resultSet.getDouble("balance");

                Buyer buyer = new Buyer(id, name, surname, email, balance);
                buyers.add(buyer);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return buyers;
    }

    public void addBuyer(Buyer buyer) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("INSERT INTO buyers (name, surname, email, balance) VALUES (?, ?, ?, ?)")) {

            ps.setString(1, buyer.getName());
            ps.setString(2, buyer.getSurname());
            ps.setString(3, buyer.getEmail());
            ps.setDouble(4, buyer.getBalance());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBuyer(int buyerId) throws ClassNotFoundException, BuyerNotFoundException {
        Class.forName(JDBC_DRIVER);
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("DELETE FROM buyers WHERE bid = ?")) {

            ps.setInt(1, buyerId);
            int rowAffected = ps.executeUpdate();

            if (rowAffected == 0) {
                throw new BuyerNotFoundException("Kupac s ID-om " + buyerId + " nije pronađen. Molimo pokušajte ponovno");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBuyer(int buyerId, String newName, String newSurname, String newEMail, double newBalance) throws ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("UPDATE buyers SET name = ?, surname = ?, email = ?, balance = ? WHERE bid = ?")) {

            ps.setString(1, newName);
            ps.setString(2, newSurname);
            ps.setString(3, newEMail);
            ps.setDouble(4, newBalance);
            ps.setInt(5, buyerId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Buyer getBuyerById(int buyerId) {
        Buyer buyer = null;

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM buyers WHERE bid = ?")) {

            stmt.setInt(1, buyerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("bid");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String email = rs.getString("email");
                double balance = rs.getDouble("balance");

                buyer = new Buyer(id, name, surname, email, balance);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return buyer;
    }

    public void updateBuyerBalance(int buyerId, double newBalance) throws ClassNotFoundException, BuyerNotFoundException, InsufficientBalanceException {
        Class.forName(JDBC_DRIVER);
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement ps = conn.prepareStatement("UPDATE buyers SET balance = ? WHERE bid = ?")) {

            ps.setDouble(1, newBalance);
            ps.setInt(2, buyerId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new BuyerNotFoundException("Kupac nije pronađen!");
            }
            if (newBalance < 0) {
                throw new InsufficientBalanceException("Nedovoljno sredstava!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
