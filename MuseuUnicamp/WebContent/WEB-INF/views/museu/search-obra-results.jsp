<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>

<h2> Resultados da busca</h2>
<br/>
<%-- BEGIN - PINTURAS --%>
<div id="obra-search-pintura-results">
	<c:if test="${not empty searchPinturaResultsList}">
		<h3> Pinturas </h3>
		<c:forEach items="${searchPinturaResultsList}" var="pinturaResult">
			<p>Titulo: ${pinturaResult.pintura.titulo} <a href="#">Ver artista</a></p>
			<p>Autor: ${pinturaResult.artista.nome}</p>
			<p>Estilo: ${pinturaResult.estilo.nome}</p>
			<hr/>			
		</c:forEach>    
 	</c:if>    
</div>
<%-- END - PINTURAS --%>

<%-- BEGIN - ESCULTURAS --%>
<div id="obra-search-escultura-results">
	<c:if test="${not empty searchEsculturaResultsList}">
		<h3> Esculturas </h3>
		<c:forEach items="${searchEsculturaResultsList}" var="esculturaResult">
			<p>Titulo: ${esculturaResult.escultura.titulo} <a href="#">Ver artista</a></p>
			<p>Autor: ${esculturaResult.artista.nome}</p>
			<p>Estilo: ${esculturaResult.estilo.nome}</p>
			<hr/>			
		</c:forEach>    
 	</c:if>  
</div>
<%-- END - ESCULTURAS --%>

<%-- BEGIN - AUDIOVISUAL --%>
<div id="obra-search-audiovisual-results">
	<c:if test="${not empty searchResultsList}">
		<h3> Audiovisual </h3>
		<c:forEach items="${searchAudiovisualResultsList}" var="audiovisualResult">
			<p>Titulo: ${audiovisualResult.audiovisual.titulo} <a href="#">Ver artista</a></p>
			<p>Autor: ${audiovisualResult.artista.nome}</p>
			<p>Estilo: ${audiovisualResult.estilo.nome}</p>
			<p>Tipo de midia: ${audiovisualResult.audiovisual.tipo_midia}</p>
			<hr/>			
		</c:forEach>    
 	</c:if> 
</div>
<%-- END - AUDIOVISUAL --%>

<div id="obra-search-no-results">
    <c:if test="${empty searchPinturaResultsList and empty searchEsculturaResultsList and empty searchAudiovisualResultsList}">
        <p style="color:red"> Não há resulados para essa busca. </p>
    </c:if> 
</div>