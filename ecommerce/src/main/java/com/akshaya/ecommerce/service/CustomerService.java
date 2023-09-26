package com.akshaya.ecommerce.service;

import java.util.List;

import com.akshaya.ecommerce.domain.CartItem;
import com.akshaya.ecommerce.domain.Customer;
import com.akshaya.ecommerce.domain.Seller;
import com.akshaya.ecommerce.domain.WishlistItem;

public interface CustomerService {
	
	public List<Customer> getCustomers();
	
	public Customer getCustomerByID(int id);
	
	public Customer findByUsername(String username);
	
	public Seller findSellerByUsername(String username);
	
	public String findCartID(String username);
	
	public List<String> findItemIDList(String cartID);
	
	public CartItem findCartItem(String cartID, String productID);
	
	public boolean updateQuantity(int quantity, String cartID, String productID);

	public String findWishlistID(String username);
	
	public List<String> findWishlistItemIDList(String wishlistID);
	
	public WishlistItem findWishlistItem(String wishlistID, String productID);
	
	public List<CartItem> findAllCartItems(String cartID);
	
	public List<WishlistItem> findAllWishlistItems(String wishlistID);
	
	public int findTotalCartPrice(String cartID);
	
	public boolean updateTotalPrice(int price, String cartID);
	
	public List<String> findCartProductList(String cartID);
	
	public List<String> findWishlistProductList(String cartID);
	
	public boolean deleteCartItem(String cartID, String productID);
	
	public boolean deleteWishlistItem(String wishlistID, String productID);
	
	public List<String> findUsersList();
	
	public boolean insertNewUser(String username, String password, int enabled);
	
	public boolean insertNewAuthority(String username, String authority);
	
	public boolean insertNewCustomer(Customer customer);

}
