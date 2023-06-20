<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.dao.BuyerDAO" %>
<%@ page import="com.example.dao.ProductDAO" %>
<%@ page import="com.example.dao.SaleDAO" %>
<%@ page import="com.example.model.Buyer" %>
<%@ page import="com.example.model.Product" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sales</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <script>
        function updatePrice() {
            var productId = document.getElementById("productId").value;
            var selectedOption = document.querySelector('#productId option:checked');
            var price = selectedOption.getAttribute("data-price");

            document.getElementById("price").value = price;
            calculateTotal();
            window.onload = updatePrice;
        }

        function calculateTotal() {
            var price = document.getElementById("price").value;
            var quantity = document.getElementById("quantity").value;
            var total = parseFloat(price) * parseInt(quantity);
            document.getElementById("total").value = total.toFixed(2);
        }
    </script>
</head>
<body>
    <h1>Prodaja</h1>

    <%
        BuyerDAO buyerDAO = new BuyerDAO();
        ProductDAO productDAO = new ProductDAO();
        List<Buyer> buyers = buyerDAO.getAllBuyers();
        List<Product> products = productDAO.getAllProducts();
    %>

    <h2>Prodaja proizvoda</h2>
    <form action="AddSaleServlet" method="post">
        <label for="buyerId">Kupac:</label>
        <select id="buyerId" name="buyerId">
            <% for (Buyer buyer : buyers) { %>
                <option value="<%= buyer.getId() %>"><%= buyer.getName() %> <%= buyer.getSurname() %></option>
            <% } %>
        </select><br>
        <label for="productId">Proizvod:</label>
        <select id="productId" name="productId" onchange="updatePrice()">
            <% for (Product product : products) { %>
                <option value="<%= product.getId() %>" data-price="<%= product.getPrice() %>"><%= product.getName() %></option>
            <% } %>
        </select><br>
        <label for="price">Jedinična cijena:</label>
        <input type="text" id="price" name="price" readonly><br>
        <label for="quantity">Količina:</label>
        <input type="number" id="quantity" name="quantity" required oninput="calculateTotal()"><br>
        <label for="total">Ukupno:</label>
        <input type="text" id="total" name="total" readonly><br>
        <input type="submit" value="Izvrši">
    </form>

    <h2>Dostupni proizvodi</h2>
    <ul class="product-list">
        <% for (Product product : products) { %>
            <li><%= product.getName() %> - <%= product.getQuantity() %> available</li>
        <% } %>
    </ul>

    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
    <p><%= message %></p>
    <%
        }
    %>

    <form action="index.jsp">
        <input type="submit" value="Idi na početnu stranicu">
    </form>
</body>
</html>