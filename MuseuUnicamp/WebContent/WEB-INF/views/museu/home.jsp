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
		<link rel="stylesheet" href="museu.css" />
	</head>
	<body>
		<header>
			<img src="<c:url value="/resources/img/logoinicialmav.jpg"/>" />
		</header>
		<nav>
			<a href="./home">Agora</a>	|	<a href="./exposicoes">Exposicoes</a>	|	<a href="./search">Busca</a>	|	<a href="./sobre">Sobre</a>	|	<a href="./contato">Contato</a>
		</nav>
		<main>
			<div id="busca-home">
				<h2>Busca em nosso catálogo</h2>
				<h3><a href="search">Buscar</a></h3>
			</div>
			<div id="home-expo-current">
				<h1>Exposicoes Correntes</h1>			
				<c:forEach items="${activeExposList}" var="expo">
					<h2>${expo.nome} </h2>
					<a href="exposicoes/${expo.idExposicao}">Visitar esta exposição</a>
					<p>Inicio: ${expo.dataInicio}</p>
					<p>Fim: ${expo.dataFim}</p>
					<hr/>			
				</c:forEach>    
			</div>
		
			<p> LOREM IPSUM </p>
			<p style="color:red"> ${testeBd}</p>
		</main>
		<footer>
			MC 437 - grupo 17
		</footer>
	</body>
</html>