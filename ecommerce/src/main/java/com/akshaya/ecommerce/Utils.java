package com.akshaya.ecommerce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utils {
	
	public static String createItemID(List<String> itemIDList) {
		
		int max = 0;
		String itemID = "AKSC";
		
		for (String ID: itemIDList) {
			System.out.println(ID);
			int itemNum = obtainItemNumber(ID);
			if (itemNum > max) {
				max = itemNum;
			}
		}
		String maxNum = String.valueOf(max + 1);
		if (maxNum.length() == 1) {
			itemID = itemID + "00" + maxNum;
		} else if (maxNum.length() == 2) {
			itemID = itemID + "0" + maxNum;
		} else if (maxNum.length() == 3) {
			itemID = itemID + maxNum;
		}
		return itemID;
	}
	
	public static String createPaymentID(List<String> paymentIDList) { 
		int max = 0;
		String paymentID = "AKPA";
		
		for (String ID: paymentIDList) {
			int itemNum = obtainPaymentNumber(ID);
			if (itemNum > max) {
				max = itemNum;
			}
		}
		String maxNum = String.valueOf(max + 1);
		if (maxNum.length() == 1) {
			paymentID = paymentID + "00" + maxNum;
		} else if (maxNum.length() == 2) {
			paymentID = paymentID + "0" + maxNum;
		} else if (maxNum.length() == 3) {
			paymentID = paymentID + maxNum;
		}
		return paymentID;
	}
	
	public static int obtainPaymentNumber(String paymentID) {
		String[] stringList = paymentID.split("A");
		String string = stringList[2].replaceFirst("^0+(?!$)", "");
		return (int) Integer.valueOf(string);
	}
	
	public static int obtainItemNumber(String itemID) {
		String[] stringList = itemID.split("C");
		String string = stringList[1].replaceFirst("^0+(?!$)", "");
		return (int) Integer.valueOf(string);
	}
	
	public static String createProductID(List<String> productIDList) {
		int max = 0;
		String productID = "AKP";
		
		for (String ID: productIDList) {
			int itemNum = obtainProductNumber(ID);
			if (itemNum > max) {
				max = itemNum;
			}
		}
		String maxNum = String.valueOf(max + 1);
		if (maxNum.length() == 1) {
			productID = productID + "00" + maxNum;
		} else if (maxNum.length() == 2) {
			productID = productID + "0" + maxNum;
		} else if (maxNum.length() == 3) {
			productID = productID + maxNum;
		}
		return productID;
	}
	
	public static String createCustomerID(List<String> customerIDList) {
		int max = 0;
		String customerID = "AKC";
		
		for (String ID: customerIDList) {
			int itemNum = obtainCustomerNumber(ID);
			if (itemNum > max) {
				max = itemNum;
			}
		}
		
		String maxNum = String.valueOf(max + 1);
		if (maxNum.length() == 1) {
			customerID = customerID + "00" + maxNum;
		} else if (maxNum.length() == 2) {
			customerID = customerID + "0" + maxNum;
		} else if (maxNum.length() == 3) {
			customerID = customerID + maxNum;
		}
		return customerID;
	}
	
	public static String createCartID(List<String> cartIDList) {
		int max = 0;
		String cartID = "AKCA";
		
		for (String ID: cartIDList) {
			int itemNum = obtainCartNumber(ID);
			if (itemNum > max) {
				max = itemNum;
			}
		}
		
		String maxNum = String.valueOf(max + 1);
		if (maxNum.length() == 1) {
			cartID = cartID + "00" + maxNum;
		} else if (maxNum.length() == 2) {
			cartID = cartID + "0" + maxNum;
		} else if (maxNum.length() == 3) {
			cartID = cartID + maxNum;
		}
		return cartID;
	}
	
	public static int obtainCartNumber(String cartID) {
		String[] stringList = cartID.split("A");
		String string = stringList[2].replaceFirst("^0+(?!$)", "");
		return (int) Integer.valueOf(string);
	}
	
	public static String createWishlistID(List<String> wishlistIDList) {
		int max = 0;
		String wishlistID = "AKW";
		
		for (String ID: wishlistIDList) {
			int itemNum = obtainWishlistNumber(ID);
			if (itemNum > max) {
				max = itemNum;
			}
		}
		
		String maxNum = String.valueOf(max + 1);
		if (maxNum.length() == 1) {
			wishlistID = wishlistID + "00" + maxNum;
		} else if (maxNum.length() == 2) {
			wishlistID = wishlistID + "0" + maxNum;
		} else if (maxNum.length() == 3) {
			wishlistID = wishlistID + maxNum;
		}
		return wishlistID;
	}
	
	public static int obtainWishlistNumber(String cartID) {
		String[] stringList = cartID.split("W");
		String string = stringList[1].replaceFirst("^0+(?!$)", "");
		return (int) Integer.valueOf(string);
	}
	
	public static int obtainCustomerNumber(String customerID) {
		String[] stringList = customerID.split("C");
		String string = stringList[1].replaceFirst("^0+(?!$)", "");
		return (int) Integer.valueOf(string);
	}
	
	public static int obtainProductNumber(String productID) {
		String[] stringList = productID.split("P");
		String string = stringList[1].replaceFirst("^0+(?!$)", "");
		return (int) Integer.valueOf(string);
	}

	public static void main(String[] args) {
		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
		
		System.out.println(e.encode("gautham"));
		System.out.println(e.encode("sheethal"));
		System.out.println(e.encode("siddhartha"));
		
		List<String> strs = new ArrayList<String>();
		strs.add("KAVYA SHOP");
		strs.add("TMP JACA JSGOA");
		
		parseAndCapitalize(strs);
	}
	
	public static String encodeString(String string) {
		BCryptPasswordEncoder e = new BCryptPasswordEncoder();
		return e.encode(string);
	}
	
	public static List<String> parseAndCapitalize(List<String> stringArray) {
		List<String> capitalStringArray = new ArrayList<String>();
		for (String s : stringArray) {
			String[] sArray = s.split(" ");
			String newStr = "";
			for (int i = 1; i < sArray.length; i++) {
				String str = sArray[i];
				str = str.toLowerCase();
				str = str.substring(0, 1).toUpperCase() + str.substring(1);
				newStr = newStr + " " + str;
			}
			newStr = newStr.substring(1);
			capitalStringArray.add(newStr);
 		}
		return capitalStringArray;
	}
}
