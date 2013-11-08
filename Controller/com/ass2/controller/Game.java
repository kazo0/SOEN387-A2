import java.util.ArrayList;

public class Game extends DomainObject {
	
	public string Name;
	public string Description;
	public double Price;
	public double Qty;
	public static ArrayList<Integer> Identity = new ArrayList<Integer>();

	

	public Game (int id, string name, string desc, double price, double qty ) {
		setID(id);
		Name = name;
		Description = desc;
		Price = price;
		Qty = qty;
		
	}
	
	public bool exists(int id)
	{ 
		return Game.Identity.contains(id);
		
	}

}
