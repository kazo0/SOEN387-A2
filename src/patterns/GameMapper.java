package patterns;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.DBStatus;
import models.DomainObject;
import models.Game;
import database.DBAccess;
import database.JdbcUtilViaSSH;
import database.SSHjdbcSession;

public class GameMapper {

	
	private static GameMapper soleInstance = null;
	
	public static GameMapper getInstance() {
		if (soleInstance == null) soleInstance =  new GameMapper();
		
		return soleInstance;
	}
	
	private Map<Integer, Game> game = new HashMap<Integer, Game>(); 
		
	public void addGameToMap(Game arg) {
		soleInstance.game.put(arg.getID(), arg);
	}
	
	public Game[] getAll() {
		
		List<Game> list = new ArrayList<Game>();
		SSHjdbcSession ssHsession = JdbcUtilViaSSH.getConnection();
		Connection connection = ssHsession.getConnection();
		String query = "select * from Games";
		ResultSet rs = DBAccess.ExecuteQuery(connection, query);
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				Game gm = game.get(id);
				if (gm == null){
					String name = rs.getString("name");
					double price = rs.getDouble("price");
					String desc = rs.getString("description");
					int qty = rs.getInt("quantity");
					gm = new Game(id,name,desc,price,qty);
				}
				list.add(gm);
			}
			// Add All New Games
			List<DomainObject> addedObjects = UOW.getCurrent().getAllNew();
			for(DomainObject item : addedObjects) {
				list.add((Game)item);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Close connection
		JdbcUtilViaSSH.close(null, null, ssHsession);
		return list.toArray(new Game[list.size()]);
	}
	
	private DBStatus GetStatus() {
		
		return null;
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
				this.addGameToMap(gm);
				
				//close connection
				JdbcUtilViaSSH.close(null, null, ssHsession);
				}
			catch (Exception ex) {
					
			}
		}
		
		return gm;
	}
	
	public void insert (DomainObject obj, Connection conn) {
		Game gm = (Game) obj;
		//DB Insert 
		String values = "'" + gm.getName() + "','" + gm.getPrice() + "','" + gm.getDescription() + "','" + gm.getQty() + "'";
		String query = "INSERT INTO Games (name,price,description,quantity) VALUES (" + values + ")";
		int  id = DBAccess.ExecuteInsert(conn, query);
		// Set id from DB
		gm.setID(id);
		soleInstance.game.put(gm.getID(), gm);
	}
	
	public void update(DomainObject obj , Connection conn) {
		Game gm = (Game) obj;
		String query = "UPDATE Games SET name='" + gm.getName() + "',price='" + gm.getPrice() + "',description='" + gm.getDescription() + "', quantity='" + gm.getQty() +  "' WHERE id=" + gm.getID();
		DBAccess.Execute(conn, query);
	}
	public void delete(int key, Connection conn) {
		String query = "DELETE FROM Games WHERE id=" + key;
		DBAccess.Execute(conn, query);
		soleInstance.game.remove(key);
	}

	public void CleanAll() {
		for (Map.Entry<Integer, Game> entry : game.entrySet()) {
			entry.getValue().setStatus(DBStatus.CLEAN);
		}
		
	}
	
}


