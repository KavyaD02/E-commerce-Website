package com.akshaya.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshaya.ecommerce.dao.CustomerDao;
import com.akshaya.ecommerce.domain.CartItem;
import com.akshaya.ecommerce.domain.Customer;
import com.akshaya.ecommerce.domain.Seller;
import com.akshaya.ecommerce.domain.WishlistItem;

@Service
public class DefaultCustomerService implements CustomerService{
	
	@Autowired
	CustomerDao customerDao;

	@Override
	public List<Customer> getCustomers() {
		return customerDao.findCustomers();
	}

	@Override
	public Customer getCustomerByID(int id) {
		return customerDao.findByID(id);
	}

	@Override
	public Customer findByUsername(String username) {
		return customerDao.findByUsername(username);
	}

	@Override
	public String findCartID(String username) {
		return customerDao.findCartID(username);
	}

	@Override
	public List<String> findItemIDList(String cartID) {
		return customerDao.findCartItemIDList(cartID);
	}

	@Override
	public CartItem findCartItem(String cartID, String productID) {
		return customerDao.findCartItem(cartID, productID);
	}

	@Override
	public boolean updateQuantity(int quantity, String cartID, String productID) {
		return customerDao.updateQuantity(quantity, cartID, productID);
	}

	@Override
	public String findWishlistID(String username) {
		return customerDao.findWishlistID(username);
	}

	@Override
	public List<String> findWishlistItemIDList(String wishlistID) {
		// TODO Auto-generated method stub
		return customerDao.findWishlistItemIDList(wishlistID);
	}

	@Override
	public WishlistItem findWishlistItem(String wishlistID, String productID) {
		// TODO Auto-generated method stub
		return customerDao.findWishlistItem(wishlistID, productID);
	}

	@Override
	public List<CartItem> findAllCartItems(String cartID) {
		// TODO Auto-generated method stub
		return customerDao.findAllCartItems(cartID);
	}

	@Override
	public List<WishlistItem> findAllWishlistItems(String wishlistID) {
		// TODO Auto-generated method stub
		return customerDao.findAllWishlistItems(wishlistID);
	}

	@Override
	public int findTotalCartPrice(String cartID) {
		// TODO Auto-generated method stub
		return customerDao.findTotalCartPrice(cartID);
	}

	@Override
	public boolean updateTotalPrice(int price, String cartID) {
		// TODO Auto-generated method stub
		return customerDao.updatePrice(price, cartID);
	}

	@Override
	public List<String> findCartProductList(String cartID) {
		// TODO Auto-generated method stub
		return customerDao.findCartProductIDList(cartID);
	}

	@Override
	public List<String> findWishlistProductList(String cartID) {
		// TODO Auto-generated method stub
		return customerDao.findWishlistProductIDList(cartID);
	}

	@Override
	public boolean deleteCartItem(String cartID, String productID) {
		// TODO Auto-generated method stub
		return customerDao.deleteProductFromCart(cartID, productID);
	}

	@Override
	public boolean deleteWishlistItem(String wishlistID, String productID) {
		// TODO Auto-generated method stub
		return customerDao.deleteProductFromWishlist(wishlistID, productID);
	}

	@Override
	public Seller findSellerByUsername(String username) {
		// TODO Auto-generated method stub
		return customerDao.findSellerByUsername(username);
	}
	
	public List<String> findUsersList(){
		return customerDao.findUsersList();
	}
	
	public boolean insertNewUser(String username, String password, int enabled) {
		return customerDao.insertNewUser(username, password, enabled);
	}
	
	public boolean insertNewAuthority(String username, String authority) {
		return customerDao.insertNewAuthority(username, authority);
	}
	
	public boolean insertNewCustomer(Customer customer) {
		return customerDao.insertNewCustomer(customer);
	}
	
}