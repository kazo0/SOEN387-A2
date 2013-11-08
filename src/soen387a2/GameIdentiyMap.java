package soen387a2;

import java.util.HashMap;
import java.util.Map;

public class GameIdentiyMap {

	private static GameIdentiyMap soleInstance = null;
	
	public static GameIdentiyMap getInstance() {
		if (soleInstance == null) return new GameIdentiyMap();
		else return soleInstance;
	}
	
	private Map game = new HashMap(); 
		
	public void addGame(Game arg) {
		soleInstance.game.put(arg.getID(), arg);
	}
	public Game get(int key) {
		return (Game) soleInstance.game.get(key);
	}
	
	public void delete(int key) {
		soleInstance.game.remove(key);
	}
	
}


