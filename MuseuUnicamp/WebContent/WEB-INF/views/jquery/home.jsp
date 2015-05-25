<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JQuery PoC</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$("buttonTestDom"). click(function(event){
					$("testDom").html("You clicked the button");
				});
			});
			
// 			 $( document ).ready(function() {
// 				$( "a" ).click(function( event ) {
// 				alert( "The link will no longer take you to jquery.com" );
// 				event.preventDefault();
// 				});
// 			 });
		</script>
	</head>
	<body>
		<h1>Hello JQuery World</h1>
		
		<form action="">
			<div id=testDom>
				<input type="button" id="buttonTestDom" value="Press Me!">
			</div>
		</form>
	</body>
</html>