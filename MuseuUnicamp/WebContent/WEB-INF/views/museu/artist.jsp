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
			agora	|	exposicoes	|	busca	|	sobre	|	contato
		</nav>
		<main>
			<h1>${artistInfo.nome}</h1>
			<p><label>Nascimento:  </label>${artistInfo.dataNasc}</p>
			<p><label>Falescimento: </label>${artistInfo.dataFalesc}</p>
			<p><label>Origem: </label>${artistInfo.origem}</p>
			<p><b>Biografia</b></p>
			<p>${artistInfo.bio}</p>
		</main>
		<footer>
			MC 437 - grupo 17
		</footer>
	</body>
</html>