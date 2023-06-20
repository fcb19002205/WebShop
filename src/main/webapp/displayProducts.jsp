<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Prikaži proizvode</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h1>Proizvodi</h1>
    <div class="table-container product-details">
    <table class="data-table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Naziv proizvoda</th>
                <th>Cijena</th>
                <th>Količina</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="product" items="${products}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.quantity}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </div>

    <form action="products.jsp">
              <input type="submit" value="Nazad">
     </form>

</body>
</html>