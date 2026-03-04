<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Vinilo</title>
</head>
<body>
    <h1><c:out value="${empty vinilo ? 'Registrar Nuevo Vinilo' : 'Editar Vinilo'}" /></h1>
    <form action="vinilos" method="POST">
        
        <input type="hidden" name="id" value="<c:out value='${vinilo.id}'/>">
        
        <label>Título del Disco:</label><br>
        <input type="text" name="titulo" value="<c:out value='${vinilo.titulo}'/>" required><br><br>
        
        <label>Artista o Banda:</label><br>
        <input type="text" name="artista" value="<c:out value='${vinilo.artista}'/>" required><br><br>
        
        <label>Año de Lanzamiento:</label><br>
        <input type="number" name="anio_lanzamiento" value="<c:out value='${vinilo.anioLanzamiento}'/>" required><br><br>
        
        <label>Género Musical:</label><br>
        <input type="text" name="genero" value="<c:out value='${vinilo.genero}'/>" required><br><br>
        
        <button type="submit">Guardar</button>
    </form>
    <br>
    <a href="vinilos">Volver al Catálogo</a>
</body>
</html>