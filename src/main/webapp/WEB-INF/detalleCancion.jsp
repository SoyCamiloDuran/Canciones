<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Detalle Cancion</title>
</head>
<body>
    <h1>Detalle de Canción</h1>
    <p>Título: ${cancion.titulo}</p>
    <p>Artista: ${cancion.artista}</p>
    <p>Álbum: ${cancion.album}</p>
    <p>Género: ${cancion.genero}</p>
    <p>Idioma: ${cancion.idioma}</p>
    <a href="/canciones">Volver a lista de canciones</a>
</body>
</html>