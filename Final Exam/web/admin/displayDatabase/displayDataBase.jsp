<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<!-- TO DO: Make css tables compatible for reset.css code:<link href="res/style/reset.css" rel="stylesheet">-->
		<link href="../../res/style/default.css" rel="stylesheet">
		<title>CAR RACING GAME</title>
	</head>
	
	<body>
		<div class="app-pane top">
			<img src="../res/img/png-sheridan.jpg">
			<div class="app-title">CAR RACING GAME</div>
			<div class="dynamic-box top">
				<p>Game designed by Group 1</p>
				<ul class="mini-text">
					<li>Daniel Hope</li>
					<li>Georgina Luce</li>
					<li>Nathaniel Primo</li>
					<li>Michael Marc</li>
				</ul>
			</div>
		</div>
		
		<div class="app-pane bottom">
		
			<!-- Connect to Database -->
			<sql:setDataSource
	        	var="myDS"
	        	driver="com.mysql.jdbc.Driver"
	        	url="jdbc:mysql://localhost:3306/dbprog32758"
	        	user="root" password="Polska615"
	    	/>
	    	
	    	<!-- Run Query -->
	    	<sql:query var="listUsers"   dataSource="${myDS}">
	        	SELECT * FROM players;
	    	</sql:query>
	    	
	    	<!-- Database Table -->
	    	<div align="center">
	        	<table border="1" cellpadding="5">
	        		<!-- Column Titles -->
	            	<caption><h2>List of players:</h2></caption>
	            	<tr>
	                	<th>First Name</th>
	                	<th>Last Name</th>
	                	<th>Group Name</th>
	                	<th>Login</th>
	                	<th>Password</th>
	            	    <th>Preferred Car Name</th>
	           	     	<th>Logo</th>
	                	<th>Score</th>
	                	<th>Credits</th>
	            	</tr>
	            	<!-- Column Rows -->
	            	<!-- uses jstl's "forEach" into table rows -->
	            	<c:forEach var="players" items="${listUsers.rows}">
	                	<tr>
		                    <td><c:out value="${players.first_name}" /></td>
		                    <td><c:out value="${players.last_name}" /></td>
		                    <td><c:out value="${players.groupN}" /></td>
		                    <td><c:out value="${players.login}" /></td>
		                    <td><c:out value="${players.password}" /></td>
		                    <td><c:out value="${players.preferred_car_name}" /></td>
		                    <td><c:out value="${players.logo}" /></td>
		                    <td><c:out value="${players.score}" /></td>
		                    <td><c:out value="${players.credits}" /></td>
		                </tr>
		            </c:forEach>
	        	</table>
	    	</div>
	    	
	    	<!-- Return to home page Button -->
			<button class="app-button exit" onclick="window.location = '../admin.html'">Back</button>
		</div>
	</body>
</html>