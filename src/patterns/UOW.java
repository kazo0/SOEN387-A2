package patterns;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import models.*;

import org.junit.Assert;

import database.JdbcUtilViaSSH;
import database.SSHjdbcSession;

import java.sql.Connection;
import java.sql.SQLException;


public class UOW {
	private List<DomainObject> newObjects = new ArrayList();
	private List<DomainObject> dirtyObjects = new ArrayList();
	private List<DomainObject> removedObjects = new ArrayList();
	private static ThreadLocal current = new ThreadLocal();
	
	
	public static void newCurrent() {
		setCurrent(new UOW());
	}
	public static void setCurrent(UOW uow) {
		current.set(uow);
	}
	public static UOW getCurrent() {
		return (UOW) current.get();
	}
	
	
	public void registerNew(DomainObject obj) {
		Assert.assertNotNull("id not null", obj.getID());
		Assert.assertTrue("object not dirty", !dirtyObjects.contains(obj));
		Assert.assertTrue("object not removed", !removedObjects.contains(obj));
		Assert.assertTrue("object not already registered new", !newObjects.contains(obj));
		newObjects.add(obj);
	}
	
	public void registerDirty(DomainObject obj) {
		Assert.assertNotNull("id not null", obj.getID());
		Assert.assertTrue("object not removed", !removedObjects.contains(obj));
		if (!dirtyObjects.contains(obj) && !newObjects.contains(obj)) {
			dirtyObjects.add(obj);
		}
	}
	
	public void registerRemoved(DomainObject obj) {
		Assert.assertNotNull("id not null", obj.getID());
		if (newObjects.remove(obj)) return;
		dirtyObjects.remove(obj);
		if (!removedObjects.contains(obj)) {
			removedObjects.add(obj);
		}
	}
	
	public void registerClean(DomainObject obj) {
		Assert.assertNotNull("id not null", obj.getID());
	}
	
	public void commit() {
		
		SSHjdbcSession ssHsession = JdbcUtilViaSSH.getConnection();
		Connection connection = ssHsession.getConnection();
		
		if (connection != null)
		{
			insertNew(connection);
			updateDirty(connection);
			deleteRemoved(connection);
			newObjects.clear();
			dirtyObjects.clear();
			removedObjects.clear();
			//Close connection
			JdbcUtilViaSSH.close(null, null, ssHsession);
		}
		
		else 
		{
			System.out.println("Connection : false");
		}
		

	}
	private void insertNew(Connection conn) {
		for (Iterator objects = newObjects.iterator(); objects.hasNext();) {
			DomainObject obj = (DomainObject) objects.next();
			// DB Insert
			GameMapper.getInstance().insert(obj, conn);
		}
	}
	
	private void updateDirty(Connection conn) {
		for (Iterator objects = dirtyObjects.iterator(); objects.hasNext();) {
			DomainObject obj = (DomainObject) objects.next();
			// DB Update
			GameMapper.getInstance().update(obj, conn);
		}
	}
	
	private void deleteRemoved(Connection conn) {
		for (Iterator objects = removedObjects.iterator(); objects.hasNext();) {
			DomainObject obj = (DomainObject) objects.next();
			// DB Delete
			GameMapper.getInstance().delete(obj.getID(), conn);
		}
	}


}