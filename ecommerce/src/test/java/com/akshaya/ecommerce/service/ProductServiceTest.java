package com.akshaya.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.akshaya.ecommerce.BaseTest;
import com.akshaya.ecommerce.domain.Product;

public class ProductServiceTest extends BaseTest {

	@Autowired
	ProductService productService;
	
	@Test
	public void allProductsTest () {
		List<Product> products = productService.getProducts();
		//System.out.println(products.size());
		assertTrue(products != null, "Products list must not be null");
		assertTrue(products.size() > 0, "Products list must not be empty");
	}
	
	@Test
	public void categoryProductsTest () {
		List<Product> products = productService.getProductsByCategory("Decoration");
		assertTrue(products != null, "Products list must not be null");
		assertTrue(products.size() > 0, "Products list must not be empty");
		for (Product p : products)
		{
			assertTrue("Decoration".equals(p.getCategory()), "Product must be of small category");
			//System.out.print(p.getProductName());
		}
	}
	
	@Test
	public void categoryTest() {
		List<String> categories = productService.getCategories();
		assertTrue(categories != null, "Category list must not be null");
		assertTrue(categories.size() > 0, "Category list must not be empty");
		for (String category : categories) {
			System.out.println(category);
		}
	}
}
