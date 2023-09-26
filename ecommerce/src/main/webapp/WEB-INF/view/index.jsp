<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<c:import url="layout/top.jsp" />

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
	var addProductLink = '${addProductLink}';
</script>
<script type="text/javascript" src="${scriptLink}"></script>
</head>

<body>

	<c:import url="layout/header.jsp" />

	<section class="home" id="home">
		<div class="home-page">
			<div class="text">
				<h1>Experience Akshaya</h1>
				<h2>Your One-Stop Shopping Destination</h2>
			</div>
		</div>
	</section>

	<section class="about-us" id="about-us">
		<div class="about">
			<div class="about-title">
				<h1>About Akshaya</h1>
				<h2>Our Motive</h2>
			</div>
			<div class="about-content">
				<div class="about-text">
					<p>Akshaya was founded in the year 2002. It was started with a
						motive to take products into the reach of many customers across
						the country. We, at Akshaya aim at satisfying both the customers
						who wish to access required products sitting in the warmth of
						their homes as well the customers who do not want to miss the fun
						of shopping in vast shopping malls.</p>
				</div>
				<div class="about-image">
					<img src="${aboutImageLink}">
				</div>
			</div>
		</div>
	</section>

	<section class="our-services" id="our-services">
		<div class="services">
			<div class="services-title">
				<h1>Akshaya's Services</h1>
				<h2>We Provide</h2>
			</div>
			<div class="services-content">
				<div class="services-text-1">
					<div class="services-online-shopping">
						<h2>
							<i class="fa fa-shopping-cart"></i>Online Shopping
						</h2>
						<p>Akshaya provides an amazing online shopping experience with
							its easy-to-understand, customer friendly interface.</p>
					</div>

					<div class="services-home-delivery">
						<h2>
							<i class="fa fa-truck"></i>Home Delivery
						</h2>
						<p>Akshaya provides a express fast and safe home delivery
							services.</p>
					</div>
				</div>
				<div class="services-text-2">
					<div class="services-products">
						<h2>
							<i class="fa fa-shopping-bag"></i>Variety of Products
						</h2>
						<p>Akshaya provides a wide range of products and offers
							customers the freedom to select products of their choice.</p>
					</div>

					<div class="services-shops">
						<h2>
							<i class="fa fa-life-ring"></i>Shelter to Multiple Shops
						</h2>
						<p>Akshaya provides support to a wide variety of shops and
							gives a platform to sell their products.</p>
					</div>
				</div>
			</div>
		</div>
	</section>

	<sec:authorize access="!isAuthenticated()">
		<section class="akshaya-sign-in" id="akshaya-sign-in">
			<div class="sign-in-page">
				<div class="sign-in-title">
					<h1>Sign In</h1>
					<h2>Experience Us</h2>
				</div>
				<div class="container">
					<div class="login-form">
						<i class="fa fa-user"></i>
						<div class="login">
							<h3>Sign In</h3>
						</div>
						<form action="${loginLink}" method="post" name="loginForm">
							<div class="inputs">
								<input name="username" type="text" placeholder="Username">
								<input name="password" type="password" placeholder="Password">
							</div>
							<div class="login_btn">
								<input class="btn" type="submit" value="Sign In">
							</div>
						</form>

					</div>
				</div>
			</div>
		</section>
	</sec:authorize>
	
	<sec:authorize access="!isAuthenticated()">
		<section class="akshaya-sign-up" id="akshaya-sign-up">
			<div class="sign-up-page">
				<div class="sign-up-title">
					<h1>Sign Up</h1>
					<h2>Join Us</h2>
				</div>
				<div class="container">
					<div class="signup-form">
						<i class="fa fa-user-plus"></i>
						<div class="signup">
							<h3>Sign Up</h3>
						</div>
						<form action="${signupLink}" method="post" name="signupForm">
							<div class="inputs">
								<input name="name" type="text" placeholder="Name">
								<input name="address" type="text" placeholder="Address">
								<input name="username" type="text" placeholder="Username">
								<input name="password" type="password" placeholder="Password">
							</div>
							<div class="signup_btn">
								<input class="btn" type="submit" value="Sign Up">
							</div>
						</form>

					</div>
				</div>
			</div>
		</section>
	</sec:authorize>

	<sec:authorize access="isAuthenticated() and hasAuthority('SELLER')">
		<section class="add-product" id="add-product">
			<div class="add-product-page">
				<div class="add-product-title">
					<h1>Add Product</h1>
					<h2>Support Us</h2>
				</div>
				<div class="container">
					<div class="add-product-form">

						<form action="${addProductLink}" method="post"
							name="addProductForm">
							<div class="inputs">
								<input name="productName" type="text" placeholder="Product Name">
								<input name="shopID" type="text" placeholder="Shop ID">
								<input name="categoryName" type="text"
									placeholder="Category Name"> <input name="rating"
									type="text" placeholder="Product Rating"> <input
									name="price" type="text" placeholder="Product Price">
								<textarea name="productDesc" cols="50" rows="3"
									placeholder="Product Description"></textarea>
							</div>
							<label class="custom-file-upload"> <input name="image"
								type="file" /> Insert Image
							</label>
							<div class="login_btn">
								<button class="btn" type="submit">Add Product</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>


		<section class="remove-product" id="remove-product">
			<div class="remove-product-page">
				<div class="remove-product-box">
				<div class="remove-product-title">
					<h1>Remove Product</h1>
					<h2>Support Us</h2>
				</div>
				<div class="container">
					<div class="remove-product-form">

						<form action="${removeProductLink}" method="post"
							name="removeProductForm">
							<div class="inputs">
								<input name="productID" type="text" placeholder="Product ID">
							</div>
							<div class="login_btn">
								<button class="btn" type="submit">Remove Product</button>
							</div>
						</form>
					</div>
				</div>
				</div>
			</div>
		</section>
	</sec:authorize>

	<section class="contact-us" id="contact-us">
		<div class="contact-us-page">
			<div class="contact-us-title">
				<h1>Contact Us</h1>
				<h2>Reach Us</h2>
			</div>
			<div class="contact-us-container">
				<div class="box-contact">
					<div class="name">
						<p class="contact-head">
							<a href="#"><i class="fa fa-user-circle"></i>Name</a>
						</p>
						<p class="contact-text">Harshitha K</p>
					</div>
					<div class="email">
						<p class="contact-head">
							<a href="http://surl.li/dmcuo"><i class="fa fa-envelope"></i>Email</a>
						</p>
						<p class="contact-text">harshithakanisettypalli@gmail.com</p>
					</div>
				</div>
				<div class="box-contact">
					<div class="name">
						<p class="contact-head">
							<a href="#"><i class="fa fa-user-circle"></i>Name</a>
						</p>
						<p class="contact-text">Kavya D</p>
					</div>
					<div class="email">
						<p class="contact-head">
							<a href="http://surl.li/dmcuh"><i class="fa fa-envelope"></i>Email</a>
						</p>
						<p class="contact-text">krishnakavya.duvvuri@gmail.com</p>
					</div>
				</div>
				<div class="box-contact">
					<div class="name">
						<p class="contact-head">
							<a href="#"><i class="fa fa-user-circle"></i>Name</a>
						</p>
						<p class="contact-text">Teja Nikhil M</p>
					</div>
					<div class="email">
						<p class="contact-head">
							<a href="http://surl.li/dmcur"><i class="fa fa-envelope"></i>Email</a>
						</p>
						<p class="contact-text">masabattulatejanikhil@gmail.com</p>
					</div>
				</div>
				<div class="address">
					<p class="contact-head">
						<a href="http://surl.li/dmcsg"><i class="fa fa-address-card"></i>Address</a>
					</p>
					<p class="contact-text">
						Akshaya Towers, Sarjapur Road<br>Bengaluru - 560035<br>Karnataka,
						India
					</p>
				</div>
			</div>
		</div>
	</section>
	<!-- <h1>Home page</h1>
		<ul>
			<li>
				<a href='<c:out value="${productsLink}"/>'>
					Show Products
				</a>
				<form name="loginForm" method="post" action="${loginLink}">
					<input name="username">
					<input name="password" type="password">
					<input type="submit" value="Login" />
				</form>
			</li>
		</ul>  -->
</body>
</html>