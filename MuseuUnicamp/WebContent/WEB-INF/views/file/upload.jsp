<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
	<title>Esqueleto</title>
<!-- 	<link rel="stylesheet" href="resources/css/style.css" /> -->
	<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container">
		<div id="header">
			<div id="logo">&nbsp;</div>
			<ul id="menu">
				<li class="menu-item">Submiss&otilde;es</li><li class="menu-item">Minha conta</li>
			</ul>
		</div>
		<div class="main">
			<div class="breadcrumb"><a href="#">Home</a> &gt; <a href="#">Minhas submiss&otilde;es</a> &gt; <a href="#">Nova submiss&atilde;o</a></div><br>
			<h1 class="pagetitle">Upload de arquivo</h1><br>
			<img src="<c:url value="/resources/img/test.png"/>" />
			<form action="#" method="get">
				Endere&ccedil;o: <input type="file" name="path" style="display: inline-block;"><br><br>
				<input type="submit" value="Submeter">
			</form>
		</div>
	</div>
</body>
</html>
