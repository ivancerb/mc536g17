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
			<h1> Exposição: ${expo.nome} </h1>
			<div id="expo-info">
				<p>Inicio: ${expo.dataInicio}</p>
				<p>Fim: ${expo.dataFim}</p>
				<p>Curador: ${nomeCurador}</p>
				<p><b>Descrição</b></p>
				<p>${expo.descricao}</p>
			</div>
			<div id="expo-obras">
				<h2>Obras</h2>
				<%-- BEGIN - PINTURAS --%>
				<div id="obra-expo-pintura-results">
					<c:if test="${not empty expoPinturaResultsList}">
						<h3> Pinturas </h3>
						<c:forEach items="${expoPinturaResultsList}" var="pinturaResult">
							<p>Titulo: ${pinturaResult.pintura.titulo} <a href="../pinturas/${pinturaResult.pintura.idObra}?idAutor=${pinturaResult.artista.idArtista}&nomeAutor=${pinturaResult.artista.nome}&idEstilo=${pinturaResult.estilo.idEstilo}&nomeEstilo=${pinturaResult.estilo.nome}">Ver detalhes da obra</a></p>
							<p>Autor: ${pinturaResult.artista.nome}    <a href="../artist/${pinturaResult.artista.idArtista}">Ver detalhes do artista</a></p>
							<p>Estilo: ${pinturaResult.estilo.nome}    <a href="../estilo/${pinturaResult.estilo.idEstilo}">Ver mais sobre esse estilo</a></p>
							<hr/>			
						</c:forEach>    
				 	</c:if>    
				</div>
				<%-- END - PINTURAS --%>
				
				<%-- BEGIN - ESCULTURAS --%>
				<div id="obra-expo-escultura-results">
					<c:if test="${not empty expoEsculturaResultsList}">
						<h3> Esculturas </h3>
						<c:forEach items="${expoEsculturaResultsList}" var="esculturaResult">
							<p>Titulo: ${esculturaResult.escultura.titulo} <a href="../esculturas/${esculturaResult.escultura.idObra}?idAutor=${esculturaResult.artista.idArtista}&nomeAutor=${esculturaResult.artista.nome}&idEstilo=${esculturaResult.estilo.idEstilo}&nomeEstilo=${esculturaResult.estilo.nome}">Ver detalhes da obra</a></p>
							<p>Autor: ${esculturaResult.artista.nome}      <a href="../artist/${esculturaResult.artista.idArtista}">Ver detalhes do artista</a></p>
							<p>Estilo: ${esculturaResult.estilo.nome}      <a href="../estilo/${esculturaResult.estilo.idEstilo}">Ver mais sobre esse estilo</a></p>
							<hr/>			
						</c:forEach>    
				 	</c:if>  
				</div>
				<%-- END - ESCULTURAS --%>
				
				<%-- BEGIN - AUDIOVISUAL --%>
				<div id="obra-expo-audiovisual-results">
					<c:if test="${not empty expoResultsList}">
						<h3> Audiovisual </h3>
						<c:forEach items="${expoAudiovisualResultsList}" var="audiovisualResult">
							<p>Titulo: ${audiovisualResult.audiovisual.titulo} <a href="../audiovisual/${audiovisualResult.audiovisual.idObra}?idAutor=${audiovisualResult.artista.idArtista}&nomeAutor=${audiovisualResult.artista.nome}&idEstilo=${audiovisualResult.estilo.idEstilo}&nomeEstilo=${audiovisualResult.estilo.nome}">Ver detalhes da obra</a></p>
							<p>Autor: ${audiovisualResult.artista.nome}        <a href="../artist/${audiovisualResult.artista.idArtista}">Ver detalhes do artista</a></p>
							<p>Estilo: ${audiovisualResult.estilo.nome}        <a href="../estilo/${audiovisualResult.estilo.idEstilo}">Ver mais sobre esse estilo</a></p>
							<p>Tipo de midia: ${audiovisualResult.audiovisual.tipo_midia}</p>
							<hr/>			
						</c:forEach>    
				 	</c:if> 
				</div>
				<%-- END - AUDIOVISUAL --%>
			</div>	
		</main>
		<footer>
			MC 437 - grupo 17
		</footer>
	</body>
</html>