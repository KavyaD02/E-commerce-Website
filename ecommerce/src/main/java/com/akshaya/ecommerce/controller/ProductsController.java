package com.akshaya.ecommerce.controller;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.akshaya.ecommerce.Utils;
import com.akshaya.ecommerce.dao.CustomerDao;
import com.akshaya.ecommerce.domain.CartItem;
import com.akshaya.ecommerce.domain.CartProduct;
import com.akshaya.ecommerce.domain.Category;
import com.akshaya.ecommerce.domain.Customer;
import com.akshaya.ecommerce.domain.Payment;
import com.akshaya.ecommerce.domain.Product;
import com.akshaya.ecommerce.domain.Seller;
import com.akshaya.ecommerce.domain.ShoppingCart;
import com.akshaya.ecommerce.domain.Wishlist;
import com.akshaya.ecommerce.service.CustomerService;
import com.akshaya.ecommerce.service.ProductService;

@Controller
public class ProductsController {

	@Autowired
	ProductService productService;
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping("/products")
	public String  getCategories(Model model) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication(); 
		List<Category> shopCategories = productService.getShopCategories();
		model.addAttribute("categories", shopCategories);
		return "categories";
	}
	
	@RequestMapping("/products/remove-product")
	public String  removeProduct(Model model, String productID) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) a.getPrincipal();
		String username = user.getUsername();
		System.out.println("Username: " + username);
		System.out.println("Product ID: " + productID);
		Seller seller = customerService.findSellerByUsername(username);
		List<String> productIDs = productService.findSellerProducts(seller.getSellerID());
		if (productIDs.contains(productID)) {
			productService.deleteProduct(productID);
			return "index";
		} else {
			return "invalid-entry";
		}
	}
	
	@RequestMapping("/signup")
	public String  signUp(Model model, String username, String password, String name, String address) {
		System.out.println("hello, in!");
		List<String> users = customerService.findUsersList();
		for (String i : users) {
			if (i.equals(username)) {
				return "index";
			}
		}
		
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("New user!!");
		customerService.insertNewUser(username, Utils.encodeString(password), 1);
		customerService.insertNewAuthority(username, "CUSTOMER");
		List<Customer> customers = customerService.getCustomers();
		List<String> customerIDs = new ArrayList<String>();
		for (Customer customer : customers) {
			customerIDs.add(customer.getCustomerID());
		}
		String customerID = Utils.createCustomerID(customerIDs);
		Customer newCustomer = new Customer();
		ShoppingCart cart = new ShoppingCart();
		Wishlist wishlist = new Wishlist();
		
		List<String> cartIDs = productService.findCartIDs();
		List<String> wishlistIDs = productService.findWishlistIDs();
		
		cart.setCartID(Utils.createCartID(cartIDs));
		cart.setCustomerID(customerID);
		cart.setTotalPrice(0);
		
		wishlist.setListID(Utils.createWishlistID(wishlistIDs));
		wishlist.setCustomerID(customerID);
		wishlist.setTotalPrice(0);

		newCustomer.setCustomerID(customerID);
		newCustomer.setCustomerUsername(username);
		newCustomer.setCustomerName(name);
		newCustomer.setCustomerAddress(address);
		customerService.insertNewCustomer(newCustomer);
		productService.insertCart(cart);
		productService.insertWishlist(wishlist);
		
		return "index";
	}
	
	
	@RequestMapping("/products/move-to-cart/{productID}")
	public String  moveToCart(Model model, @PathVariable("productID") String productID) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) a.getPrincipal();
		String username = user.getUsername();
		System.out.println("Username: " + username);
		String wishlistID = customerService.findWishlistID(username);
		customerService.deleteWishlistItem(wishlistID, productID);
		String cartID = customerService.findCartID(username);
		System.out.println("Cart ID: " + cartID);
		System.out.println("Product ID: " + productID);
		List<String> itemIDList = customerService.findItemIDList(cartID);
		CartItem existingItem = customerService.findCartItem(cartID, productID);
		if (existingItem == null) {
			String itemID;
			if (itemIDList.size() == 0) {
				itemID = "AKSC001";
			} else {
				itemID = Utils.createItemID(itemIDList);
			}
			CartItem cartItem = new CartItem();
			cartItem.setCartID(cartID);
			cartItem.setItemID(itemID);
			cartItem.setProductID(productID);
			cartItem.setQuantity(1);
			productService.insertCartItem(cartItem);
			int price = customerService.findTotalCartPrice(cartID);
			price = price + productService.getPriceFromID(productID)*1;
			customerService.updateTotalPrice(price, cartID);
		} else {
			customerService.updateQuantity(existingItem.getQuantity() + 1, cartID, productID);
			int price = customerService.findTotalCartPrice(cartID);
			price = price + productService.getPriceFromID(productID)*1;
			customerService.updateTotalPrice(price, cartID);
		}
		return "redirect:/wishlist";
	}
	
	@RequestMapping("/products/category/{parentName}")
	public String  getSubCategories(Model model, @PathVariable(name="parentName", required = false) String categoryName) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		List<Category> subCategories = productService.getSubCategories(categoryName);
		System.out.println(subCategories.size());
		if (subCategories.size() == 0) {
			String categoryID = productService.getCategoryID(categoryName);
			List<Product> products = productService.getProductsByCategory(categoryID);
			System.out.println(products.size());
			System.out.println(categoryID);
			model.addAttribute("products", products);
			return "products";
		} else {
			model.addAttribute("categories", subCategories);
			return "sub-categories";
		}
	}
	
	
	
	@RequestMapping("/products/{productID}/desc")
	public String getProductDesc(Model model, @PathVariable("productID") String productID) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		Product product = productService.getProductByID(productID);
		System.out.println(product.getProductDesc());
		model.addAttribute("product", product);
		return "product-description";
	}
	
	@RequestMapping("/products/category/{categoryName}/show-products")
	public String  getProductsByCategory(Model model, @PathVariable("categoryName") String categoryName) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(categoryName);
		List<Product> products = productService.getProductsByCategory(categoryName);
		System.out.println(products.size());
		model.addAttribute("products", products);
		return "products";
	}
	
	@RequestMapping("/show-products")
	public String  showProducts() {
		return "redirect:/products";
	}
	
	@RequestMapping("/products/buy-product/{ID}")
	public String makePayment(Model model, @PathVariable("ID") String ID) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) a.getPrincipal();
		String username = user.getUsername();
		System.out.println("Username: " + username);
		System.out.println(ID);
		if (ID.contains("CA")) {
			int price = customerService.findTotalCartPrice(ID);
			System.out.println(price);
			model.addAttribute("productPrice", price);
			model.addAttribute("ID", ID);
		} else {
			int price = productService.getPriceFromID(ID);
			System.out.println(price);
			model.addAttribute("productPrice", price);
			model.addAttribute("ID", ID);
		}
		return "payment-options";
	}
	
	@RequestMapping("/products/buy-product/card/{ID}/process")
	public String paymentSuccess(Model model, String cardholderName, String cardNumber, String CVV, String amount, @PathVariable("ID") String productID) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) a.getPrincipal();
		String username = user.getUsername();
		System.out.println("Cardholder Name: " + cardholderName);
		System.out.println("Card Number: " + cardNumber);
		System.out.println("CVV: " + CVV);
		System.out.println("Username: " + username);
		Customer customer = customerService.findByUsername(username);
		String customerID = customer.getCustomerID();
		String paymentID;
		List<String> paymentIDList = productService.selectPaymentID();
		if (paymentIDList.size() == 0) {
			paymentID = "AKPA001";
		} else {
			paymentID = Utils.createPaymentID(paymentIDList);
		}
		if (cardholderName != null && cardNumber != null && cardNumber.length() == 9 && CVV != null && CVV.length() == 3) {
			model.addAttribute("amount", amount);
			Payment payment = new Payment();
			payment.setAmount(Integer.valueOf(amount));
			payment.setCustomerID(customerID);
			payment.setPaymentID(paymentID);
			payment.setPaymentMode("Card");
			productService.insertPayment(payment);
			return "payment-success";
		} else {
			return "payment-failed";
		}
	}
	
	@RequestMapping("/products/buy-product/card/{ID}")
	public String paymentOptions(Model model, @PathVariable("ID") String ID) {
		System.out.println("ID" + ID);
		model.addAttribute("ID", ID);
		int amount = 0;
		if (ID.contains("CA")) {
			amount = customerService.findTotalCartPrice(ID);
		} else {
			amount = productService.getPriceFromID(ID);
		}
		model.addAttribute("amount", String.valueOf(amount));
		return "payment-card";
	}
	
	@RequestMapping("/products/buy-product/cash/{ID}")
	public String paymentOptionsCash(Model model, @PathVariable("ID") String ID) {
		System.out.println("ID" + ID);
		model.addAttribute("ID", ID);
		int amount = 0;
		if (ID.contains("CA")) {
			amount = customerService.findTotalCartPrice(ID);
		} else {
			amount = productService.getPriceFromID(ID);
		}
		model.addAttribute("amount", String.valueOf(amount));
		return "payment-cash";
	}
	
	@RequestMapping("/products/buy-product/cash/{ID}/process")
	public String paymentSuccessCash(Model model, @PathVariable("ID") String ID) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) a.getPrincipal();
		String username = user.getUsername();
		System.out.println("Username: " + username);
		Customer customer = customerService.findByUsername(username);
		String customerID = customer.getCustomerID();
		System.out.println("customer ID" + customerID);
		String paymentID;
		List<String> paymentIDList = productService.selectPaymentID();
		if (paymentIDList.size() == 0) {
			paymentID = "AKPA001";
		} else {
			paymentID = Utils.createPaymentID(paymentIDList);
		}
		model.addAttribute("ID", ID);
		int amount = 0;
		System.out.println("ID: hello " + ID);
		if (ID.contains("CA")) {
			amount = customerService.findTotalCartPrice(ID);
		} else {
			amount = productService.getPriceFromID(ID);
		}
		model.addAttribute("amount", String.valueOf(amount));
		Payment payment = new Payment();
		payment.setAmount(Integer.valueOf(amount));
		payment.setCustomerID(customerID);
		payment.setPaymentID(paymentID);
		payment.setPaymentMode("Cash");
		productService.insertPayment(payment);
		System.out.println("Done");
		return "order-success";
	}
	
	@RequestMapping("/products/buy-product/upi/{ID}/process")
	public String paymentSuccessUPI(Model model, @PathVariable("ID") String ID) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) a.getPrincipal();
		String username = user.getUsername();
		System.out.println("Username: " + username);
		Customer customer = customerService.findByUsername(username);
		String customerID = customer.getCustomerID();
		System.out.println("customer ID" + customerID);
		String paymentID;
		List<String> paymentIDList = productService.selectPaymentID();
		if (paymentIDList.size() == 0) {
			paymentID = "AKPA001";
		} else {
			paymentID = Utils.createPaymentID(paymentIDList);
		}
		model.addAttribute("ID", ID);
		int amount = 0;
		System.out.println("ID: hello " + ID);
		if (ID.contains("CA")) {
			amount = customerService.findTotalCartPrice(ID);
		} else {
			amount = productService.getPriceFromID(ID);
		}
		model.addAttribute("amount", String.valueOf(amount));
		Payment payment = new Payment();
		payment.setAmount(Integer.valueOf(amount));
		payment.setCustomerID(customerID);
		payment.setPaymentID(paymentID);
		payment.setPaymentMode("UPI");
		productService.insertPayment(payment);
		System.out.println("Done");
		return "payment-success";
	}
	
	@RequestMapping("/products/buy-product/upi/{ID}")
	public String paymentOptionsUPI(Model model, @PathVariable("ID") String ID) {
		model.addAttribute("ID", ID);
		int amount = 0;
		System.out.println("ID: " + ID);
		if (ID.contains("CA")) {
			amount = customerService.findTotalCartPrice(ID);
		} else {
			amount = productService.getPriceFromID(ID);
		}
		model.addAttribute("amount", String.valueOf(amount));
		return "payment-upi";
	}
	
	@RequestMapping(value="/products/add-product")
	public String postAddProduct(String productName, String productDesc, String categoryName, String rating, String price, String shopID, String image, Model model) throws IOException {
		System.out.println(productDesc);
		System.out.println(image);
		String imagePath = "C:\\Users\\Kavya Duvvuri\\Downloads\\" + image;
		BufferedImage imageFile = null;
		try {
			File input_file = new File(imagePath);
			imageFile = new BufferedImage(280, 230, BufferedImage.TYPE_INT_ARGB);
			imageFile = ImageIO.read(input_file);
		} 
		catch (IOException e){
			System.out.println("Error: " + e);
		}
		
		String outputFilePath = "D:\\Workspaces\\Java\\MyJavaWorkspaces\\ecommerce\\src\\main\\webapp\\images\\" + productName + ".png";
		
		try {
            // Output file path
            File outputFile = new File(outputFilePath);
  
            // Writing to file taking type and path as
            ImageIO.write(imageFile, "png", outputFile);
  
            System.out.println("Writing complete.");
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
		System.out.println(categoryName);
		List<String> productIDList = productService.getProductIDList();
		String productID = Utils.createProductID(productIDList);
		System.out.println(productID);
		String categoryID = productService.getCategoryID(categoryName);
		System.out.println(categoryID);
		Product product = new Product();
		product.setProductID(productID);
		product.setProductDesc(productDesc);
		product.setProductName(productName);
		product.setCategory(categoryID);
		product.setRating(Float.valueOf(rating));
		product.setPrice(Double.valueOf(price));
		product.setShopID(shopID);
		productService.insertProduct(product);
		int totalCart = 1;
		Map<String, Object> map = new HashMap<>();
		map.put("cartTotal", totalCart);
		return "index";
	}
	
	@RequestMapping("/cart")
	public String getCartItems(Model model) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) a.getPrincipal();
		String username = user.getUsername();
		String cartID = customerService.findCartID(username);
		List<CartItem> cartItems = customerService.findAllCartItems(cartID);
		System.out.println("CartItems: " + cartItems.size());
		System.out.println("Hello, " + username);
		List<String> productIDs = customerService.findCartProductList(cartID);
		List<CartProduct> products = new ArrayList<CartProduct>();
		System.out.println("Cart ID: " + cartID);
		int price = 0;
		for (int i = 0; i < productIDs.size(); i++) {
			String productID = productIDs.get(i);
			Product product = productService.getProductByID(productID);
			CartProduct cartProduct = new CartProduct();
			cartProduct.setPrice(product.getPrice());
			cartProduct.setProductDesc(product.getProductDesc());
			cartProduct.setProductName(product.getProductName());
			cartProduct.setProductID(product.getProductID());
			for (int j = 0; j < cartItems.size(); j++) {
				CartItem cartItem = cartItems.get(j);
				System.out.println("cart id: " + cartItem.getCartID());
				if (product.getProductID().equals(cartItem.getProductID())) {
					cartProduct.setQuantity(cartItem.getQuantity());
				}
			}
			products.add(cartProduct);
			int productPrice = productService.getPriceFromID(productID);
			int productQuantity = cartProduct.getQuantity();
			System.out.println("Price: " + productPrice);
			price = price + (productPrice*productQuantity);
		}
		customerService.updateTotalPrice(price, cartID);
		model.addAttribute("products", products);
		model.addAttribute("price", price);
		model.addAttribute("cartid", cartID);
		
		return "cart";
	}
	
	@RequestMapping("/wishlist")
	public String getWishlistItems(Model model) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) a.getPrincipal();
		String username = user.getUsername();
		String wishlistID = customerService.findWishlistID(username);
		List<CartItem> wishlistItems = customerService.findAllCartItems(wishlistID);
		List<String> productIDs = customerService.findWishlistProductList(wishlistID);
		List<Product> products = new ArrayList<Product>();
		
		int price = 0;
		for (int i = 0; i < productIDs.size(); i++) {
			String productID = productIDs.get(i);
			Product product = productService.getProductByID(productID);
			products.add(product);
		}
		model.addAttribute("products", products);
		
		return "wishlist";
	}
	
	@RequestMapping("/products/remove-from-cart/{cartID}/{productID}")
	public String removeProductFromCart(@PathVariable("cartID") String cartID, @PathVariable("productID") String productID) {
		CartItem cartItem = customerService.findCartItem(cartID, productID);
		System.out.println("Cart ID to remove from: " + cartID);
		System.out.println("Product ID to be removed: " + productID);
		int quantity = cartItem.getQuantity();
		if (quantity == 1) {
			customerService.deleteCartItem(cartID, productID);
		} else if (quantity > 1) {
			customerService.updateQuantity(quantity - 1, cartID, productID);
		}
		return "redirect:/cart";
	}
}


// Model (POJO), View, Controller, Service, Dao, DB
