<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Proizvodi</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h1>PROIZVODI</h1>
    <div class="table-container">
            <table class="data-table">
                <tr>
                    <td>
                        <form action="AddProductServlet" method="post">
                             <label>Dodajte novi proizvod u bazu podataka</label><br><br>
                             <label for="name">Naziv proizvoda:</label>
                             <input type="text" id="name" name="name" required><br>
                             <br>
                             <label for="price">Cijena proizvoda:</label>
                             <input type="number" id="price" step="any" name="price" required><br>
                             <br>
                             <label for="quantity">Količina:</label>
                             <input type="number" id="quantity" name="quantity" required><br>
                             <br>
                             <input type="submit" value="Dodaj novi proizvod">
                        </form>
                    </td>
                    <td>
                        <form action="DeleteProductServlet" method="post">
                            <label>Izbrišite proizvod po ID-u iz baze podataka</label><br><br>
                            <label for="productId">ID Proizvoda:</label>
                            <input type="number" id="productId" name="productId" required><br>
                            <br>
                            <input type="submit" value="Izbriši proizvod">
                    </form>
                    </td>
                    <td>
                        <form action="UpdateProductServlet" method="post">
                            <label>Ažurirajte proizvod u bazi podataka</label><br><br>
                            <label for="productId">ID proizvoda:</label>
                            <input type="number" id="productId" name="productId" required><br>
                            <br>
                            <label for="newName">Izmjena naziva proizvoda:</label>
                            <input type="text" id="newName" name="newName" required><br>
                            <label for="newPrice">Cijena:</label>
                            <br>
                            <input type="number" id="newPrice" step="any" name="newPrice" required><br>
                            <label for="newQuantity">Količina:</label>
                            <input type="number" id="newQuantity" name="newQuantity" required><br>
                            <br>
                            <input type="submit" value="Ažuriraj proizvod">
                        </form>
                    </td>
                     <td>
                             <label>Isprintajte proizvode iz baze podataka</label>
                             <form action="AllProductsServlet" method="get">
                             <input type="submit" value="Prikaz svih proizvoda">
                         </form>
                     </td>
                </tr>
            </table>
        </div>

        <% String addMessage = (String) request.getAttribute("addMessage");
        if (addMessage != null) { %>
            <p class="add-message"><%= addMessage %></p>
        <% } %>

        <% String updateMessage = (String) request.getAttribute("updateMessage");
        if (updateMessage != null) { %>
            <p class="update-message"><%= updateMessage %></p>
        <% } %>

        <% String errorMessage = (String) request.getAttribute("errorMessage");
           String successMessage = (String) request.getAttribute("successMessage");
            if (errorMessage != null) { %>
                <p class="error-message"><%= errorMessage %></p>
         <% } else if (successMessage != null) { %>
                <p class="success-message"><%= successMessage %></p>
          <% } %>

    <br>
    <form action="index.jsp">
        <input type="submit" value="Idi na početnu stranicu">
    </form>

</body>
</html>