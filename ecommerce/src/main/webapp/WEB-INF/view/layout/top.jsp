<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jstl/sql" prefix="sql" %>

<c:url value="/" var="homeLink" scope="request" />
<c:url value="/#about-us" var="aboutUsLink" scope="request" />
<c:url value="/#our-services" var="ourServicesLink" scope="request" />
<c:url value="/#contact-us" var="contactUsLink" scope="request" />
<c:url value="/#akshaya-sign-in" var="logLink" scope="request" />
<c:url value="/#akshaya-sign-up" var="signUpLink" scope="request" />
<c:url value="/#add-product" var="addProductsLink" scope="request" />
<c:url value="/show-products" var="productsLink" scope="request" />
<c:url value="/products/add-product" var="addProductLink" scope="request"/>
<c:url value="/products/remove-product" var="removeProductLink" scope="request"/>
<c:url value="/#remove-product" var="removeLink" scope="request"/>
<c:url value="/cart" var="cartLink" scope="request"/>
<c:url value="/wishlist" var="wishlistLink" scope="request"/>
<c:url value="/login" var="loginLink" scope="request" />
<c:url value="/signup" var="signupLink" scope="request" />
<c:url value="/logout" var="logoutLink" scope="request" />
<c:url value="/css/style.css" var="styleLink" scope="request" />
<c:url value="/css/payment-style.css" var="paymentStyleLink" scope="request" />
<c:url value="/images/Logo.png" var="logoLink" scope="request" />
<c:url value="/images/about.jpg" var="aboutImageLink" scope="request" />
<c:url value="/images/vi.jpg" var="visaLink" scope="request" />
<c:url value="/images/mc.jpg" var="mcLink" scope="request" />
<c:url value="/images/pp.jpg" var="ppLink" scope="request" />
<c:url value="/images/Cash.jpg" var="cashLink" scope="request" />
<c:url value="/images/Card.jpg" var="cardLink" scope="request" />
<c:url value="/images/UPI.jpg" var="UPILink" scope="request" />
<c:url value="/images/signup.jpg" var="paymentImageLink" scope="request" />
<c:url value="/js/script.js" var="scriptLink" scope="request" />
<c:url value="/css/payment-options.css" var="paymentOptionStyleLink" scope="request" />
<c:url value="/js/jquery-3.6.1.min.js" var="jQueryLink" scope="request" />
<c:url value="/sign-in" var="signInLink" scope="request" />
<c:url value="/api/products/add-to-cart/" var="addToCartLink" scope="request" />
<c:url value="/api/products/add-to-wishlist/" var="addToWishlistLink" scope="request" />
