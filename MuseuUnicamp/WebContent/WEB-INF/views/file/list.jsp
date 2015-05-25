<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<h2>Seus arquivos</h2>
<div id="files-owned-list">
	<table>
		<thead>
			<tr>
		    	<th> Arquivo </th> 
				<th> Adicionado em </th> 
				<th> Ações </th>
		    </tr>
		</thead>
		<tbody>
			<c:forEach var="ownedFile" items="${ownedFilesList}">
				<tr>
					<td> ${ownedFile.name} </td> 
					<td> ${ownedFile.addTs} </td>
					<td> <a href="../test/results"> Visualizar </a> | <a href=""> Compartilhar </a></td>
		        </tr>
			</c:forEach>	
		</tbody>
	</table>
</div>

<h2>Arquivos compartilhados com você</h2>

<div id="files-shared-list">
	<table>
		<thead>
			<tr>
		    	<th> Arquivo </th> 
		    	<th> Adicionado por </th>
				<th> Adicionado em </th>
				<th> Ações </th> 
		    </tr>
		</thead>
		<tbody>
			<c:forEach var="sharedFile" items="${sharedFilesList}">
				<tr>
					<td> ${sharedFile.name} </td>
					<td> ${sharedFile.owner}
					<td> ${sharedFiles.addTs} </td>
					<td> <a href="../test/results"> Visualizar </a> </td>
		        </tr>
			</c:forEach>	
		</tbody>
	</table>
</div>