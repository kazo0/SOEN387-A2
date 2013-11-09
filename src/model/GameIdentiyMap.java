package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import database.DBAccess;
import database.JdbcUtilViaSSH;
import database.SSHjdbcSession;

public class GameIdentiyMap {

	private static GameIdentiyMap soleInstance = null;
	
	public static GameIdentiyMap getInstance() {
		if (soleInstance == null) soleInstance =  new GameIdentiyMap();
		
		return soleInstance;
	}
	
	private Map game = new HashMap(); 
		
	public void addGame(Game arg) {
		soleInstance.game.put(arg.getID(), arg);
	}
	public Game get(int key) {
		Game gm =  (Game) soleInstance.game.get(key);
		if (gm == null) {
			try{
				SSHjdbcSession ssHsession = JdbcUtilViaSSH.getConnection();
				Connection connection = ssHsession.getConnection();
				String query = "select * from Games where id =" + key; 
				ResultSet rs = DBAccess.ExecuteQuery(connection, query);
				rs.next();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				String desc = rs.getString("description");
				int qty = rs.getInt("quantity");
				gm = new Game(id,name,desc,price,qty);
				this.addGame(gm);
				JdbcUtilViaSSH.close(null, null, ssHsession);
				}
			catch (Exception ex) {
					
			}
		}
		return gm;
	}
	
	public void delete(int key) {
		soleInstance.game.remove(key);
	}
	
}


