package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import patterns.GameMapper;
import database.DBAccess;

public class Game extends DomainObject {
	
	private String Name;
	private String Description;
	private double Price;
	private int Qty;	

	public Game (int id, String name, String desc, double price, int qty ){
		super(id);
		Name = name;
		Description = desc;
		Price = price;
		Qty = qty;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public int getQty() {
		return Qty;
	}

	public void setQty(int qty) {
		Qty = qty;
	}
	
	

}

