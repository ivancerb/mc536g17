<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Home - Museu Unicamp</title>
		<!-- BEGIN - Including Dojo -->
		<script src="//ajax.googleapis.com/ajax/libs/dojo/1.9.3/dojo/dojo.js"></script>
		<!-- END - Including Dojo -->
	</head>
	<body>
		<header>
			<img src="<c:url value="/resources/img/logoinicialmav.jpg"/>" />
		</header>
		<nav>
			<a href="./home">Agora</a>	|	<a href="./exposicoes">Exposicoes</a>	|	<a href="./search">Busca</a>	|	<a href="./sobre">Sobre</a>	|	<a href="./contato">Contato</a>
		</nav>
		<main>
			<h1>Estilo: ${estiloInfo.nome}</h1>
			<p><label>Inicio:  </label>${estiloInfo.inicio}</p>
			<p><label>Fim: </label>${estiloInfo.fim}</p>
			<p><b>Descricao:</b></p>
			<p>${estiloInfo.descricao}</p>
		</main>
		<footer>
			MC 437 - grupo 17
		</footer>
	</body>
</html>