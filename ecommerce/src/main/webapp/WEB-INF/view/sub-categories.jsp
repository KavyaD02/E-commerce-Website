<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="layout/top.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Akshaya Mall</title>
<link rel="icon" type="image/x-icon" href="${logoLink}">
<link rel="stylesheet" type="text/css" href="${styleLink}">
<script
	src="https://cdn.jsdelivr.net/gh/cferdinandi/smooth-scroll/dist/smooth-scroll.polyfills.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
	integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script type="text/javascript" src="${scriptLink}"></script>
</head>

<body>

	<c:import url="layout/header.jsp" />

	<div class="header-gap"></div>

	<!--  
	<c:forEach var="listVar" items="${products}">
    	<p><c:out value="${listVar.productID}"/></p>
	</c:forEach>
	
	-->

	<div class="product-box">
		<c:forEach var="category" items="${categories}">
			<div class="category-box">
				<a href="/products/category/${category.categoryID}/show-products"> 
					<img src="../../images/${category.categoryName}.png">
				</a>
				<p><c:out value="${category.categoryName}" /></p>
			</div>
		</c:forEach>
	</div>

</body>
</html>