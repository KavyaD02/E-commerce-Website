package com.akshaya.ecommerce.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.akshaya.ecommerce.domain.CartItem;
import com.akshaya.ecommerce.domain.Category;
import com.akshaya.ecommerce.domain.Payment;
import com.akshaya.ecommerce.domain.Product;
import com.akshaya.ecommerce.domain.ShoppingCart;
import com.akshaya.ecommerce.domain.Wishlist;
import com.akshaya.ecommerce.domain.WishlistItem;

@Mapper
public interface ProductDao {

	@Select("SELECT * FROM Product WHERE product_id = #{id}")
	Product findById(@Param("id") String id);
	
	@Select("SELECT * FROM Product")
	List<Product> findProducts();
	
	@Select("SELECT * FROM Product WHERE Category_ID = #{category}")
	List<Product> findProductsByCategory(@Param("category") String category);
	
	@Select("SELECT DISTINCT Category FROM Product")
	List<String> findCategories();
	
	@Select("SELECT Cart_ID FROM Shopping_Cart")
	List<String> findCartIDs();
	
	@Select("SELECT list_ID FROM Wishlist")
	List<String> findWishlistIDs();
	
	@Select("SELECT * FROM Category WHERE Parent_Category IS NULL")
	List<Category> findShopCategories();
	
	@Select("SELECT * FROM Category WHERE Parent_Category = #{parentName}")
	List<Category> findSubCategories(@Param("parentName") String parentCategory);
	
	@Select("SELECT Category_ID FROM Category WHERE Category_Name = #{categoryName}")
	String findCategoryID(@Param("categoryName") String categoryName);
	
	@Insert("INSERT INTO Cart_Item VALUES (#{cartID}, #{itemID}, #{productID}, #{quantity})")
	boolean insertCartItem(CartItem cartItem);
	
	@Insert("INSERT INTO Wishlist_Item VALUES (#{listID}, #{itemID}, #{productID})")
	boolean insertWishlistItem(WishlistItem wishlistItem);
	
	@Insert("INSERT INTO Product VALUES (#{productID}, #{productName}, #{productDesc}, #{rating}, #{price}, #{shopID}, #{categoryID})")
	boolean insertProduct(Product product);
	
	@Select("SELECT Product_ID FROM Product")
	List<String> findProductIDList();
	
	@Select("SELECT Price FROM Product WHERE Product_ID = #{productID}")
	int getPriceFromID(String productID);
	
	@Insert("INSERT INTO Payment VALUES (#{paymentID}, #{amount}, #{paymentMode}, #{customerID})")
	boolean insertPayment(Payment payment);
	
	@Select("SELECT Payment_ID FROM Payment")
	List<String> selectPaymentID();
	
	@Delete("DELETE FROM Product WHERE Product_ID = #{productID}")
	boolean deleteProduct(String productID);
	
	@Select("SELECT Product_ID FROM Seller, Product WHERE Seller.Shop_ID = Product.Shop_ID AND Seller.Seller_ID = #{sellerID}")
	List<String> findSellerProducts(String sellerID);
	
	@Insert("INSERT INTO Shopping_Cart VALUES (#{cartID}, #{totalPrice}, #{customerID})")
	boolean insertCart(ShoppingCart cart);
	
	@Insert("INSERT INTO Wishlist VALUES (#{listID}, #{totalPrice}, #{customerID})")
	boolean insertWishlist(Wishlist wishlist);
	
}
