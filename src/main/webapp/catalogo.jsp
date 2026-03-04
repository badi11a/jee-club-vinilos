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
    <p>Usuario: <c:out value="${sessionScope.usuarioLogueado}" /></p>
    <a href="formulario-vinilo.jsp">Registrar Nuevo Vinilo</a>
    <br><br>
    <table border="1" cellpadding="5" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Título</th>
                <th>Artista</th>
                <th>Año</th>
                <th>Género</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="v" items="${vinilos}">
                <tr>
                    <td><c:out value="${v.id}" /></td>
                    <td><c:out value="${v.titulo}" /></td>
                    <td><c:out value="${v.artista}" /></td>
                    <td><c:out value="${v.anioLanzamiento}" /></td>
                    <td><c:out value="${v.genero}" /></td>
                    <td>
                        <a href="vinilos?accion=editar&id=${v.id}">Editar</a> | 
                        <a href="vinilos?accion=eliminar&id=${v.id}" onclick="return confirm('¿Seguro que deseas eliminar este registro?');">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>