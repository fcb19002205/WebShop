<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Kupci</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h1>KUPCI</h1>
    <div class="table-container">
        <table class="data-table">
            <tr>
                <td>
                    <form action="AddBuyerServlet" method="post">
                        <label>Dodajte novog kupca u bazu podataka</label><br><br>
                        <label for="name">Ime:</label>
                        <input type="text" id="name" name="name" required>
                        <br>
                        <label for="surname">Prezime:</label>
                        <input type="text" id="surname" name="surname" required>
                        <br>
                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" required>
                        <br>
                        <label for="balance">Raspoloživo stanje:</label>
                        <input type="number" id="balance" step="any" name="balance" required>
                        <br>
                        <input type="submit" value="Dodaj novog kupca">
                    </form>
                </td>
                <td>
                    <form action="DeleteBuyerServlet" method="post">
                        <label>Izbrišite kupca po ID-u iz baze podataka</label><br><br>
                        <label for="buyerId">ID Kupca:</label>
                        <input type="number" id="buyerId" name="buyerId" required>
                        <br>
                        <input type="submit" value="Izbriši kupca">
                    </form>
                </td>
                <td>
                    <form action="UpdateBuyerServlet" method="post">
                        <label>Ažurirajte kupca u bazi podataka</label><br><br>
                        <label for="buyerId">ID kupca:</label>
                        <input type="number" id="buyerId" name="buyerId" required>
                        <br>
                        <label for="newName">Novo ime:</label>
                        <input type="text" id="newName" name="newName" required>
                        <br>
                        <label for="newSurname">Novo ime:</label>
                        <input type="text" id="newSurname" name="newSurname" required>
                         <br>
                        <label for="newEmail">Novi Email:</label>
                        <input type="email" id="newEmail" name="newEmail" required>
                        <br>
                        <label for="newBalance">Raspoloživo stanje:</label>
                        <input type="number" id="newBalance" step="any" name="newBalance" required>
                        <br>
                        <input type="submit" value="Ažuriranje kupca">
                    </form>
                </td>
                <td>
                    <label>Isprintajte kupce iz baze podataka</label>
                    <form action="AllBuyersServlet" method="get">
                        <input type="submit" value="Prikaz svih kupaca">
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