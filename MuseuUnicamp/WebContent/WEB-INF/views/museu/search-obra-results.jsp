<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<script>
	function showObraInfo(tipoObra, idObra, idAutor, nomeAutor, idEstilo, nomeEstilo){
		require(["dojo/dom","dojo/request", "dojo/domReady!"], function(dom, request){
			var resultDiv = dom.byId("search-results"); //the id to be filled with results
			
	   		// Message while processing request
	        resultDiv.innerHTML = "Aguarde enquanto sua consulta esta sendo processada";
	
			var url = "process-artist-search"; //base url to be called; form parameters added below
			alert("here1");

			var nome =  dom.byId("artist-search-name").value;
			alert("here2");
			var pais = dom.byId("artist-search-country").value;
			alert("here2");
			var estilo = dom.byId("artist-search-style").options[dom.byId('artist-search-style').selectedIndex].text;
			var dataInicioChecked =  dom.byId("artist-search-data-checkbox-begin").checked;
			var dataInicio = dom.byId("artist-search-data-begin").value;
			if(dataInicio=="" || dataInicio=="undefined"){
				dataInicio=-1;
			}
			var dataFimChecked = dom.byId("artist-search-data-checkbox-end").checked;
			var dataFim = dom.byId("artist-search-data-end").value;
			if(dataFim=="" || dataFim=="undefined"){
				dataFim=-1;
			}
			
			alert("nome" + nome + "pais" + pais + "estilo" + estilo +  "dataInicioChecked" + dataInicioChecked +
				"dataInicio" + dataInicio + "dataFimChecked" + dataFimChecked +  "dataFim" + dataFim);
			
			//append form values to the base url
			url +="?nome=" + nome +
				"&pais=" + pais +
				"&estilo=" + estilo +
				"&dataInicioChecked=" + dataInicioChecked +
				"&dataInicio=" + dataInicio +
				"&dataFimChecked=" + dataFimChecked +
				"&dataFim=" + dataFim;
				
	
			// AJAX call
	   		request.post(url, {
	            handleAs: "text"
    		}).then(
       		function(text){
	         	resultDiv.innerHTML = text; //fill div with results
       		},
       		function(error){
           		resultDiv.innerHTML = "An error ocurred. Please refresh the page | " + error; //error
       		});
		});
	}

</script>

<h2> Resultados da busca</h2>
<br/>
<%-- BEGIN - PINTURAS --%>
<div id="obra-search-pintura-results">
	<c:if test="${not empty searchPinturaResultsList}">
		<h3> Pinturas </h3>
		<c:forEach items="${searchPinturaResultsList}" var="pinturaResult">
			<p>Titulo: ${pinturaResult.pintura.titulo} <a href="pinturas/${pinturaResult.pintura.idObra}?idAutor=${pinturaResult.artista.idArtista}&nomeAutor=${pinturaResult.artista.nome}&idEstilo=${pinturaResult.estilo.idEstilo}&nomeEstilo=${pinturaResult.estilo.nome}">Ver detalhes da obra</a></p>
			<p>Autor: ${pinturaResult.artista.nome}    <a href="artist/${pinturaResult.artista.idArtista}">Ver detalhes do artista</a></p>
			<p>Estilo: ${pinturaResult.estilo.nome}    <a href="estilo/${pinturaResult.estilo.idEstilo}">Ver mais sobre esse estilo</a></p>
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
			<p>Titulo: ${esculturaResult.escultura.titulo} <a href="esculturas/${esculturaResult.escultura.idObra}?idAutor=${esculturaResult.artista.idArtista}&nomeAutor=${esculturaResult.artista.nome}&idEstilo=${esculturaResult.estilo.idEstilo}&nomeEstilo=${esculturaResult.estilo.nome}">Ver detalhes da obra</a></p>
			<p>Autor: ${esculturaResult.artista.nome}      <a href="artist/${esculturaResult.artista.idArtista}">Ver detalhes do artista</a></p>
			<p>Estilo: ${esculturaResult.estilo.nome}      <a href="estilo/${esculturaResult.estilo.idEstilo}">Ver mais sobre esse estilo</a></p>
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
			<p>Titulo: ${audiovisualResult.audiovisual.titulo} <a href="audiovisual/${audiovisualResult.audiovisual.idObra}?idAutor=${audiovisualResult.artista.idArtista}&nomeAutor=${audiovisualResult.artista.nome}&idEstilo=${audiovisualResult.estilo.idEstilo}&nomeEstilo=${audiovisualResult.estilo.nome}">Ver detalhes da obra</a></p>
			<p>Autor: ${audiovisualResult.artista.nome}        <a href="artist/${audiovisualResult.artista.idArtista}">Ver detalhes do artista</a></p>
			<p>Estilo: ${audiovisualResult.estilo.nome}        <a href="estilo/${audiovisualResult.estilo.idEstilo}">Ver mais sobre esse estilo</a></p>
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