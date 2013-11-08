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
	
	public void AddGame(Game gm) {
		
	
	}

	@Override
	void Insert(Connection conn) {
		// TODO Auto-generated method stub
		//DB Insert 
		String query = "";
		ResultSet rs = DBAccess.ExecuteInsert(conn, query);
		// Set id from DB
		try {
			this.setID(rs.getInt(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GameIdentiyMap.getInstance().addGame(this);
	}

	@Override
	void Update(Connection conn) {
		// TODO Auto-generated method stub
		String query = "";
		DBAccess.Execute(conn, query);
	}

	@Override
	void Delete(Connection conn) {
		// TODO Auto-generated method stub
		String query = "";
		DBAccess.Execute(conn, query);
		GameIdentiyMap.getInstance().delete(this.getID());

	}
	
	

}

