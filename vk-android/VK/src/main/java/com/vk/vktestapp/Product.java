package com.vk.vktestapp;

public class Product {

	String name;
	int price;
	int image;
	boolean box;


	Product(String first_name, int _price, int _image, boolean _box) {
		name = first_name;
		price = _price;
		image = _image;
		box = _box;
	}
}
