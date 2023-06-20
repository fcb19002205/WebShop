<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shop</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h1>Dobrodošli u shop managament</h1>
    <ul>
        <h2>Za rad sa kupcima, proizvodima ili prodajom, kliknite da ponuđeno:</h2>
        <li>
            <form action="buyers.jsp">
                <input type="submit" value="Kupci">
            </form>
        </li>
        <li>
            <form action="products.jsp">
                <input type="submit" value="Proizvodi">
            </form>
        </li>
        <li>
            <form action="sales.jsp">
                <input type="submit" value="Prodaja">
            </form>
        </li>
    </ul>
</body>
</html>
