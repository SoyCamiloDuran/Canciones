<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Canciones</title>
</head>
<body>
	<h1>Lista de Canciones</h1>
    <table>
        <tr>
            <th>Título</th>
            <th>Artista</th>
            <th>Detalle</th>
        </tr>
        <c:forEach items="${canciones}" var="cancion">
            <tr>
                <td>${cancion.titulo}</td>
                <td>${cancion.artista.nombre} ${cancion.artista.apellido}</td>
                <td><a href="/canciones/detalle/${cancion.id}">Detalle</a></td>
            </tr>
        </c:forEach>
    </table>
    <div >
		<form action="/canciones/formulario/agregar/{idCancion}" method="GET">
			<button>Agregar Cancion</button>
		</form>
	</div>
	
	<a href="/artistas">Ir a artistas</a>
</body>
</html>