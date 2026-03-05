<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catálogo - Club de Vinilos</title>
</head>
<body>
    <h1>Catálogo de Vinilos</h1>
    <p>Usuario: <c:out value="${sessionScope.usuario.nombre}" /></p>
    
    <table border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td><a href="formulario-vinilo.jsp">Registrar Nuevo Vinilo</a></td>
            <td style="padding-left: 20px;"><a href="mis-prestamos">Mis Préstamos</a></td>
        </tr>
    </table>
    <br>

    <c:if test="${not empty mensaje}"><p style="color:green"><c:out value="${mensaje}"/></p></c:if>
    <c:if test="${not empty error}"><p style="color:red"><c:out value="${error}"/></p></c:if>

    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Título</th>
                <th>Artista</th>
                <th>Año</th>
                <th>Género</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="v" items="${vinilos}">
                <tr>
                    <td><c:out value="${v.idVinilo}" /></td>
                    <td><c:out value="${v.titulo}" /></td>
                    <td><c:out value="${v.artista}" /></td>
                    <td><c:out value="${v.anioLanzamiento}" /></td>
                    <td><c:out value="${v.genero}" /></td>
                    <td>
                        <c:choose>
                            <c:when test="${v.disponible}">Disponible</c:when>
                            <c:otherwise><span style="color:red">Prestado</span></c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${v.disponible}">
                            <a href="solicitarPrestamo?id=${v.idVinilo}">Solicitar</a> |
                        </c:if>
                        <a href="vinilos?accion=editar&id=${v.idVinilo}">Editar</a> |
                        <a href="vinilos?accion=eliminar&id=${v.idVinilo}" onclick="return confirm('¿Eliminar?');">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>