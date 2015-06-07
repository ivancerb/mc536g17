<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>

<h2> Resultados da busca</h2>
<br/>
<%-- BEGIN - TABLE --%>
<div id="search-results-table">
	<c:choose>
		<c:when test="${not empty searchResultsList}">
			<c:forEach items="${searchResultsList}" var="result">
				<p>Nome: ${result.artista.nome} <a href="#">Ver artista</a></p>
				<p>Pais: ${result.artista.origem}</p>
				<p>Estilo: ${result.estilo.nome}</p>
				<hr/>			
			</c:forEach>    
	 	</c:when>
	    <c:otherwise>
	        <p style="color:red"> Não há resulados para essa busca. </p>
	    </c:otherwise>
	</c:choose>    
</div>
<%-- END - TABLE --%>