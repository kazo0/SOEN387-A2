package soen387a2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game extends DomainObject {
	
	public String Name;
	public String Description;
	public double Price;
	public int Qty;	

	public Game (int id, String name, String desc, double price, int qty ){
		super(id);
		Name = name;
		Description = desc;
		Price = price;
		Qty = qty;
	}
	

	@Override
	void Insert(Connection conn) {
		// TODO Auto-generated method stub
		//DB Insert 
		String values = "'" + this.Name + "','" + this.Price + "','" + this.Description + "','" + this.Qty + "'";
		String query = "INSERT INTO Games (name,price,description,quantity) VALUES (" + values + ")";
		int  id = DBAccess.ExecuteInsert(conn, query);
		// Set id from DB
		this.setID(id);
		
		GameIdentiyMap.getInstance().addGame(this);
	}

	@Override
	void Update(Connection conn) {
		// TODO Auto-generated method stub
		String query = "UPDATE Games SET name='" + Name + "',price='" + Price + "',description='" + Description + "', quantity='" + Qty +  "' WHERE id=" + this.getID();
		DBAccess.Execute(conn, query);
	}

	@Override
	void Delete(Connection conn) {
		// TODO Auto-generated method stub
		String query = "DELETE FROM Games WHERE id=" + this.getID();
		DBAccess.Execute(conn, query);
		GameIdentiyMap.getInstance().delete(this.getID());

	}
	
	

}

