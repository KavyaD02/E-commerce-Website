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
<script type="text/javascript" src="${jQueryLink}"></script>
<script>
	var addToCartLink = '${addToCartLink}';
	var addToWishlistLink = '${addToWishlistLink}'
</script>
<script type="text/javascript" src="${scriptLink}"></script>

</head>

<body>

	<c:import url="layout/header.jsp" />

	<div class="header-gap"></div>

	<div class="product-box">
		<div class="product-description">
			<div class="product-title">
				<h1>
					<c:out value="${product.productName} " />
				</h1>
				<h3>
					Rating:
					<c:out value="${product.rating}" />
				</h3>
			</div>
			<h2>Product Description:</h2>
			<p>
				<c:out value="${product.productDesc}" />
			</p>
			<div class="buttons">
				<a href="/products/buy-product/${product.productID}">
					<button class="buy-now">Buy Now</button>
				</a>
				<button class="cart-button"
					onclick="addToCart('${product.productID}');">
					<i class="fa fa-shopping-cart"></i>Add to Cart
				</button>
				<button class="wishlist-button"
					onclick="addToWishlist('${product.productID}');">
					<i class="fa fa-magic"></i>Add to Wishlist
				</button>
			</div>
		</div>
		<div class="product-image-box">
			<img class="product-image"
				src="../../../images/${product.productName}.png">
			<p>
				Price:
				<c:out value="${product.price}" />
			</p>
		</div>
	</div>
	<div class="overlay" style="display: none">
		<div class="loader"></div>
	</div>


</body>
</html>