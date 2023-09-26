CREATE TABLE Customer (
	Customer_ID varchar(20) PRIMARY KEY,
	Username varchar(20) NOT NULL,
    Customer_Name varchar(20) NOT NULL,
    Customer_Address varchar(50)
);

CREATE TABLE Customer_Phone (
	Customer_ID varchar(20) NOT NULL,
    Customer_Phone numeric(10) NOT NULL,
    CONSTRAINT Customer_Phone_ID PRIMARY KEY (Customer_ID, Customer_Phone)
);

CREATE TABLE Shop (
	Shop_ID varchar(20) PRIMARY KEY,
    Shop_Name varchar(20) NOT NULL,
    Shop_Floor integer NOT NULL
);

CREATE TABLE Shopping_Cart (
	Cart_ID varchar(20) PRIMARY KEY,
    Total_Price number(10, 2) NOT NULL, 
    Customer_ID varchar(20) NOT NULL
);

CREATE TABLE Seller (
	Seller_ID varchar(20) PRIMARY KEY,
	Username varchar(50) not null,
    Seller_Name varchar(20) NOT NULL,
    Seller_Address varchar(20), 
    Shop_ID varchar(20) NOT NULL, 
    FOREIGN KEY (Shop_ID) REFERENCES Shop (Shop_ID)
);

CREATE TABLE Seller_Phone(
	Seller_ID varchar(20) NOT NULL,
    Seller_Phone numeric(10) NOT NULL, 
    CONSTRAINT Seller_Phone_ID PRIMARY KEY (Seller_ID, Seller_Phone)
);

CREATE TABLE Product(
	Product_ID varchar(20) PRIMARY KEY,
    Product_Name varchar(20) NOT NULL, 
    Product_Desc varchar(500),   
    Product_Image varchar(100),
    Rating integer NOT NULL,
    Category varchar(20) NOT NULL,
    Price numeric(10,2) NOT NULL,
    Shop_ID varchar(20) FOREIGN KEY REFERENCES Shop (Shop_ID) NOT NULL
);

CREATE TABLE Wishlist(
	List_ID varchar(20) PRIMARY KEY,
    Total_Price numeric(10,2) NOT NULL, 
    Customer_ID varchar(20) NOT NULL, 
    FOREIGN KEY (Customer_ID) REFERENCES Customer (Customer_ID)
);

CREATE TABLE Wishlist_Item(
	List_ID varchar(20),
    Item_ID varchar(20) NOT NULL,
    Product_ID varchar(20) NOT NULL,  
    FOREIGN KEY (Product_ID) REFERENCES Product (Product_ID),
    CONSTRAINT List_Item_ID PRIMARY KEY (List_ID, Item_ID)
);

CREATE TABLE Cart_Item(
	Cart_ID varchar(20),
    Item_ID varchar(20) NOT NULL,
    Product_ID varchar(20) NOT NULL, 
    Quantity integer, 
    FOREIGN KEY (Product_ID) REFERENCES Product (Product_ID),
    CONSTRAINT Cart_Item_ID PRIMARY KEY (Cart_ID, Item_ID));

CREATE TABLE Payment(
	Payment_ID varchar(20) PRIMARY KEY,
    Amount numeric(10) NOT NULL,
    Payment_Mode varchar(20) NOT NULL,
    Customer_ID varchar(20) NOT NULL, 
    FOREIGN KEY (Customer_ID) REFERENCES Customer (Customer_ID)
);

CREATE TABLE Users
(
	Username VARCHAR(50) NOT NULL PRIMARY KEY,
    Password VARCHAR(68) NOT NULL,
    Enabled integer NOT NULL
);

CREATE TABLE Authorities
(
	Username VARCHAR(50) NOT NULL,
    Authority VARCHAR(68) NOT NULL,
    FOREIGN KEY (Username) REFERENCES Users (Username)
);
