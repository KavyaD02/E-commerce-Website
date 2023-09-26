package com.akshaya.ecommerce.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.akshaya.ecommerce.domain.CartItem;
import com.akshaya.ecommerce.domain.Customer;
import com.akshaya.ecommerce.domain.Seller;
import com.akshaya.ecommerce.domain.WishlistItem;

@Mapper
public interface CustomerDao {
	
	@Select("SELECT * FROM Customer WHERE Customer_ID = #{id}")
	Customer findByID(@Param("id") int id);
	
	@Select("SELECT * FROM Customer")
	List<Customer> findCustomers();
	
	@Select("SELECT * FROM Customer WHERE Username = #{username}")
	Customer findByUsername(@Param("username") String username);
	
	@Select("SELECT Username FROM Users")
	List<String> findUsersList();
	
	@Select("SELECT * FROM Seller WHERE Username = #{username}")
	Seller findSellerByUsername(@Param("username") String username);
	
	@Select("SELECT Shopping_Cart.Cart_ID FROM Customer, Shopping_Cart WHERE Customer.Customer_ID = Shopping_Cart.Customer_ID AND Username = #{username}")
	String findCartID(@Param("username") String username);
	
	@Select("SELECT Wishlist.List_ID FROM Customer, Wishlist WHERE Customer.Customer_ID = Wishlist.Customer_ID AND Username = #{username}")
	String findWishlistID(@Param("username") String username);
	
	@Select("SELECT Item_ID FROM Cart_Item WHERE Cart_ID = #{cartID}")
	List<String> findCartItemIDList(@Param("cartID") String cartID);
	
	@Select("SELECT Item_ID FROM Wishlist_Item WHERE List_ID = #{wishlistID}")
	List<String> findWishlistItemIDList(@Param("wishlistID") String wishlistID);
	
	@Select("SELECT Product_ID FROM Cart_Item WHERE Cart_ID = #{cartID}")
	List<String> findCartProductIDList(@Param("cartID") String cartID);
	
	@Select("SELECT Product_ID FROM Wishlist_Item WHERE List_ID = #{wishlistID}")
	List<String> findWishlistProductIDList(@Param("wishlistID") String wishlistID);
	
	@Select("SELECT * FROM Cart_Item WHERE Cart_ID = #{cartID} AND Product_ID = #{productID}")
	CartItem findCartItem(@Param("cartID") String cartID, @Param("productID") String productID);
	
	@Select("SELECT * FROM Wishlist_Item WHERE List_ID = #{wishlistID} AND Product_ID = #{productID}")
	WishlistItem findWishlistItem(@Param("wishlistID") String wishlistID, @Param("productID") String productID);
	
	@Update("UPDATE Cart_Item SET Quantity = #{quantity} WHERE Cart_ID = #{cartID} AND Product_ID = #{productID}")
	boolean updateQuantity(@Param("quantity") int quantity, @Param("cartID") String cartID, @Param("productID") String productID);
	
	@Select("SELECT * FROM Cart_Item WHERE Cart_ID = #{cartID}")
	List<CartItem> findAllCartItems(@Param("cartID") String cartID);
	
	@Select("SELECT * FROM Wishlist_Item WHERE List_ID = #{wishlistID}")
	List<WishlistItem> findAllWishlistItems(@Param("wishlistID") String wishlistID);
	
	@Select("SELECT Total_Price FROM Shopping_Cart WHERE Cart_ID = #{cartID}")
	int findTotalCartPrice(@Param("cartID") String cartID);
	
	@Update("UPDATE Shopping_Cart SET Total_Price = #{price} WHERE Cart_ID = #{cartID}")
	boolean updatePrice(@Param("price") int price, @Param("cartID") String cartID);
	
	@Delete("DELETE FROM Cart_Item WHERE Cart_ID = #{cartID} AND Product_ID = #{productID}")
	boolean deleteProductFromCart(@Param("cartID") String cartID, @Param("productID") String productID);
	
	@Delete("DELETE FROM Wishlist_Item WHERE List_ID = #{listID} AND Product_ID = #{productID}")
	boolean deleteProductFromWishlist(@Param("listID") String listID, @Param("productID") String productID);
	
	@Insert("INSERT INTO Users VALUES (#{username}, #{password}, #{enabled})")
	boolean insertNewUser(@Param("username") String username, @Param("password") String password, @Param("enabled") int enabled);
	
	@Insert("INSERT INTO Authorities VALUES (#{username}, #{authority})")
	boolean insertNewAuthority(@Param("username") String username, @Param("authority") String authority);
	
	@Insert("INSERT INTO Customer VALUES (#{customerID}, #{customerName}, #{customerAddress}, #{customerUsername})")
	boolean insertNewCustomer(Customer customer);
}
