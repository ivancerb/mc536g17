<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
	</head>
	<body>
		<h1>Arquivos de Teste</h1>
		<div id="uploadlink">
			<h2> Upload </h2>
			<a href="upload">Faça o upload de um novo arquivo</a>
		</div>
		<jsp:include page="list.jsp" /> 
	</body>
</html>
