var scroll = new SmoothScroll('a[href*="#"]', {
	speed: 1250,
	speedAsDuration: true
});

function addToCart(prodId) {
	// showSpinner
	$('.overlay').show();

	$.post(addToCartLink + prodId, function(data) {
		console.log("cartTotal: ", data);
		// $("#cartTotal").html(data.cartTotal);
		$('.overlay').hide();
	});
}

function addToWishlist(productID) {
	$('.overlay').show();

	$.post(addToWishlistLink + productID, function(data) {
		console.log("cartTotal: ", data);
		// $("#cartTotal").html(data.cartTotal);
		$('.overlay').hide();
	});
}

function addProduct() {
	$('.overlay').show();
	
	var productName = document.getElementsByName("productName").value;
	var productImage = document.getElementsByName("image").value;
	
	console.log(productName);
	console.log(productImage);
	
	$.post(addProductLink, function(data) {
		console.log("cartTotal: ", data);
		$('.overlay').hide();
	});
}

function removeProduct() {
	$('.overlay').show();
	$.post(addProductLink, function(data) {
		console.log("cartTotal: ", data);
		$('.overlay').hide();
	});
}
function myFunction() {
	document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
	if (!event.target.matches('.dropbtn')) {
		var dropdowns = document.getElementsByClassName("dropdown-content");
		var i;
		for (i = 0; i < dropdowns.length; i++) {
			var openDropdown = dropdowns[i];
			if (openDropdown.classList.contains('show')) {
				openDropdown.classList.remove('show');
			}
		}
	}
}