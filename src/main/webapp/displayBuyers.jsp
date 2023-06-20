<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Prikaži kupce</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h1>Kupci</h1>
    <div class="table-container buyer-details">
    <table class="data-table">
        <thead>
             <tr>
                 <th>ID</th>
                 <th>Ime</th>
                 <th>Prezime</th>
                 <th>Email</th>
                 <th>Raspoloživo stanje</th>
              </tr>
        </thead>
        <tbody>
            <c:forEach var="buyer" items="${buyers}">
            <tr>
               <td>${buyer.id}</td>
               <td>${buyer.name}</td>
               <td>${buyer.surname}</td>
               <td>${buyer.email}</td>
               <td>${buyer.balance}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </div>

    <form action="buyers.jsp">
            <input type="submit" value="Nazad">
    </form>

</body>
</html>