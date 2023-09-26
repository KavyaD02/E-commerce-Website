package com.akshaya.ecommerce.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akshaya.ecommerce.Utils;
import com.akshaya.ecommerce.domain.CartItem;
import com.akshaya.ecommerce.domain.WishlistItem;
import com.akshaya.ecommerce.service.CustomerService;
import com.akshaya.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ProductService productService;

	@PostMapping(value="/products/add-to-cart/{productID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object>  postAddToCart(Model model, @PathVariable("productID") String productID) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) a.getPrincipal();
		String username = user.getUsername();
		System.out.println("Username: " + username);
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
		
		int totalCart = 1;
		Map<String, Object> map = new HashMap<>();
		map.put("cartTotal", totalCart);
		return map;
	}
	
	@PostMapping(value="/products/add-to-wishlist/{productID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object>  postAddToWishlist(Model model, @PathVariable("productID") String productID) {
		System.out.println("Hello, entered!");
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) a.getPrincipal();
		String username = user.getUsername();
		System.out.println("Username: " + username);
		String wishlistID = customerService.findWishlistID(username);
		System.out.println("Cart ID: " + wishlistID);
		System.out.println("Product ID: " + productID);
		List<String> itemIDList = customerService.findWishlistItemIDList(wishlistID);
		WishlistItem existingItem = customerService.findWishlistItem(wishlistID, productID);
		if (existingItem == null) {
			String itemID;
			if (itemIDList.size() == 0) {
				itemID = "AKSC001";
			} else {
				itemID = Utils.createItemID(itemIDList);
			}
			WishlistItem wishlistItem = new WishlistItem();
			wishlistItem.setListID(wishlistID);
			wishlistItem.setItemID(itemID);
			wishlistItem.setProductID(productID);
			productService.insertWishlistItem(wishlistItem);
		}
		int totalCart = 1;
		Map<String, Object> map = new HashMap<>();
		map.put("cartTotal", totalCart);
		return map;
	}
	
}
