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
<link rel="stylesheet" type="text/css" href="${paymentStyleLink}">
<script
	src="https://cdn.jsdelivr.net/gh/cferdinandi/smooth-scroll/dist/smooth-scroll.polyfills.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
	integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script type="text/javascript" src="${jQueryLink}"></script>
<script type="text/javascript" src="${scriptLink}"></script>
</head>

<body>

	<section>
		<div class="image-box">
			<img src="${paymentImageLink}">
		</div>
		<div class="content-box">
			<div class="form-box">
				<h2>Payment Portal</h2>
				<div class="selection-box">
					<img src="${mcLink}" alt=""> <img src="${visaLink}" alt="">
					<img src="${ppLink}" alt="">
				</div>
				<form action="/products/buy-product/card/${ID}/process" method="post">
					<div class="input-box">
						<span>Cardholder Name</span> <input type="text" name="cardholderName"
							placeholder="Name Surname">
					</div>
					<div class="input-box">
						<span>Card Number</span> <input type="number"
							oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
							maxlength="9" name="cardNumber"
							placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;">
					</div>
					<div class="cvv_amount">
						<div class="cvv-box">
							<span>CVV</span> <input type="number" name="CVV"
								oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"
								maxlength="3" placeholder="CVV">
						</div>
						<div class="amount-box">
							<span>Amount</span> <input type="number" name="amount"
								value="${amount}" readonly="readonly" placeholder="Amount">
						</div>
					</div>
					<div class="expiry-box">
						<span>Card Expiry Date</span> <input type="date" name="date"
							placeholder="expiry">
					</div>
					<div class="remember">
						<label><input type="checkbox" name="accept"> Accept the
							terms and conditions</label>
					</div>

					<div class="confirmation">
						<div class="confirm-box payment">
						<button class="payment-button" type="submit">Proceed to Pay</button>
						</div>
					</div>
					</form>
					<form action="${logLink}">
					<div class="confirm-box-cancel">
							<button class="cancel-button" type="submit">Cancel Payment</button>
					</div>
					</form>
					<!-- <div class="go-back">
						<p>
							Want to visit the cart? <a href="#">Visit cart</a>
						</p>
					</div> -->
			</div>
		</div>
	</section>
</body>
</html>