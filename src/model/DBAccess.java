package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess {

	public static ResultSet ExecuteQuery (Connection connection, String query) {
		ResultSet result = null;
		try {
			Statement st = connection.createStatement();
			result = st.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	public static void Execute (Connection connection, String query) {
		try {
			Statement st = connection.createStatement();
			st.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static int ExecuteInsert (Connection connection, String query) {
		ResultSet result = null;
		int id = -1;
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			result = st.getGeneratedKeys();
			result.next();
			id =  result.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
		
	}
}
