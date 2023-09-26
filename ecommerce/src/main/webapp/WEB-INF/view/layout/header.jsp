<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
	
<div class="header">
	<div class="logo">
		<a href="#"><span>A</span>kshaya</a>
	</div>
	<div class="top-menu">
		<ul>
			<li <%= request.getRequestURI().endsWith("/index.jsp") ? "class='active'" : "" %>><a href="${homeLink}"><i class="fa fa-home"></i>Home</a></li>
			<sec:authorize access="isAuthenticated()">
				<sec:authorize access="hasAnyAuthority('CUSTOMER', 'ADMIN')">
				<li <%= request.getRequestURI().endsWith("/products.jsp") ? "class='active'" : "" %>><a href="${productsLink}"><i class="fa fa-folder"></i>Products</a></li>
				<li <%= request.getRequestURI().endsWith("/cart.jsp") ? "class='active'" : "" %>><a href="${cartLink}"><i class="fa fa-shopping-basket"></i>Shop Cart</a></li>
				<li <%= request.getRequestURI().endsWith("/wishlist.jsp") ? "class='active'" : "" %>><a href="${wishlistLink}"><i class="fa fa-bars"></i>Wishlist</a></li>
				</sec:authorize>
				<sec:authorize access="hasAnyAuthority('SELLER', 'ADMIN')">
					<li <%= request.getRequestURI().endsWith("/products.jsp") ? "class='active'" : "" %>><a href="${addProductsLink}"><i class="fa fa-plus"></i>Add Product</a></li>
					<li <%= request.getRequestURI().endsWith("/remove-product.jsp") ? "class='active'" : "" %>><a href="${removeLink}"><i class="fa fa-minus"></i>Remove</a></li>
				</sec:authorize>
			</sec:authorize>
			<li><a href="${aboutUsLink}"><i class="fa fa-users"></i>About Us</a></li>
			<li><a href="${ourServicesLink}"><i class="fa fa-clone"></i>Services</a></li>
			<li><a href="${contactUsLink}"><i class="fa fa-phone"></i>Contact Us</a></li>
			<sec:authorize access="!isAuthenticated()">
				<li><a href="${logLink}"><i class="fa fa-user"></i>Sign
						In</a></li>
			</sec:authorize>
			<sec:authorize access="!isAuthenticated()">
				<li><a href="${signUpLink}"><i class="fa fa-user-plus"></i>Sign
						Up</a></li>
			</sec:authorize>
			
			<sec:authorize access="isAuthenticated()">
				<li>
					<div style="position: absolute; top: 10px; right: 10px;">
						Welcome
						<sec:authentication property="name" />
					</div> <a href="${logoutLink}"><i class="fa fa-user"></i>Sign Out</a>
				</li>
			</sec:authorize>
		</ul>
	</div>
</div>