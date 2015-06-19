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
			<a href="../home">Agora</a>	|	<a href="../exposicoes">Exposicoes</a>	|	<a href="../search">Busca</a>	|	<a href="../sobre">Sobre</a>	|	<a href="../contato">Contato</a>
		</nav>
		<main>
			<h1>${result.titulo}</h1>
			<div id="artwork-photo">
				<c:choose>
					<c:when test="${empty result.pathImagem}">
						<img src="<c:url value="/resources/img/obras/indisponivel.png"/>" />
					</c:when>
					<c:otherwise>
						<img src="<c:url value="/resources/img/obras/${result.pathImagem}"/>" />
					</c:otherwise>
				</c:choose>
			</div>
			
			<p><label>Autor: </label>${nomeAutor}   <a href="../artist/${idAutor}">Ver mais sobre esse artista</a></p>
			<p><label>Estilo: </label>${nomeEstilo} <a href="../estilo/${idEstilo}">Ver mais sobre esse estilo</a></p>
			<p><label>Data:  </label>${result.data}</p>
			<p><label>Descrição: </label>${result.descricao}</p>
			<c:if test="${isPintura}">
				<p><label>Material: </label>${result.materiais}</p>
				<p><label>Altura: </label>${result.altura}</p>
				<p><label>Largura: </label>${result.largura}</p>
			</c:if>
			<c:if test="${isEscultura}">
				<p><label>Material: </label>${result.materiais}</p>
				<p><label>Altura: </label>${result.altura}</p>
				<p><label>Largura: </label>${result.largura}</p>
				<p><label>Profundidade: </label>${result.profundidade}</p>
			</c:if>
			<c:if test="${isAudiovisual}">
				<p><label>Tipo de midia: </label>${result.tipo_midia}</p>
			</c:if>
		</main>
		<footer>
			MC 437 - grupo 17
		</footer>
	</body>
</html>