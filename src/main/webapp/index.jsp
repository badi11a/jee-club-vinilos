<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Acceso - Club de Vinilos</title>
</head>
<body>
    <h1>Ingreso de Socios</h1>
    <c:if test="${param.error == '1'}">
        <p style="color:red; font-weight:bold;">Credenciales incorrectas.</p>
    </c:if>
    <form action="login" method="POST">
        <label>Email:</label><br>
        <input type="email" name="email" required><br><br>
        
        <label>Contraseña:</label><br>
        <input type="password" name="password" required><br><br>
        
        <button type="submit">Ingresar</button>
    </form>
</body>
</html>