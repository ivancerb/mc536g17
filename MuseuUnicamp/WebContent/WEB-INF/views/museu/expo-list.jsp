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
			<div id="expo-list-current-expo">
				<h2>Exposicoes Correntes</h2>			
				<c:forEach items="${activeExposList}" var="activeExpo">
					<h3>${activeExpo.nome} </h3>
					<a href="exposicoes/${activeExpo.idExposicao}">Visitar esta exposição</a>
					<p>Inicio: ${activeExpo.dataInicio}</p>
					<p>Fim: ${activeExpo.dataFim}</p>
					<hr/>			
				</c:forEach>    
			</div>
			<div id="expo-list-all-expos">
				<h2>Todas as exposicoes do museu</h2>	
				<c:forEach items="${expoList}" var="expo">
					<p>Nome: ${expo.nome} <a href="exposicoes/${expo.idExposicao}">Ver detalhes desta exposição</a></p>
					<p>Inicio: ${expo.dataInicio}</p>
					<p>Fim: ${expo.dataFim}</p>
					<hr/>			
				</c:forEach>    
			</div>
		</main>
		<footer>
			MC 437 - grupo 17
		</footer>
	</body>
</html>