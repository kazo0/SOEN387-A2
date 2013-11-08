package soen387a2;

import java.util.ArrayList;

public class Game extends DomainObject {
	
	public String Name;
	public String Description;
	public double Price;
	public double Qty;
	public static ArrayList<Integer> Identity = new ArrayList<Integer>();

	

	public Game (int id, String name, String desc, double price, double qty ) {
		this.setID(id);
		Name = name;
		Description = desc;
		Price = price;
		Qty = qty;
		
	}
	
	public boolean exists(int id)
	{ 
		return Game.Identity.contains(id);
		
	}

}

