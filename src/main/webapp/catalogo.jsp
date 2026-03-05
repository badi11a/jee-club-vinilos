<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Catálogo - Club de Vinilos</title></head>
<body>
    <h1>Catálogo de Vinilos</h1>
    <p>Usuario: <c:out value="${sessionScope.usuario.nombre}" /></p>
    <a href="formulario-vinilo.jsp">Registrar Nuevo</a> | <a href="mis-prestamos">Mis Préstamos</a>
    <br><br>
    
    <%-- Solo se mantiene el mensaje de error para validaciones de seguridad --%>
    <c:if test="${not empty error}"><p style="color:red"><c:out value="${error}"/></p></c:if>

    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
            <tr><th>ID</th><th>Título</th><th>Artista</th><th>Año</th><th>Género</th><th>Acciones</th></tr>
        </thead>
        <tbody>
            <c:forEach var="v" items="${vinilos}">
                <tr>
                    <td>${v.idVinilo}</td><td>${v.titulo}</td><td>${v.artista}</td>
                    <td>${v.anioLanzamiento}</td><td>${v.genero}</td>
                    <td>
                        <c:choose>
                            <c:when test="${v.disponible}">
                                <a href="solicitarPrestamo?id=${v.idVinilo}">Solicitar</a> |
                                <a href="vinilos?accion=editar&id=${v.idVinilo}">Editar</a> |
                                <a href="vinilos?accion=eliminar&id=${v.idVinilo}" onclick="return confirm('¿Eliminar?');">Eliminar</a>
                            </c:when>
                            <c:otherwise>
                                <span style="color: grey; font-style: italic;">En préstamo</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>