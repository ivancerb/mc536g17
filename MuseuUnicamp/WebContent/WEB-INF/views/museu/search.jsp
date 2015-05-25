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
			
					var url = "process-search"; //base url to be called; form parameters added below
					
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
			<h2> Digite sua busca </h2>
			<form id="search-form"> 
				<label>Artista</label>
				<input type="text" id="search-artist" name="person"> <br/>
				
				<label>Obra</label>
				<input type="text" id="search-artwork" name="colleague"> <br/>
				
				<label>Material</label>
				<input type="text" id="search-material" name="rating">
				
				<input type="button" id="search-submit" value="buscar" onclick="processSearch()">
			</form>
			
			<hr/>
			<div id="search-results">
			
			</div>
			<p style="color:red"> ${testeBd}</p>
		</main>
		<footer>
			MC 437 - grupo 17
		</footer>
	</body>
</html>