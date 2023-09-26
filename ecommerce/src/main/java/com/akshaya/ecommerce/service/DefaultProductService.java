package com.akshaya.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akshaya.ecommerce.dao.ProductDao;
import com.akshaya.ecommerce.domain.CartItem;
import com.akshaya.ecommerce.domain.Category;
import com.akshaya.ecommerce.domain.Payment;
import com.akshaya.ecommerce.domain.Product;
import com.akshaya.ecommerce.domain.ShoppingCart;
import com.akshaya.ecommerce.domain.Wishlist;
import com.akshaya.ecommerce.domain.WishlistItem;

@Service
public class DefaultProductService implements ProductService {

	@Autowired
	ProductDao productDao;
	
	public List<Product> getProducts()
	{
		return productDao.findProducts();
	}
	
	public List<Product> getProductsByCategory(String category)
	{
		return productDao.findProductsByCategory(category);
	}

	public Product getProductByID(String id) {
		return productDao.findById(id);
	}

	public List<String> getCategories() {
		return productDao.findCategories();
	}

	public List<Category> getShopCategories() {
		return productDao.findShopCategories();
	}

	public List<Category> getSubCategories(String parentName) {
		return productDao.findSubCategories(parentName);
	}
	
	public String getCategoryID(String categoryName) {
		return productDao.findCategoryID(categoryName);
	}

	public boolean insertCartItem(CartItem cartItem) {
		return productDao.insertCartItem(cartItem);
	}

	public boolean insertWishlistItem(WishlistItem wishlistItem) {
		return productDao.insertWishlistItem(wishlistItem);
	}
	
	public boolean insertProduct(Product product) {
		return productDao.insertProduct(product);
	}

	@Override
	public List<String> getProductIDList() {
		return productDao.findProductIDList();
	}

	@Override
	public int getPriceFromID(String productID) {
		return productDao.getPriceFromID(productID);
	}

	@Override
	public boolean insertPayment(Payment payment) {
		return productDao.insertPayment(payment);
	}

	@Override
	public List<String> selectPaymentID() {
		return productDao.selectPaymentID();
	}

	@Override
	public boolean deleteProduct(String productID) {
		// TODO Auto-generated method stub
		return productDao.deleteProduct(productID);
	}

	@Override
	public List<String> findSellerProducts(String sellerID) {
		// TODO Auto-generated method stub
		return productDao.findSellerProducts(sellerID);
	}
	
	@Override
	public boolean insertCart(ShoppingCart cart) {
		// TODO Auto-generated method stub
		return productDao.insertCart(cart);
	}
	
	@Override
	public boolean insertWishlist(Wishlist wishlist) {
		// TODO Auto-generated method stub
		return productDao.insertWishlist(wishlist);
	}
	
	@Override
	public List<String> findCartIDs() {
		// TODO Auto-generated method stub
		return productDao.findCartIDs();
	}

	@Override
	public List<String> findWishlistIDs() {
		// TODO Auto-generated method stub
		return productDao.findWishlistIDs();
	}
}
