<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulario Vinilo</title>
</head>
<body>
    <h1><c:out value="${vinilo != null ? 'Editar' : 'Registrar'}" /> Vinilo</h1>
    
    <form action="vinilos" method="post">
        <input type="hidden" name="idVinilo" value="${vinilo.idVinilo}">
        
        <table border="0" cellpadding="5">
            <tr>
                <td><label for="titulo">Título:</label></td>
                <td><input type="text" id="titulo" name="titulo" value="${vinilo.titulo}" required></td>
            </tr>
            <tr>
                <td><label for="artista">Artista:</label></td>
                <td><input type="text" id="artista" name="artista" value="${vinilo.artista}" required></td>
            </tr>
            <tr>
                <td><label for="anioLanzamiento">Año:</label></td>
                <td><input type="number" id="anioLanzamiento" name="anioLanzamiento" value="${vinilo.anioLanzamiento}" required></td>
            </tr>
            <tr>
                <td><label for="genero">Género:</label></td>
                <td><input type="text" id="genero" name="genero" value="${vinilo.genero}"></td>
            </tr>
            <input type="hidden" name="disponible" value="${vinilo.idVinilo != null ? vinilo.disponible : true}">
            <tr>
                <td></td>
                <td>
                    <button type="submit">Guardar</button>
                    <a href="vinilos">Cancelar</a>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>