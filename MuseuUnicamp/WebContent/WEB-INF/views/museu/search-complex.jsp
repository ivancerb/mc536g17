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
		
		<!-- BEGIN - Script to process search -->
		<script>
			function processSearch(){
				require(["dojo/dom","dojo/request", "dojo/domReady!"], function(dom, request){
					var resultDiv = dom.byId("search-results"); //the id to be filled with results
					
			   		// Message while processing request
			        resultDiv.innerHTML = "Aguarde enquanto sua consulta esta sendo processada";
			
					var url = "process-artist-search"; //base url to be called; form parameters added below
					
					var artist =  dom.byId("search-artist").value;
					var artwork = dom.byId("search-artwork").value;
					var material = dom.byId("search-material").value;

					//append form values to the base url
					url +="?artist=" + artist +
						"&artwork=" + artwork +
						"&material=" + material;
			
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
		<!-- END - Script to process search -->
		
		<!-- BEGIN - Script to process artist search -->
		<script>
			function processArtistaSearch(){
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
		<!-- END - Script to process artist search -->
		
		<!-- BEGIN - Script to process artwork search -->
		<script>
			function processObraSearch(){
				require(["dojo/dom","dojo/request", "dojo/domReady!"], function(dom, request){
					var resultDiv = dom.byId("search-results"); //the id to be filled with results
					
			   		// Message while processing request
			        resultDiv.innerHTML = "Aguarde enquanto sua consulta esta sendo processada";
			
					var url = "process-obra-search"; //base url to be called; form parameters added below
					alert("here1");

					var titulo =  dom.byId("obra-search-title").value;
					alert("here2");
					var autor = dom.byId("obra-search-author").value;
					alert("here2.5");
					var isPintura = dom.byId("obra-search-tipo-checkbox-pintura").checked;
					var isEscultura = dom.byId("obra-search-tipo-checkbox-escultura").checked;
					var isAudiovisual = dom.byId("obra-search-tipo-checkbox-audiovisual").checked;
					alert("here3");
					var materialSearchChecked = dom.byId("obra-search-material-checkbox").checked;
					var material = dom.byId("obra-search-material-value").value;
					alert("here4");
					var tipoMidiaChecked = dom.byId("obra-search-midia-checkbox").checked;
					var tipoMidia = dom.byId("obra-search-midia-value").value;
					alert("here5");
					var pais = dom.byId("obra-search-country").value;
					
					var estilo = dom.byId("obra-search-style").options[dom.byId('obra-search-style').selectedIndex].text;
					alert("here6");
					var dataInicioChecked =  dom.byId("obra-search-data-checkbox-begin").checked;
					var dataInicio = dom.byId("obra-search-data-begin").value;
					if(dataInicio=="" || dataInicio=="undefined"){
						dataInicio=-1;
					}
					alert("here7");
					var dataFimChecked = dom.byId("obra-search-data-checkbox-end").checked;
					var dataFim = dom.byId("obra-search-data-end").value;
					if(dataFim=="" || dataFim=="undefined"){
						dataFim=-1;
					}
					
					alert("dskfodskfo");
					
					//append form values to the base url
					url +="?titulo=" + titulo +
						"&autor=" + autor +
						"&isPintura=" + isPintura +
						"&isEscultura=" + isEscultura +
						"&isAudiovisual=" + isAudiovisual +
						"&materialSearchChecked=" + materialSearchChecked +
						"&material=" + material +
						"&tipoMidiaChecked=" + tipoMidiaChecked +
						"&tipoMidia=" + tipoMidia +
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
		<!-- END - Script to process artwork search -->
	</head>
	<body>
		<header>
			<img src="<c:url value="/resources/img/logoinicialmav.jpg"/>" />
		</header>
		<nav>
			agora	|	exposicoes	|	busca	|	sobre	|	contato
		</nav>
		<main>
			<h1>Busca - Acervo</h1>
			<%--Busca por artista --%>
			<div id="busca-artista">
				<h2>Busca por artista </h2>
				<form id="artista-search-form"> 
					<label>Nome</label>
					<input type="text" id="artist-search-name" name="artist-search-name"> <br/><br/>
					<h3> Filtrar por</h3><br/>
					
					<label>Pais</label>
					<input type="text" id="artist-search-country" name="artist-search-country"> <br/><br/>
					
					<label>Estilo</label>
					<select id="artist-search-style" name="artist-search-style">
						<option value="all">Todos</option>
						<c:forEach items="${nomeEstilosList}" var="nomeEstilo">
							<option value="${nomeEstilo}">${nomeEstilo} </option>
						</c:forEach>
					</select>
					<br/><br/>
					
					<label>Data</label><br/><br/>
					<input type="checkbox" id="artist-search-data-checkbox-begin" name="artist-search-data-checkbox" value="inicio">A partir de: 
					<input type="text" id="artist-search-data-begin" name="artist-search-data-checkbox-begin"> Exemplo: 1950
					<br> 
					<input type="checkbox" id="artist-search-data-checkbox-end" name="artist-search-data-checkbox" value="fim">Até: 
					<input type="text" id="artist-search-data-end" name="artist-search-data-checkbox-end"> Exemplo: 1980
					<br/>
					<br/>
					<input type="button" id="artist-search-submit" value="Busca artistas" onclick="processArtistaSearch()">
				</form>
			</div>
			
			<hr/>
			
			<%--Busca por obra --%>
			<div id="busca-obra">
				<h2>Busca por obra </h2>
				<form id="obra-search-form"> 
					<label>Titulo</label>
					<input type="text" id="obra-search-title" name="obra-search-title"> <br/><br/>
					
					<h3> Filtrar por</h3><br/>
					
					<label>Autor</label>
					<input type="text" id="obra-search-author" name="obra-search-author"> <br/><br/>
					
					<label>Tipo</label><br/>
					<input type="checkbox" id="obra-search-tipo-checkbox-pintura" name="obra-search-tipo-checkbox" value="pintura">Pintura <br/>
					<input type="checkbox" id="obra-search-tipo-checkbox-escultura" name="obra-search-tipo-checkbox" value="escultura">Escultura  <br/>
					<input type="checkbox" id="obra-search-tipo-checkbox-audiovisual" name="obra-search-tipo-checkbox" value="audiovisual">Audiovisual <br/> <br/>
					
					<input type="checkbox" id="obra-search-material-checkbox" name="obra-search-material-checkbox" value="material">Material (somente para pinturas e esculturas) <br/>
					<input type="text" id="obra-search-material-value" name="obra-search-midia-value"> <br/><br/>
					
					<input type="checkbox" id="obra-search-midia-checkbox" name="obra-search-midia-checkbox" value="midia">Tipo de midia (somente para material audiovisual)<br/>
					<input type="text" id="obra-search-midia-value" name="obra-search-midia-value"> <br/><br/>
					
					
					<label>Pais</label>
					<input type="text" id="obra-search-country" name="obra-search-country"> <br/><br/>
					
					<label>Estilo</label>
					<select id="obra-search-style" name="obra-search-style">
						<option value="all">Todos</option>
						<c:forEach items="${nomeEstilosList}" var="nomeEstilo">
							<option value="${nomeEstilo}">${nomeEstilo} </option>
						</c:forEach>
					</select>
					<br/><br/>
					<label>Data</label><br/><br/>
					<input type="checkbox" id="obra-search-data-checkbox-begin" name="obra-search-data-checkbox" value="inicio">A partir de: 
					<input type="text" id="obra-search-data-begin" name="obra-search-data-checkbox-begin"> Exemplo: 1950
					<br> 
					<input type="checkbox" id="obra-search-data-checkbox-end" name="obra-search-data-checkbox" value="fim">Até: 
					<input type="text" id="obra-search-data-end" name="obra-search-data-checkbox-end"> Exemplo: 1980
					<br/>
					<br/>
					<input type="button" id="obra-search-submit" value="Busca obras" onclick="processObraSearch()">
				</form>
			</div>
			
			
			<hr/>
			<div id="search-results">
			
			</div>
			<p style="color:red"></p>
		</main>
		<footer>
			MC 437 - grupo 17
		</footer>
	</body>
</html>