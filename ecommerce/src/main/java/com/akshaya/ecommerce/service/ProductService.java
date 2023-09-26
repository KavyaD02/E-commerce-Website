package com.akshaya.ecommerce.service;

import java.util.List;

import com.akshaya.ecommerce.domain.CartItem;
import com.akshaya.ecommerce.domain.Category;
import com.akshaya.ecommerce.domain.Payment;
import com.akshaya.ecommerce.domain.Product;
import com.akshaya.ecommerce.domain.ShoppingCart;
import com.akshaya.ecommerce.domain.Wishlist;
import com.akshaya.ecommerce.domain.WishlistItem;


public interface ProductService {
	
	public Product getProductByID(String productID);
	
	public List<Product> getProducts();
	
	public List<Product> getProductsByCategory(String category);
	
	public List<String> getCategories();
	
	public List<Category> getShopCategories();
	
	public List<Category> getSubCategories(String parentName);
	
	public String getCategoryID(String categoryName);
	
	public boolean insertCartItem(CartItem cartItem);
	
	public boolean insertWishlistItem(WishlistItem wishlistItem);
	
	public boolean insertProduct(Product product);
	
	public List<String> getProductIDList();
	
	public int getPriceFromID(String productID);
	
	public boolean insertPayment(Payment payment);
	
	public List<String> selectPaymentID();
	
	public boolean deleteProduct(String productID);
	
	public List<String> findSellerProducts(String sellerID);

	boolean insertCart(ShoppingCart cart);

	boolean insertWishlist(Wishlist wishlist);

	List<String> findCartIDs();

	List<String> findWishlistIDs();
	
}
