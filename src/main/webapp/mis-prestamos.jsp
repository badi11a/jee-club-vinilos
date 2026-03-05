<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Mis Préstamos</title></head>
<body>
    <h1>Mis Préstamos Activos</h1>
    <a href="vinilos">Volver al Catálogo</a>
    <br><br>
    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
            <tr><th>Vinilo</th><th>Fecha Préstamo</th><th>Acción</th></tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${prestamos}">
                <tr>
                    <td><c:out value="${p.tituloVinilo}" /></td>
                    <td><c:out value="${p.fechaPrestamo}" /></td>
                    <td>
                        <a href="devolverVinilo?idVinilo=${p.idVinilo}">Devolver</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>